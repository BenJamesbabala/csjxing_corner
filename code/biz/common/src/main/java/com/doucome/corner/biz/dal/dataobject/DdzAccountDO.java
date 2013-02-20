package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ��Account.java��ʵ���������ʺ�MODEL
 * 
 * @author ib 2012-3-4 ����06:50:14
 */
public class DdzAccountDO extends AbstractModel {

	/**
	 * PK
	 */
	private Integer id;
	/**
	 * �˺�ID
	 */
	private String accountId;
	/**
	 * alipayId
	 */
	private String alipayId;
	/**
	 * usrId
	 */
	private String uid;
	
	/**
	 * �˿�άȨ����
	 */
	private Integer refundCount ;
	
	/**
	 * �������Ѵ���
	 */
	private Integer notifyCount ;
	
	private Date gmtCreate;
	private Date gmtModified ;
	
	/**
	 * ���֧������½ʱ��
	 */
	private Date gmtLastLogin ;
	
	/**
	 * ������ʱ��
	 */
	private Date gmtLastVisit ;
	
	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Integer refundCount) {
		this.refundCount = refundCount;
	}

	public Integer getNotifyCount() {
		return notifyCount;
	}

	public void setNotifyCount(Integer notifyCount) {
		this.notifyCount = notifyCount;
	}

	public Date getGmtLastLogin() {
		return gmtLastLogin;
	}

	public void setGmtLastLogin(Date gmtLastLogin) {
		this.gmtLastLogin = gmtLastLogin;
	}

	public Date getGmtLastVisit() {
		return gmtLastVisit;
	}

	public void setGmtLastVisit(Date gmtLastVisit) {
		this.gmtLastVisit = gmtLastVisit;
	}

}
