package com.doucome.corner.biz.core.sms.model;

import java.io.Serializable;

import com.doucome.corner.biz.core.model.AbstractModel;


/**
 * ���ж���
 * @author shenjia.caosj 2012-7-19
 *
 */
public class SmsMtDO extends AbstractModel implements Serializable  {

	private static final long serialVersionUID = -4517943087428707999L;

	/**
	 * businessId , ����Ϊ��
	 */
	private SmsBusinessEnums businessId ;
	
	/**
	 * �滻����������{0}��{1}��ռλ��
	 */
	private String[] msgParameter ;
	
	/**
	 * ���к���
	 */
	private String toMobile ;
	
	/**
	 * ͨ��
	 */
	private String chlCode ;
	
	/**
	 * message key ,Ĭ��Ϊ��normal��
	 */
	private String messageKey = "normal" ;
	
	/**
	 * �������
	 */
	private String msgId ;
	
	/**
	 * ��������
	 */
	private String batId ;

	public SmsBusinessEnums getBusinessId() {
		return businessId;
	}

	public void setBusinessId(SmsBusinessEnums businessId) {
		this.businessId = businessId;
	}

	public String[] getMsgParameter() {
		return msgParameter;
	}

	public void setMsgParameter(String[] msgParameter) {
		this.msgParameter = msgParameter;
	}

	
	public String getToMobile() {
		return toMobile;
	}

	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}

	public String getChlCode() {
		return chlCode;
	}

	public void setChlCode(String chlCode) {
		this.chlCode = chlCode;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
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
