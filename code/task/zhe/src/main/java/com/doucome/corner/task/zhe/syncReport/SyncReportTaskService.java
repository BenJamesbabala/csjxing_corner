package com.doucome.corner.task.zhe.syncReport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeReportMemberDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.taobao.model.TaobaokeDate;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzSyncReportTaskDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.enums.OutCodeEnums;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule.UserCommission;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzSyncReportTaskService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.utils.OutCodeUtils;
import com.doucome.corner.biz.zhe.utils.OutCodeUtils.OutCode;
import com.doucome.corner.task.zhe.TaskService;
import com.doucome.corner.task.zhe.model.SyncReportCodeEnums;
import com.doucome.corner.task.zhe.model.SyncReportRunResult;
import com.doucome.corner.task.zhe.model.SyncReportRunResult.Page;
import com.doucome.corner.task.zhe.utils.TaskUtils;

/**
 * 同步淘宝客报表
 *
 */
public class SyncReportTaskService implements TaskService {

	private static final Log syncReportLog = LogFactory.getLog(LogConstant.task_syncReport_log) ;
	
	private static final Log log = LogFactory.getLog(SyncReportTaskService.class) ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	 
	@Autowired
	private DdzSyncReportTaskService ddzSyncReportTaskService ;
	
	@Autowired
	private DdzAccountService ddzAccountService ;
		
	@Autowired
	private DdzEatDiscountRule ddzEatDiscountRule ;
	
	/**
	 * task interval
	 */
	@Override
	public String executeInternal(){
		
		/**
		 * 跑前2天的数据
		 */
		Date date = TaskUtils.getLastLateDate() ;
		
		TaobaokeDate taobaokeDate = new TaobaokeDate(date) ; 
		
		return syncDailyReport(taobaokeDate) ;
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public String syncDailyReport(TaobaokeDate date){
		
		SyncReportRunResult returnObject = new SyncReportRunResult() ; //return Value 
		
		String taskId = date.getDateFormat() ;
		
		DdzSyncReportTaskDO ddzSyncReportTaskDO = ddzSyncReportTaskService.getByTaskId(taskId) ;

		//任务已经执行过
		if(ddzSyncReportTaskDO != null){
			syncReportLog.info("taskId : " + taskId + " has already runed , skip .") ;
			returnObject.setCode(SyncReportCodeEnums.ASSIGN_ERROR) ;
			returnObject.setDetailMsg("taskId [" + taskId + "] has been runed , skip .") ;
			return returnObject.toString() ;
		}
		
		DdzSyncReportTaskDO reportTask = new DdzSyncReportTaskDO() ;
		reportTask.setTaskId(taskId) ;
				
		try{
			//分配任务
			int createId = ddzSyncReportTaskService.createReportTask(reportTask) ; //分配taskId
			
		}catch(Exception e) {
			log.error(e.getMessage() , e) ;
			syncReportLog.error("error create report task record . " + e.getMessage()) ;
			returnObject.setCode(SyncReportCodeEnums.ASSIGN_ERROR) ;
			returnObject.setDetailMsg("create task record error , msg : " + e.getMessage()) ;
			return returnObject.toString();
		}
		
		//从淘宝API获取报表
		List<TaobaokeReportMemberDTO> taobaoReportList = getAllReport(date, returnObject) ;
		if(!CollectionUtils.isEmpty(taobaoReportList)){
			//同步报表到数据库
			int successCount = syncEveryReport(taobaoReportList) ;
			returnObject.setSuccessCount(successCount) ;
		}
		
		if(!CollectionUtils.isEmpty(returnObject.getFailPages())){
			returnObject.setCode(SyncReportCodeEnums.PARTIAL_SUCCESS) ;
		}
		
		reportTask.setIsSuccess(returnObject.isSuccess() ? TrueFalseEnums.TRUE.getValue() : TrueFalseEnums.FALSE.getValue()) ;
		reportTask.setReportMembCount(returnObject.getTotalCount()) ;
		reportTask.setSuccessCount(returnObject.getSuccessCount()) ;
		reportTask.setRunoutData(returnObject.toString()) ;
		reportTask.setGmtReport(date.getDate()) ;
		try {
			ddzSyncReportTaskService.updateByTaskId(reportTask) ;
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
			returnObject.setCode(SyncReportCodeEnums.UPDATE_TASK_ERROR) ;
			returnObject.setDetailMsg("update task record error , msg : "  + e.getMessage()) ;
		}
		return returnObject.toString() ;
	}
		
	private List<TaobaokeReportMemberDTO> getAllReport(TaobaokeDate date , SyncReportRunResult runout){
		
		
		List<TaobaokeReportMemberDTO> reportList = new ArrayList<TaobaokeReportMemberDTO>() ;
		List<Page> failPageList = new ArrayList<Page>() ;
		QueryResult<TaobaokeReportMemberDTO> result = null ;
		Integer totalPages = null ;
		Long totalRecords = null ;
		int page = 1  ;
		while(true){
			Pagination pagination = new Pagination(page, 40) ;
			try {
				
				result = taobaokeServiceDecorator.getReport(date, TaobaokeFields.taoke_report_memb_fields , pagination) ;
				if(result != null){
					reportList.addAll(result.getItems()) ;
				}
			}catch(Exception e){
				log.error(e.getMessage() , e) ;
				syncReportLog.error("error get report . " + e.getMessage()) ;
				failPageList.add(new Page(pagination.getPage(), pagination.getStart(), pagination.getSize())) ; //添加失败记录
			}
						
			if(totalPages == null){
				if(result == null){ //取记录出错
					break ;
				}
				totalPages = pagination.getTotalPages() ;
				totalRecords = result.getTotalRecords() ;
			}
				
			
			page += 1  ; // 下一页
			
			if( page > totalPages ){
				break ;
			}		
		}
		
		//runout.setSuccessCount(successCount) ;
		
		if(totalRecords != null){
			runout.setTotalCount(Integer.valueOf(String.valueOf(totalRecords))) ;
		}
		if(!CollectionUtils.isEmpty(failPageList)){
			runout.setFailPages(failPageList) ;
		}
		return reportList ;
		
	}
	
	/**
	 * 同步每一个报表
	 * @param itemList
	 * @return successCount 
	 */
	private int syncEveryReport(List<TaobaokeReportMemberDTO> itemList){
		if(CollectionUtils.isEmpty(itemList)) {
			return 0 ;
		}
		int successCount = 0 ;
		for(TaobaokeReportMemberDTO item : itemList){
			if(item == null){
				continue ;
			}
			DdzTaokeReportDO report = new DdzTaokeReportDO() ;
			report.setCategoryId(item.getCategoryId()) ;
			report.setCommission(item.getCommission()) ;
			report.setCommissionRate(item.getCommissionRate()) ;
			report.setGmtPaid(item.getPayTime()) ;
			report.setSettleStatus(SettleStatusEnums.UNSETTLE.toString());
			report.setItemNum(item.getItemNum()) ;
			report.setItemTitle(item.getItemTitle()) ;
			report.setNumIid(item.getNumIid()) ;
			report.setOutCode(item.getOuterCode()) ;
			report.setPayPrice(item.getPayPrice()) ;
			report.setRealPayFee(item.getRealPayFee()) ;
			report.setSellerNick(item.getSellerNick()) ;
			report.setTradeId(item.getTradeId()) ;
			String outCode = report.getOutCode() ;
			//
			if(StringUtils.isNotBlank(report.getOutCode())){
				OutCode o = OutCodeUtils.decodeOutCode(outCode) ;
				if(o.getType() == OutCodeEnums.DDZ_ACCOUNT_ID || o.getType() == OutCodeEnums.UNKNOWN){
					String accountId = o.getOutCode() ;
					DdzAccountDO acc = ddzAccountService.queryAccountByAccountId(accountId) ;
					if(acc != null){ //账号存在
						report.setSettleAlipay(acc.getAlipayId()) ;
						BigDecimal newCommissionRate = DecimalUtils.multiply(item.getCommissionRate(),DecimalConstant.HUNDRED) ;
						UserCommission userCommission = DdzEatDiscountRule.calcUserCommissions(ddzEatDiscountRule, item.getCommission() , newCommissionRate , item.getRealPayFee()) ;
						report.setUserCommission(userCommission.getCommission()) ;
						report.setUserCommissionRate(DecimalUtils.divide(userCommission.getCommissionRate(),DecimalConstant.HUNDRED)) ;
					} else {
						syncReportLog.error("cant find account from outCode : " + outCode) ;
					}
				}
			}
			
			ddzTaokeReportService.createReport(report) ;
			successCount ++ ;
		}
		return successCount ;
	}
	
}
