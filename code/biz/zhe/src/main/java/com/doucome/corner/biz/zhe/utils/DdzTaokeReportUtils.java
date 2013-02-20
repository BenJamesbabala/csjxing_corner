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
 * 报表工具
 * @author langben 2012-9-16
 *
 */
public class DdzTaokeReportUtils {

	/**
	 * 判断报表是否可以用户人工结算
	 * @param report
	 * @return
	 */
	public static boolean canManualSettle(DdzTaokeReportDO report) {
		if(report == null){
			return false ;
		}
		//已经有结算ID
		if(IDUtils.isCorrect(report.getSettleId())){
			return false ;
		}
		
		String settleStatus = report.getSettleStatus() ;
		String settleAlipay = report.getSettleAlipay() ;
		String outcodeType = report.getOutcodeType() ;
		//String isRefuned = report.getIsRefund() ;
		
		//类型是手动结算
		if(OutCodeEnums.toOutCodeEnums(outcodeType) != OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL && OutCodeEnums.toOutCodeEnums(outcodeType) != OutCodeEnums.DDZ_ACCOUNT_ID_JFB){
			return false ;
		}
		
		//支付宝为空
		if(StringUtils.isBlank(settleAlipay)){
			return false ;
		}
		
		//状态不是未结算
		if(SettleStatusEnums.fromValue(settleStatus) != SettleStatusEnums.UNSETTLE){
			return false ;
		}
		
		//延迟
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
		//大额的看是否到达延迟限制时间
		if(settleFee.compareTo(delaySettleAmount) > -1){
			if(createDate == null){
				return true ;
			}
			Calendar calCreate = Calendar.getInstance() ;
			calCreate.setTime(createDate) ;
			Calendar calNow = Calendar.getInstance() ;
			calCreate.add(Calendar.DATE, delayDays) ;
			if(calCreate.after(calNow)){ //没到打款周期
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * 结算SettleDO
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
	 * 计算结算模型（结算的现金和集分宝）
	 * @param settleCash
	 * @param refundCash
	 * @param settleJfb
	 * @param refundJfb
	 */
	public static SettleModel calcSettleModel(BigDecimal settleCash , BigDecimal refundCash , int settleJfb , int refundJfb){
		
		SettleModel settleModel = new SettleModel() ;
		
		//计算 提现金额 是不是大于 维权金额
		//现金类维权大于0
		if(DecimalUtils.isGreaterThan0(refundCash)){  
			if(DecimalUtils.greatEqualThan(settleCash, refundCash)){
				settleCash = settleCash.subtract(refundCash) ;
			} else {
				//维权先扣除现金，结算现金变为0
				refundCash = refundCash.subtract(settleCash) ;
				refundJfb += DdzJfbConvertUtils.convertMoney2Jfb(refundCash) ;
				settleCash = DecimalConstant.ZERO ;
			}
		}
		
		//计算提现的集分宝 是不是 大于 维权的集分宝
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
