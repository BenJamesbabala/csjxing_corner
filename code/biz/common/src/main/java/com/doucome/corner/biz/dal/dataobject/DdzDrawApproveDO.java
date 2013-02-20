package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

/**
 * Ã·œ÷…Í«Î
 * @author langben 2012-9-20
 *
 */
public class DdzDrawApproveDO {

	private Long id ;
	
	/**
	 * ÷ß∏∂±¶µ«¬Ω∑÷≈‰µƒID
	 */
	private String loginId ;
	
	/**
	 * ÷ß∏∂±¶’À∫≈
	 */
	private String alipayAccount ;
	
	/**
	 * Ω·À„ID
	 */
	private String settleIds ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
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

	public String getSettleIds() {
		return settleIds;
	}

	public void setSettleIds(String settleIds) {
		this.settleIds = settleIds;
	}

}
