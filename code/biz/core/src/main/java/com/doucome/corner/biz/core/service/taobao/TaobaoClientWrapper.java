package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

public class TaobaoClientWrapper implements InitializingBean{

	/**
	 * ·þÎñURL
	 */
	private String serverUrl ;
	
	/**
	 * app key
	 */
	private String appKeys ;
	
	/**
	 * app secret
	 */
	private String appSecrets ;
	
	/**
	 * session
	 */
	private String topSessions ;
	
	private List<String> appKeyList ;
	
	private List<String> appSecretList ;
	
	private List<String> topSessionList ;
	
	private int index = 0 ;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		appKeyList = ArrayStringUtils.toList(appKeys) ;
		appSecretList = ArrayStringUtils.toList(appSecrets) ;
		if(CollectionUtils.isEmpty(appKeyList) || CollectionUtils.isEmpty(appSecretList)){
			throw new IllegalArgumentException("appKeyList or secretList cant be empty!") ;
		}
		
		int appKeyListSize = appKeyList.size() ;
		int secretListSize = appSecretList.size() ;
		if(appKeyListSize != secretListSize){
			throw new IllegalArgumentException("appKeyList size not equal secretList size !") ;
		}
		
		topSessionList = ArrayStringUtils.toList(topSessions) ;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public TaobaoClient newClient(){
		TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKeyList.get(index), appSecretList.get(index)) ;
		if(appKeyList.size() > 1){
			int nextIndex = index + 1 ;
			if(nextIndex >= appKeyList.size()){
				nextIndex = 0 ;
			}
			index = nextIndex ;
		}
		return client ;
	}
	
	public TaobaoClient newDefaultClient(){
		TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKeyList.get(0), appSecretList.get(0)) ;
		return client ;
	}
	
	public String defaultSession() {
		if(CollectionUtils.isEmpty(topSessionList)){
			return null ;
		}
		return topSessionList.get(0) ;
	}
	
	/**
	 * ----------------------------------------------------
	 */
	

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getAppKeys() {
		return appKeys;
	}

	public void setAppKeys(String appKeys) {
		this.appKeys = appKeys;
	}

	public String getAppSecrets() {
		return appSecrets;
	}

	public void setAppSecrets(String appSecrets) {
		this.appSecrets = appSecrets;
	}

	public String getTopSessions() {
		return topSessions;
	}

	public void setTopSessions(String topSessions) {
		this.topSessions = topSessions;
	}



	

	
	
}
