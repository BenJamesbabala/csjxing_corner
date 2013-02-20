package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * @author ze2200
 */
public class DdzTaokeReportSettleDO extends AbstractModel {

	private Long id;
	/**
	 * 不使用
	 */
	private String settleId;
	/**
	 * 结算ALIPAY
	 */
	private String settleAlipay;
	/**
	 * 结算佣金
	 */
	private BigDecimal settleFee;
	
	/**
	 * 结算的集分宝
	 */
	private Integer settleJfb ;
	
	/**
	 * 结算状态
	 */
	private String settleStatus;
	private String settleBatchno;
	private String alipayBatchno;
	private String alipayStatus;
	private String emailStatus;
	private String mobileStatus;
	private String memo;
	/**
	 * 结算类型（OutcodeEnums）| A 自动打款  | B 用户自提
	 */
	private String outcodeType ;
	private Date gmtCreate;
	private Date gmtModified;
	private Date gmtSettled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(String mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public String getSettleAlipay() {
		return settleAlipay;
	}

	public void setSettleAlipay(String settleAlipay) {
		this.settleAlipay = settleAlipay;
	}

	public BigDecimal getSettleFee() {
		return settleFee;
	}

	public void setSettleFee(BigDecimal settleFee) {
		this.settleFee = settleFee;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getSettleBatchno() {
		return settleBatchno;
	}

	public void setSettleBatchno(String settleBatchno) {
		this.settleBatchno = settleBatchno;
	}

	public String getAlipayBatchno() {
		return alipayBatchno;
	}

	public void setAlipayBatchno(String alipayBatchno) {
		this.alipayBatchno = alipayBatchno;
	}

	public String getAlipayStatus() {
		return alipayStatus;
	}

	public void setAlipayStatus(String alipayStatus) {
		this.alipayStatus = alipayStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtSettled() {
		return gmtSettled;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public void setGmtSettled(Date gmtSettled) {
		this.gmtSettled = gmtSettled;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOutcodeType() {
		return outcodeType;
	}

	public void setOutcodeType(String outcodeType) {
		this.outcodeType = outcodeType;
	}

	public Integer getSettleJfb() {
		return settleJfb;
	}

	public void setSettleJfb(Integer settleJfb) {
		this.settleJfb = settleJfb;
	}
	
}
