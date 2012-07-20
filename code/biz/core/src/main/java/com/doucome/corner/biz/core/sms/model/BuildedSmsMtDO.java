package com.doucome.corner.biz.core.sms.model;

import java.io.Serializable;

import com.doucome.corner.biz.core.model.AbstractModel;

public class BuildedSmsMtDO extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -3698994591791564878L;

	/**
	 * �����ֻ���
	 */
	private String toMobile ;
	
	/**
	 * ��������
	 */
	private String smsContent ; 
	
	/**
	 * ͨ��
	 */
	private String chlCode ;
	
	/**
	 * ���ű�ʶ
	 */
	private String msgId ; 
	
	/**
	 * ����
	 */
	private String batId ;
	
	/**
	 * ҵ��ID
	 */
	private String businessId ;
	
	
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getToMobile() {
		return toMobile;
	}

	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getChlCode() {
		return chlCode;
	}

	public void setChlCode(String chlCode) {
		this.chlCode = chlCode;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getBatId() {
		return batId;
	}

	public void setBatId(String batId) {
		this.batId = batId;
	}
	
	
}
