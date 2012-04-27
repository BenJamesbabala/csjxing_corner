package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * 类Account.java的实现描述：帐号MODEL
 * 
 * @author ib 2012-3-4 下午06:50:14
 */
public class DdzAccountDO extends AbstractModel {

	/**
	 * PK
	 */
	private Integer id;
	/**
	 * 账号ID
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
	private Date gmtCreate;
	private Date gmtModified ;
	
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

}
