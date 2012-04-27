package com.doucome.corner.web.zhe.action;

import javax.servlet.http.HttpServletResponse;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;

/**
 * ��½
 * @author shenjia.caosj 2012-4-1
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends DdzBasicAction {
	

	@Override
	public String execute() throws Exception {
		return SUCCESS ;
	}
	
	/**
	 * �Ա���½
	 * @return
	 * @throws Exception
	 */
	public String tblogin() throws Exception {
		HttpServletResponse response = getResponse() ;
		String taobaoLoginLink = DefaultUriService.getFactoryURI(URIConstant.TAOBAO_LOGIN_LINK) ;
		response.sendRedirect(taobaoLoginLink) ;
		return null ;
	}
	
	
}
