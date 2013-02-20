package com.doucome.corner.biz.dcome.model.facade;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;

public class QQPfModel extends PfModel {

	/**
     * 用户登录当前平台的key，相当于密码的作用（对应qq的openkey）
     */
    private String             openKey;
    /**
     * 用户在当前登录平台的昵称
     */
    private String             pfNick;
    
    /**
     * 外部用户id（对应qq的openid）
     */
    private String             openId;
    
    private DcLoginSourceEnums pf ;

	public String getOpenKey() {
		return openKey;
	}

	public void setOpenKey(String openKey) {
		this.openKey = openKey;
	}

	public String getPfNick() {
		return pfNick;
	}

	public void setPfNick(String pfNick) {
		this.pfNick = pfNick;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public DcLoginSourceEnums getPf() {
		return pf;
	}

	public void setPf(DcLoginSourceEnums pf) {
		this.pf = pf;
	}

    
}
