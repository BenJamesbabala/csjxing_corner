package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

/**
 * Ö§¸¶±¦µÇÂ½
 * @author langben 2012-9-20
 *
 */
public class DdzAlipayLoginDO {

	private Long id ;
	
	/**
	 * Ö§¸¶±¦ID¡£
	 */
	private String nativeAlipayId ;
	
	/**
	 * Ö§¸¶±¦ÕËºÅ
	 */
	private String bindAlipayAccount ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBindAlipayAccount() {
		return bindAlipayAccount;
	}

	public void setBindAlipayAccount(String bindAlipayAccount) {
		this.bindAlipayAccount = bindAlipayAccount;
	}

	public String getNativeAlipayId() {
		return nativeAlipayId;
	}

	public void setNativeAlipayId(String nativeAlipayId) {
		this.nativeAlipayId = nativeAlipayId;
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
}
