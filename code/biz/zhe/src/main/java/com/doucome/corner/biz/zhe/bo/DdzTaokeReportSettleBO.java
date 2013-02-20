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
 * ����۱������BO
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
			//ɸѡ����Ҫ�����
			for(Iterator<DdzTaokeReportDO> i = reportList.iterator() ; i.hasNext();){
				DdzTaokeReportDO report = i.next() ;
				//����άȨ�����ﲻ�ж�
				if(!DdzTaokeReportUtils.canManualSettle(report)){
					i.remove() ;
				}
			}
		}
		
		return reportList ;
	}
	
	/**
	 * �ϲ�ĳһ�û��ı��������¼
	 * @param userId
	 * @throws ManualSettleNotSufficientException  û�п����ֵļ�¼
	 */
	@Transactional(rollbackFor=RuntimeException.class)
	public ManualSettleResult manualCombineSettlesByUser(String settleAlipay) throws ManualSettleNotSufficientException {
		
		ManualSettleResult result = new ManualSettleResult() ;
		
		//άȨ�ļ�¼���ų�
		List<DdzTaokeReportDO> reportList = getManualCombineSettlesByUser(settleAlipay) ;
		
		if(CollectionUtils.isEmpty(reportList)){
			return result ;
		}
		
		//����û��άȨû������
		TaokeReportSearchCondition refundCondition = new TaokeReportSearchCondition() ;
		refundCondition.setSettleAlipay(settleAlipay) ;
		refundCondition.setRefundStatus(DdzRefundStatusEnums.REFUND_TRUE.getValue()) ;
		List<DdzTaokeReportDO> refundList = ddzTaokeReportService.getReports(refundCondition) ;
		
		//������
		DdzTaokeReportSettleDO cashSettleModel = DdzTaokeReportUtils.buildSettleModel(settleAlipay ,OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL , reportList) ;
		DdzTaokeReportSettleDO jfbSettleModel = DdzTaokeReportUtils.buildSettleModel(settleAlipay, OutCodeEnums.DDZ_ACCOUNT_ID_JFB, reportList) ;
			
		
		
		//άȨ�ĿάȨ��Ϊ �����ֱ����͡��ֽ�
		BigDecimal refundTotalCash = DdzTaokeReportUtils.calcTotalSettleFee(refundList) ;//�ֽ���άȨ
		int refundTotalJfb = DdzTaokeReportUtils.calcTotalSettleJfb(refundList) ; //���ֱ���άȨ
		
		BigDecimal cashTotalSettleFee = cashSettleModel.getSettleFee() ; //�ֽ������
		int jfbTotalSettleFee = NumberUtils.parseInt(jfbSettleModel.getSettleJfb()) ;//���ֱ�����
		
		SettleModel settleModel = DdzTaokeReportUtils.calcSettleModel(cashTotalSettleFee, refundTotalCash, jfbTotalSettleFee, refundTotalJfb) ;
		if(!settleModel.isCanSettle()) {
			throw new ManualSettleNotSufficientException("ddz.settle.refund.bigger") ;
		}
		
		
		
		//άȨ�����֣�
		//1.�Ѿ����ɽ��㣨�ⲿ��ֻ�ܴ��µļ�¼��۳���
		//2.��δ���֣�ֱ�Ӹı�״̬������
		//����άȨ
		List<Long> refundIdList = DdzTaokeReportUtils.getReportIds(refundList) ;
		
		List<Long> cashSettleIdList = DdzTaokeReportUtils.getReportIds(reportList, OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL) ;
		List<Long> jfbSettleIdList = DdzTaokeReportUtils.getReportIds(reportList, OutCodeEnums.DDZ_ACCOUNT_ID_JFB) ;

		if(CollectionUtils.isNotEmpty(refundList)) {
			for(DdzTaokeReportDO report : refundList) {
				if(IDUtils.isCorrect(report.getSettleId())){ //�Ѿ��н���ģ����������¼
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
					//��û�н���ģ��ѽ������Ϊ0
					ddzTaokeReportService.updateSettleFeeById(report.getId() , DecimalConstant.ZERO) ;
				}
			}
		}
		//άȨ�ļ�¼״̬��ΪάȨ������
		if(CollectionUtils.isNotEmpty(refundList)){
			ddzTaokeReportService.updateRefundByIds(refundIdList, DdzRefundStatusEnums.REFUND_PAYBACK) ;
			ddzTaokeReportService.updateSettleStatusByIds(refundIdList, SettleStatusEnums.SETTLE_REFUND_CHARGE) ;
		}
		
		//��������¼
		if(DecimalUtils.isGreaterThan0(settleModel.getCashAmount())){
			cashSettleModel.setSettleFee(settleModel.getCashAmount()) ;
			cashSettleModel.setSettleJfb(0) ;
			//����һ�������¼
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
