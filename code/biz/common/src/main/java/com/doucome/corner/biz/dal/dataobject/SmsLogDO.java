package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

/**
 * ������־
 * @author langben 2012-9-10
 *
 */
public class SmsLogDO {

	private Long id  ;
	
	/**
	 * ҵ������
	 */
	private String businessId ;
	
	private String messageKey ;
	
	/**
	 * ������װ����
	 */
	private String msgParameter ;
	
	/**
	 * �ֻ�
	 */
	private String toMobile ;
	
	/**
	 * ����
	 */
	private String batId ;
	
	/**
	 * �ύ״̬��
	 */
	private String resultCode ;
	
	private Date gmtCreate ;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgParameter() {
		return msgParameter;
	}

	public void setMsgParameter(String msgParameter) {
		this.msgParameter = msgParameter;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getToMobile() {
		return toMobile;
	}

	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}

	public String getBatId() {
		return batId;
	}

	public void setBatId(String batId) {
		this.batId = batId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
}
