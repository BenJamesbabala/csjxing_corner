package com.doucome.corner.task.zhe.syncReport.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils;

/**
 * 处理点点折报表结算
 * @author langben 2012-9-6
 *
 */
public class DdzReportSettleHandler implements Handler {
	
	private static final Log syncReportLog = LogFactory.getLog(LogConstant.task_syncReport_log) ;
	
	private static final Log log = LogFactory.getLog(DcPromotionReportHandler.class) ;

	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;

	@Override
	public HandleResult handleReport(List<DdzTaokeReportDO> reportList) {
		if(CollectionUtils.isEmpty(reportList)){
			return new HandleResult();
		}
		
		try {
			//相同的settleAlipay合并
			Map<String,List<DdzTaokeReportDO>> mergedMap = mergeReport(reportList) ;
			
			//计算每个支付宝的结算金额
			if(MapUtils.isNotEmpty(mergedMap)) {
				Set<Map.Entry<String, List<DdzTaokeReportDO>>> entrySet = mergedMap.entrySet() ;
				for(Map.Entry<String, List<DdzTaokeReportDO>> entry : entrySet){
					if(entry == null){
						continue ;
					}
					String settleAlipay = entry.getKey() ; //alipay
					List<DdzTaokeReportDO> personList = entry.getValue() ;
					DdzTaokeReportSettleDO settleDO = DdzTaokeReportUtils.buildSettleModel(settleAlipay, OutCodeEnums.DDZ_ACCOUNT_ID, personList) ;
					
					if(settleDO != null){
						//insert record 
						Long settleId = ddzTaokeReportSettleService.insertSettleReport(settleDO); 
						List<Long> idList = DdzTaokeReportUtils.getReportIds(personList) ;
						//更新对应报表的settleId
						int effectCount = ddzTaokeReportService.updateTaokeReportSettleId(idList, settleId);
						if(effectCount != idList.size()){
							syncReportLog.error("settleCount is not equal to update count." + effectCount + " idList:"  + ArrayStringUtils.toString(idList)) ;
						}
					}
					
				}
			}
		}catch(Exception e){
			log.error(e.getMessage() , e) ;
			syncReportLog.error(e.getMessage()) ;
		}
		
		
		return new HandleResult() ;
	}
		
	private Map<String,List<DdzTaokeReportDO>> mergeReport(List<DdzTaokeReportDO> allReport){
		
		Map<String,List<DdzTaokeReportDO>> mergeMap = new HashMap<String,List<DdzTaokeReportDO>>();
		
		if(CollectionUtils.isEmpty(allReport)){
			return mergeMap ;
		}
		for(DdzTaokeReportDO report : allReport){
			if(report == null){
				continue ;
			}
			
			String outcodeType = report.getOutcodeType() ;
			OutCodeEnums outCode = OutCodeEnums.toOutCodeEnums(outcodeType) ;
			if(outCode != OutCodeEnums.DDZ_ACCOUNT_ID){
				continue ;
			}
			
			String settleAlipay = report.getSettleAlipay() ;
			if(StringUtils.isBlank(settleAlipay)){
				continue ;
			}
			List<DdzTaokeReportDO> personalList = null ;
			if(mergeMap.containsKey(settleAlipay)){
				personalList = mergeMap.get(settleAlipay) ;
			} else {
				personalList = new ArrayList<DdzTaokeReportDO>() ;
				mergeMap.put(settleAlipay, personalList) ;
			}
			
			personalList.add(report) ;
		}
		
		return mergeMap ;
	}
}
