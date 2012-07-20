package com.doucome.corner.biz.core.sms.model;

import java.io.Serializable;

import com.doucome.corner.biz.core.model.AbstractModel;


/**
 * 下行短信
 * @author shenjia.caosj 2012-7-19
 *
 */
public class SmsMtDO extends AbstractModel implements Serializable  {

	private static final long serialVersionUID = -4517943087428707999L;

	/**
	 * businessId , 不能为空
	 */
	private SmsBusinessEnums businessId ;
	
	/**
	 * 替换短信内容中{0}、{1}等占位符
	 */
	private String[] msgParameter ;
	
	/**
	 * 下行号码
	 */
	private String toMobile ;
	
	/**
	 * 通道
	 */
	private String chlCode ;
	
	/**
	 * message key ,默认为“normal”
	 */
	private String messageKey = "normal" ;
	
	/**
	 * 短信类别
	 */
	private String msgId ;
	
	/**
	 * 短信批次
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
