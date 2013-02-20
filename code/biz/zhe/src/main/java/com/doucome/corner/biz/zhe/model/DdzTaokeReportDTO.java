package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils;

public class DdzTaokeReportDTO extends AbstractModel {

	private DdzTaokeReportDO report ;
	
	public DdzTaokeReportDTO(DdzTaokeReportDO report){
		if(report == null){
			report = new DdzTaokeReportDO() ;
		}
		this.report = report ;
	}
	
	public String getGmtCreateFormatYMD(){
		Date gmtCreate = getGmtCreate() ;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd") ;
		return df.format(gmtCreate) ;
	}
	
	public String getPrivateSettleAlipay(){
		return StringUtils.substring(this.getSettleAlipay(), 0, 2)  + "****" ;
	}
	
	public BigDecimal getOriginUserCommission(){
		
		return DecimalUtils.multiply(this.getPayPrice(), this.getUserCommissionRate()) ;
	}
	
	public String getFormatUserCommission(){
		if(this.getUserCommission() == null){
			return "" ;
		}
		return DecimalUtils.format(this.getUserCommission(), "0.00") ;
	}
	
	public String getFormatUserCommissionRate(){
		if(this.getUserCommissionRate() == null){
			return "" ;
		}
		return DecimalUtils.format(this.getUserCommissionRate().multiply(new BigDecimal(100)), "0.00") ;
	}
	
	
	/**
	 * -------------------------------------------------------------------------------
	 */

	public BigDecimal getUserJfbRate(){
		return report.getUserJfbRate() ;
	}
	
	public boolean isDelaySettle(){
		return DdzTaokeReportUtils.isDelaySettle(report) ;
	}
	
	public String getSettleTaobaoNick() {
		return report.getSettleTaobaoNick();
	}


	public BigDecimal getUserCommission() {
		return report.getUserCommission();
	}

	public BigDecimal getUserCommissionRate() {
		return report.getUserCommissionRate();
	}
	
	public Long getId() {
		return report.getId();
	}

	public BigDecimal getCommissionRate() {
		return report.getCommissionRate();
	}

	public BigDecimal getRealPayFee() {
		return report.getRealPayFee();
	}

	public String getOutCode() {
		return report.getOutCode();
	}

	public Long getTradeId() {
		return report.getTradeId();
	}

	public Date getGmtPaid() {
		return report.getGmtPaid();
	}

	public BigDecimal getPayPrice() {
		return report.getPayPrice();
	}

	public Long getNumIid() {
		return report.getNumIid();
	}

	public String getItemTitle() {
		return report.getItemTitle();
	}

	public Long getItemNum() {
		return report.getItemNum();
	}

	public Long getCategoryId() {
		return report.getCategoryId();
	}

	public BigDecimal getCommission() {
		return report.getCommission();
	}

	public String getSellerNick() {
		return report.getSellerNick();
	}

	public String getSettleAlipay() {
		return report.getSettleAlipay();
	}

	public String getSettleUid() {
		return report.getSettleUid();
	}

	public BigDecimal getSettleFee() {
		return report.getSettleFee();
	}

	public String getSettleStatus() {
		return report.getSettleStatus();
	}

	public Date getGmtSettled() {
		return report.getGmtSettled();
	}

	public Date getGmtCreate() {
		return report.getGmtCreate();
	}

	public Date getGmtModified() {
		return report.getGmtModified();
	}

	public String getSettleResult() {
		return report.getSettleResult();
	}

	public Long getSettleId() {
		return report.getSettleId();
	}

	public String getOutcodeType() {
		return report.getOutcodeType();
	}

	public String getInsertBatch() {
		return report.getInsertBatch();
	}


	public Long getDcItemId() {
		return report.getDcItemId();
	}

	public Long getDcUserId() {
		return report.getDcUserId();
	}
	
	public String getRefundStatus() {
		return report.getRefundStatus();
	}
	
	public Integer getSettleJfb(){
		return report.getSettleJfb() ;
	}
}
