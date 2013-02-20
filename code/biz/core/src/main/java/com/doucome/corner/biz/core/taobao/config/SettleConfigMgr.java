package com.doucome.corner.biz.core.taobao.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * @author langben 2013-1-6
 *
 */
public class SettleConfigMgr implements InitializingBean {
	
	public static final String DDZ = "ddzSettleConfig";
	
	public static final String DCOME = "dcTaobaoSettleConfig" ; 
	
	private static SettleConfigMgr _this ;

	private Map<String,SettleConfig> configMap = new HashMap<String,SettleConfig>() ;
	
	public static SettleConfig get(String key) {
		return _this.configMap.get(key) ;
	}

	public void setConfigMap(Map<String, SettleConfig> configMap) {
		this.configMap = configMap;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		_this = this  ;
	}
}
