package com.doucome.corner.biz.zhe.bo;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.enums.DdzRefundStatusEnums;
import com.doucome.corner.biz.zhe.exception.ManualSettleNotSufficientException;
import com.doucome.corner.biz.zhe.model.ManualSettleResult;
import com.doucome.corner.biz.zhe.service.DdzJfbSettleRecordService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils.SettleModel;

/**
 * 点点折报表结算BO
 * @author langben 2012-9-16
 *
 */
public class DdzTaokeReportSettleBO {
	
	private static final Log syncReportLog = LogFactory.getLog(LogConstant.task_syncReport_log) ;

	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ; 
	
	@Autowired
	private DdzJfbSettleRecordService ddzJfbSettleRecordService ;
	
	public List<DdzTaokeReportDO> getManualCombineSettlesByUser(String settleAlipay){
		TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition() ;
		searchCondition.setOutcodeTypeList(new String[]{OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL.getName() ,OutCodeEnums.DDZ_ACCOUNT_ID_JFB.getName()});
		searchCondition.setSettleAlipay(settleAlipay) ;
		searchCondition.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
		List<DdzTaokeReportDO> reportList = ddzTaokeReportService.getReports(searchCondition) ;
		
		if(CollectionUtils.isNotEmpty(reportList)){
			//筛选不需要结算的
			for(Iterator<DdzTaokeReportDO> i = reportList.iterator() ; i.hasNext();){
				DdzTaokeReportDO report = i.next() ;
				//对于维权的这里不判断
				if(!DdzTaokeReportUtils.canManualSettle(report)){
					i.remove() ;
				}
			}
		}
		
		return reportList ;
	}
	
	/**
	 * 合并某一用户的报表到结算记录
	 * @param userId
	 * @throws ManualSettleNotSufficientException  没有可提现的记录
	 */
	@Transactional(rollbackFor=RuntimeException.class)
	public ManualSettleResult manualCombineSettlesByUser(String settleAlipay) throws ManualSettleNotSufficientException {
		
		ManualSettleResult result = new ManualSettleResult() ;
		
		//维权的记录不排除
		List<DdzTaokeReportDO> reportList = getManualCombineSettlesByUser(settleAlipay) ;
		
		if(CollectionUtils.isEmpty(reportList)){
			return result ;
		}
		
		//看有没有维权没有清算
		TaokeReportSearchCondition refundCondition = new TaokeReportSearchCondition() ;
		refundCondition.setSettleAlipay(settleAlipay) ;
		refundCondition.setRefundStatus(DdzRefundStatusEnums.REFUND_TRUE.getValue()) ;
		List<DdzTaokeReportDO> refundList = ddzTaokeReportService.getReports(refundCondition) ;
		
		//结算金额
		DdzTaokeReportSettleDO cashSettleModel = DdzTaokeReportUtils.buildSettleModel(settleAlipay ,OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL , reportList) ;
		DdzTaokeReportSettleDO jfbSettleModel = DdzTaokeReportUtils.buildSettleModel(settleAlipay, OutCodeEnums.DDZ_ACCOUNT_ID_JFB, reportList) ;
			
		
		
		//维权的款，维权分为 【集分宝】和【现金】
		BigDecimal refundTotalCash = DdzTaokeReportUtils.calcTotalSettleFee(refundList) ;//现金类维权
		int refundTotalJfb = DdzTaokeReportUtils.calcTotalSettleJfb(refundList) ; //集分宝类维权
		
		BigDecimal cashTotalSettleFee = cashSettleModel.getSettleFee() ; //现金结算金额
		int jfbTotalSettleFee = NumberUtils.parseInt(jfbSettleModel.getSettleJfb()) ;//集分宝结算
		
		SettleModel settleModel = DdzTaokeReportUtils.calcSettleModel(cashTotalSettleFee, refundTotalCash, jfbTotalSettleFee, refundTotalJfb) ;
		if(!settleModel.isCanSettle()) {
			throw new ManualSettleNotSufficientException("ddz.settle.refund.bigger") ;
		}
		
		
		
		//维权有两种：
		//1.已经生成结算（这部分只能从新的记录里扣除）
		//2.还未提现，直接改变状态不结算
		//处理维权
		List<Long> refundIdList = DdzTaokeReportUtils.getReportIds(refundList) ;
		
		List<Long> cashSettleIdList = DdzTaokeReportUtils.getReportIds(reportList, OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL) ;
		List<Long> jfbSettleIdList = DdzTaokeReportUtils.getReportIds(reportList, OutCodeEnums.DDZ_ACCOUNT_ID_JFB) ;

		if(CollectionUtils.isNotEmpty(refundList)) {
			for(DdzTaokeReportDO report : refundList) {
				if(IDUtils.isCorrect(report.getSettleId())){ //已经有结算的，插入充正记录
					DdzTaokeReportDO chargeReport = report.deepCopy() ;
					chargeReport.setId(null) ;
					chargeReport.setSettleFee(DecimalUtils.multiply(chargeReport.getSettleFee() , new BigDecimal("-1"))) ;
					if(chargeReport.getSettleJfb() != null){
						chargeReport.setSettleJfb(chargeReport.getSettleJfb() * -1) ;
					}
					chargeReport.setSettleStatus(SettleStatusEnums.SETTLE_REFUND_CHARGE.getValue()) ;
					//chargeReport.setTradeId(report.getTradeId()) ;
					Long id = ddzTaokeReportService.createReport(chargeReport) ;
					if(OutCodeEnums.DDZ_ACCOUNT_ID_JFB == OutCodeEnums.toOutCodeEnums(chargeReport.getOutcodeType())){
						jfbSettleIdList.add(id) ;
					} else {
						cashSettleIdList.add(id) ;
					}
					refundIdList.add(id) ; 
				} else {
					//还没有结算的，把结算金额改为0
					ddzTaokeReportService.updateSettleFeeById(report.getId() , DecimalConstant.ZERO) ;
				}
			}
		}
		//维权的记录状态改为维权已清算
		if(CollectionUtils.isNotEmpty(refundList)){
			ddzTaokeReportService.updateRefundByIds(refundIdList, DdzRefundStatusEnums.REFUND_PAYBACK) ;
			ddzTaokeReportService.updateSettleStatusByIds(refundIdList, SettleStatusEnums.SETTLE_REFUND_CHARGE) ;
		}
		
		//插入结算记录
		if(DecimalUtils.isGreaterThan0(settleModel.getCashAmount())){
			cashSettleModel.setSettleFee(settleModel.getCashAmount()) ;
			cashSettleModel.setSettleJfb(0) ;
			//插入一条结算记录
			Long settleId = ddzTaokeReportSettleService.insertSettleReport(cashSettleModel) ;		
			
			int effectCount = ddzTaokeReportService.updateTaokeReportSettleId(cashSettleIdList, settleId);
			result.addSettleId(settleId) ;
			
		}
		
		if(settleModel.getJfbAmount() > 0){
			jfbSettleModel.setSettleFee(DecimalConstant.ZERO) ;
			jfbSettleModel.setSettleJfb(settleModel.getJfbAmount()) ;
			Long settleId = ddzTaokeReportSettleService.insertSettleReport(jfbSettleModel) ;	
			
			int effectCount = ddzTaokeReportService.updateTaokeReportSettleId(jfbSettleIdList, settleId);
			result.addSettleId(settleId) ;
		}
	
		return result ;
	}
	
}
