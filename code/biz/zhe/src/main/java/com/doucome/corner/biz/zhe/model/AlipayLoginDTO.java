package com.doucome.corner.biz.zhe.model;

import com.doucome.corner.biz.dal.model.AbstractModel;

public class AlipayLoginDTO extends AbstractModel {

	private String realName ;
	
	private String nativeAlipayId ;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNativeAlipayId() {
		return nativeAlipayId;
	}

	public void setNativeAlipayId(String nativeAlipayId) {
		this.nativeAlipayId = nativeAlipayId;
	}
	
	
	
}
