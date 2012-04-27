package com.doucome.corner.web.common.toolbox;



import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.constant.EnvConstant;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.EnvPropertiesUtil;


/**
 * EnvPropertiesToolbox velocity toolbox
 * @author shenjia.caosj 2011-12-23
 *
 */
public class EnvPropertiesToolbox {
	
	/**
	 * getProperty
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		return EnvPropertiesUtil.getProperty(key) ;
	}
	
	public String getProp(String key){
		return getProperty(key) ;
	}
	
	public boolean isProduction(){
		String production = EnvPropertiesUtil.getProperty(EnvConstant.CORNER_PRODUCTION) ;
		if(StringUtils.equals(production, "true")){
			return true ;
		}
		return false ;
	}
	
	public String getDdzRoot(){
		return DefaultUriService.getFactoryURI(URIConstant.DDZ_SERVER) ;
	}
	
	public String getDdzStaticroot(){
		return DefaultUriService.getFactoryURI(URIConstant.DDZ_STATIC_SERVER) ;
	}
	
	public String getSurlRoot(){
		return DefaultUriService.getFactoryURI(URIConstant.SURL_SERVER) ;
	}
	
	public String getBopsRoot(){
		return DefaultUriService.getFactoryURI(URIConstant.DDZ_SERVER) ;
	}
		
}
