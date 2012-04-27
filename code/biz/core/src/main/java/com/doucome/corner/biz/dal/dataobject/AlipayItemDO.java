package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author ze2200
 *
 */
public class AlipayItemDO {
	/**
	 * id.
	 */
	private String ids;
	/**
	 * user alipay account.
	 */
	private String account;
	/**
	 * 
	 */
	private BigDecimal amount;
	/**
	 * mobile phone which related alipay account.
	 */
	private String mobilePhone;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
