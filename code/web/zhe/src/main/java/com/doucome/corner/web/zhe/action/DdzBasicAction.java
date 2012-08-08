package com.doucome.corner.web.zhe.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.zhe.authz.DdzAuthz;

@SuppressWarnings("serial")
public abstract class DdzBasicAction extends BasicAction {

    @Autowired
    protected DdzAuthz ddzAuthz; 

	public DdzAuthz getDdzAuthz() {
		return ddzAuthz;
	}
	
	/**
	 *  «∑Ò”–÷ß∏∂±¶’À∫≈
	 * @return
	 */
	public String getAlipayAccount(){
		String alipayId = ddzAuthz.getAlipayId() ;
		if ("vip@diandianzhe.com".equals(alipayId)) {
			return null;
		}
		return alipayId;
	}
    
}
