package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author ze2200
 *
 */
public class AlipayDetail {
	/**
	 * 付款流水号.
	 */
	private Long serialNO;
	/**
	 * 付款批次号.
	 */
	private String batchNO;
	/**
	 * 支付宝流水号.
	 */
	private String alipayId;
	/**
	 * 支付宝合作者身份id.
	 */
	private String payerId;
	/**
	 * 付款账户.
	 */
	private String payerAccount;
	/**
	 * 付款账户名.
	 */
	private String payerName;
	/**
	 * 收款账户.
	 */
	private String payeeAccount;
	/**
	 * 收款账户名.
	 */
	private String payeeName;
	/**
	 * 支付状态. I:init; S: success; F:fail;
	 */
	private String status;
	/**
	 * 支付金额.
	 */
	private BigDecimal fee;
	/**
	 * 支付备注.
	 */
	private String remark;
	/**
	 * 支付结果备注.
	 */
	private String resultRemark;
	/**
	 * 付款活动发起时间.
	 */
	private Date gmtCreated;
	/**
	 * 付款活动修改时间.
	 */
	private Date gmtModified;
	/**
	 * 付款活动结束时间.
	 */
	private Date gmtDone;
	
	public Long getSerialNO() {
		return serialNO;
	}
	public void setSerialNO(Long serialNO) {
		this.serialNO = serialNO;
	}
	public String getBatchNO() {
		return batchNO;
	}
	public void setBatchNO(String batchNO) {
		this.batchNO = batchNO;
	}
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getPayerAccount() {
		return payerAccount;
	}
	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayeeAccount() {
		return payeeAccount;
	}
	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResultRemark() {
		return resultRemark;
	}
	public void setResultRemark(String resultRemark) {
		this.resultRemark = resultRemark;
	}
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Date getGmtDone() {
		return gmtDone;
	}
	public void setGmtDone(Date gmtDone) {
		this.gmtDone = gmtDone;
	}
}
