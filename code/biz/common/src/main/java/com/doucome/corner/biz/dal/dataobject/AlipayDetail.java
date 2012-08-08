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
	 * ������ˮ��.
	 */
	private Long serialNO;
	/**
	 * �������κ�.
	 */
	private String batchNO;
	/**
	 * ֧������ˮ��.
	 */
	private String alipayId;
	/**
	 * ֧�������������id.
	 */
	private String payerId;
	/**
	 * �����˻�.
	 */
	private String payerAccount;
	/**
	 * �����˻���.
	 */
	private String payerName;
	/**
	 * �տ��˻�.
	 */
	private String payeeAccount;
	/**
	 * �տ��˻���.
	 */
	private String payeeName;
	/**
	 * ֧��״̬. I:init; S: success; F:fail;
	 */
	private String status;
	/**
	 * ֧�����.
	 */
	private BigDecimal fee;
	/**
	 * ֧����ע.
	 */
	private String remark;
	/**
	 * ֧�������ע.
	 */
	private String resultRemark;
	/**
	 * ��������ʱ��.
	 */
	private Date gmtCreated;
	/**
	 * �����޸�ʱ��.
	 */
	private Date gmtModified;
	/**
	 * ��������ʱ��.
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
