package com.doucome.corner.web.zhe.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.EnvConstant;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.service.alipay.AlipayCore;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.EnvPropertiesUtil;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;

/**
 * µÇÂ½
 * @author langben 2012-4-1
 *
 */
@SuppressWarnings("serial")
public class LoginAction extends DdzBasicAction {
	@Autowired
    private DdzSessionOperator ddzSessionOperator;
	@Override
	public String execute() throws Exception {
		return SUCCESS ;
	}
	
	/**
	 * ÌÔ±¦µÇÂ½
	 * @return
	 * @throws Exception
	 */
	public String tblogin() throws Exception {
		HttpServletResponse response = getResponse() ;
		String taobaoLoginLink = DefaultUriService.getFactoryURI(URIConstant.TAOBAO_LOGIN_LINK) ;
		response.sendRedirect(taobaoLoginLink) ;
		return null ;
	}
	
	public String alipayLogin() throws Exception {
		String alipayLoginServer = DefaultUriService.getFactoryURI(URIConstant.ALIPAY_LOGIN_SERVER) ;
		Map<String,String> sParam = new HashMap<String,String>() ;
		sParam.put("service" , "alipay.auth.authorize");
		sParam.put("target_service" , "user.auth.quick.login");
		sParam.put("partner" , EnvPropertiesUtil.getProperty(EnvConstant.CORNER_ALIPAY_PARTNER_ID));
		
		int port = Integer.valueOf(EnvPropertiesUtil.getProperty(EnvConstant.CORNER_PORT));
		if(port != 80){
			sParam.put("return_url" , "http://" + EnvPropertiesUtil.getProperty(EnvConstant.CORNER_DDZ_DOMAINNAME) + ":" + EnvPropertiesUtil.getProperty(EnvConstant.CORNER_PORT) + "/zhe/alipaylogin_pass.htm");
		} else {
			sParam.put("return_url" , "http://" + EnvPropertiesUtil.getProperty(EnvConstant.CORNER_DDZ_DOMAINNAME) + "/zhe/alipaylogin_pass.htm");
		}
		sParam.put("_input_charset" , EnvPropertiesUtil.getProperty(EnvConstant.CORNER_ALIPAY_CHARSET));
		
		
		sParam.put("sign" , AlipayCore.buildMysign(sParam));
		sParam.put("sign_type" , EnvPropertiesUtil.getProperty(EnvConstant.CORNER_ALIPAY_SIGNTYPE));
		
		Set<Map.Entry<String, String>> entrySet = sParam.entrySet() ;
		
		StringBuilder queryStr = new StringBuilder() ;
		
		for(Map.Entry<String, String> entry : entrySet){
			String key = entry.getKey() ;
			String value = entry.getValue() ;
			queryStr.append(key).append("=").append(value).append("&") ;
		}
		
		String qs = queryStr.toString() ;
		
		redirect(alipayLoginServer + "?" + qs) ;
		return null ;
 	}
	
	public String doLogout() {
	    ddzSessionOperator.unload();
        return DDZ_INDEX;
    }
}
