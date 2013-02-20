package com.doucome.corner.biz.zhe.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.taobao.config.SettleConfigMgr;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

/**
 * ������
 * @author langben 2012-9-16
 *
 */
public class DdzTaokeReportUtils {

	/**
	 * �жϱ����Ƿ�����û��˹�����
	 * @param report
	 * @return
	 */
	public static boolean canManualSettle(DdzTaokeReportDO report) {
		if(report == null){
			return false ;
		}
		//�Ѿ��н���ID
		if(IDUtils.isCorrect(report.getSettleId())){
			return false ;
		}
		
		String settleStatus = report.getSettleStatus() ;
		String settleAlipay = report.getSettleAlipay() ;
		String outcodeType = report.getOutcodeType() ;
		//String isRefuned = report.getIsRefund() ;
		
		//�������ֶ�����
		if(OutCodeEnums.toOutCodeEnums(outcodeType) != OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL && OutCodeEnums.toOutCodeEnums(outcodeType) != OutCodeEnums.DDZ_ACCOUNT_ID_JFB){
			return false ;
		}
		
		//֧����Ϊ��
		if(StringUtils.isBlank(settleAlipay)){
			return false ;
		}
		
		//״̬����δ����
		if(SettleStatusEnums.fromValue(settleStatus) != SettleStatusEnums.UNSETTLE){
			return false ;
		}
		
		//�ӳ�
		if(isDelaySettle(report)){
			return false ;
		}
				
		return true ;
	}
	
	public static boolean isDelaySettle(DdzTaokeReportDO report){
		int delayDays = SettleConfigMgr.get(SettleConfigMgr.DDZ).getDelaySettleDays() ;
		BigDecimal delaySettleAmount = SettleConfigMgr.get(SettleConfigMgr.DDZ).getDelaySettleAmount() ;
		Date createDate = report.getGmtCreate() ;
		BigDecimal settleFee = report.getSettleFee() ;
		if(settleFee == null){
			return false ;
		}
		//���Ŀ��Ƿ񵽴��ӳ�����ʱ��
		if(settleFee.compareTo(delaySettleAmount) > -1){
			if(createDate == null){
				return true ;
			}
			Calendar calCreate = Calendar.getInstance() ;
			calCreate.setTime(createDate) ;
			Calendar calNow = Calendar.getInstance() ;
			calCreate.add(Calendar.DATE, delayDays) ;
			if(calCreate.after(calNow)){ //û���������
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * ����SettleDO
	 * @param settleAlipay
	 * @param reportList
	 * @return
	 */
	public static DdzTaokeReportSettleDO buildSettleModel(String settleAlipay , OutCodeEnums type ,List<DdzTaokeReportDO> reportList){
		
		if(CollectionUtils.isEmpty(reportList)){
			return null ;
		}
		
		BigDecimal totalSettleFee = calcTotalSettleFee(reportList) ;
		int totalSettleJfb = calcTotalSettleJfb(reportList) ;
		
		DdzTaokeReportSettleDO settle = new DdzTaokeReportSettleDO() ;
		settle.setSettleAlipay(settleAlipay) ;
		settle.setSettleFee(totalSettleFee) ;
		settle.setSettleJfb(totalSettleJfb) ;
		settle.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
		settle.setOutcodeType(type.getName()) ;
		
		return settle ;
	}
	
	public static List<Long> getReportIds(List<DdzTaokeReportDO> reportList){
		List<Long> idList = new ArrayList<Long>() ;
		if(CollectionUtils.isEmpty(reportList)){
			return idList ;
		}
		for(DdzTaokeReportDO report : reportList){
			if(report == null){
				continue ;
			}
			idList.add(Long.valueOf(report.getId())) ;
		}
		
		return idList ;
	}
	
	public static List<Long> getReportIds(List<DdzTaokeReportDO> reportList , OutCodeEnums outcodeType){
		List<Long> idList = new ArrayList<Long>() ;
		if(CollectionUtils.isEmpty(reportList)){
			return idList ;
		}
		for(DdzTaokeReportDO report : reportList){
			
			if(report == null){
				continue ;
			}
			if(!StringUtils.equals(report.getOutcodeType() , outcodeType.getName())){
				continue ;
			}
			idList.add(Long.valueOf(report.getId())) ;
			
		}
		
		return idList ;
	}
	
	public static BigDecimal calcTotalSettleFee(List<DdzTaokeReportDO> reportList){
		
		BigDecimal amount = new BigDecimal("0") ;
		if(CollectionUtils.isEmpty(reportList)){
			return amount ;
		}
		
		for(DdzTaokeReportDO report : reportList){
			
			if(report.getSettleFee() == null){
				continue ;
			}
			
			if(StringUtils.equals(report.getOutcodeType() , OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL.getName()) ){
				amount = amount.add(report.getSettleFee()) ;
			}
		}
		
		return amount ;
		
	}
	
	public static int calcTotalSettleJfb(List<DdzTaokeReportDO> reportList) {
		int settleJfb = 0 ;
		if(CollectionUtils.isEmpty(reportList)){
			return settleJfb ;
		}
		for(DdzTaokeReportDO report : reportList){
			if(StringUtils.equals(report.getOutcodeType() , OutCodeEnums.DDZ_ACCOUNT_ID_JFB.getName()) ){
				settleJfb += NumberUtils.parseInt(report.getSettleJfb()) ;
			}
		}
		return settleJfb ;
	}
		
	/**
	 * �������ģ�ͣ�������ֽ�ͼ��ֱ���
	 * @param settleCash
	 * @param refundCash
	 * @param settleJfb
	 * @param refundJfb
	 */
	public static SettleModel calcSettleModel(BigDecimal settleCash , BigDecimal refundCash , int settleJfb , int refundJfb){
		
		SettleModel settleModel = new SettleModel() ;
		
		//���� ���ֽ�� �ǲ��Ǵ��� άȨ���
		//�ֽ���άȨ����0
		if(DecimalUtils.isGreaterThan0(refundCash)){  
			if(DecimalUtils.greatEqualThan(settleCash, refundCash)){
				settleCash = settleCash.subtract(refundCash) ;
			} else {
				//άȨ�ȿ۳��ֽ𣬽����ֽ��Ϊ0
				refundCash = refundCash.subtract(settleCash) ;
				refundJfb += DdzJfbConvertUtils.convertMoney2Jfb(refundCash) ;
				settleCash = DecimalConstant.ZERO ;
			}
		}
		
		//�������ֵļ��ֱ� �ǲ��� ���� άȨ�ļ��ֱ�
		if(refundJfb > 0){
			if(settleJfb >= refundJfb){
				settleJfb = settleJfb - refundJfb ;
				refundJfb = 0 ;
			} else {
				settleModel.setCanSettle(false) ;
				return settleModel ;
			}
		}
		
		settleModel.setCanSettle(DecimalUtils.isGreaterThan0(settleCash) || settleJfb > 0 ) ;
		settleModel.setCashAmount(settleCash) ;
		settleModel.setJfbAmount(settleJfb) ;
		return settleModel ;
	}
	
	public static List<Long> getSettleIdList(List<DdzTaokeReportSettleDO> list){
		List<Long> idList = new ArrayList<Long>() ;
		if(CollectionUtils.isEmpty(list)) {
			return idList ;
		}
		
		for(DdzTaokeReportSettleDO trs : list){
			idList.add(trs.getId()) ;
		}
		return idList ;
	}
	
	public static List<Long> getSettleIdList(List<DdzTaokeReportSettleDO> list , OutCodeEnums outcodeType){
		List<Long> settleIdList = new ArrayList<Long>() ;
		if(CollectionUtils.isNotEmpty(list)) {
			for(DdzTaokeReportSettleDO settle : list){
				OutCodeEnums o = OutCodeEnums.toOutCodeEnums(settle.getOutcodeType()) ;
				if( o == outcodeType ){
					settleIdList.add(settle.getId()) ;
				}
			}
		}
		return settleIdList ;
	}
	
	public static class SettleModel {
		
		private int jfbAmount ;
		
		private BigDecimal cashAmount ;
		
		private boolean canSettle = false ;

		public int getJfbAmount() {
			return jfbAmount;
		}

		public void setJfbAmount(int jfbAmount) {
			this.jfbAmount = jfbAmount;
		}

		public BigDecimal getCashAmount() {
			return cashAmount;
		}

		public void setCashAmount(BigDecimal cashAmount) {
			this.cashAmount = cashAmount;
		}

		public boolean isCanSettle() {
			return canSettle;
		}

		public void setCanSettle(boolean canSettle) {
			this.canSettle = canSettle;
		}
		
		
	}

	
}
