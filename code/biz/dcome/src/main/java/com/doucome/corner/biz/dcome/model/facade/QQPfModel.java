package com.doucome.corner.biz.dcome.model.facade;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;

public class QQPfModel extends PfModel {

	/**
     * �û���¼��ǰƽ̨��key���൱����������ã���Ӧqq��openkey��
     */
    private String             openKey;
    /**
     * �û��ڵ�ǰ��¼ƽ̨���ǳ�
     */
    private String             pfNick;
    
    /**
     * �ⲿ�û�id����Ӧqq��openid��
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
