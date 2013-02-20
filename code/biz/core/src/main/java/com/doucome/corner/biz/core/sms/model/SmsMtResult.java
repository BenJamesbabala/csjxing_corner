package com.doucome.corner.biz.core.sms.model;

import org.apache.commons.lang.StringUtils;

public class SmsMtResult {
	
	private String resultCode ;

	public boolean isSuccess() {
		return StringUtils.equals(StringUtils.trim(resultCode), "0");
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	
}
