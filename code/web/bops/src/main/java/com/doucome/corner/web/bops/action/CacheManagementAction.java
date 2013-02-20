package com.doucome.corner.web.bops.action;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport;

/**
 * ª∫¥Êπ‹¿Ì
 * @author langben 2012-8-10
 *
 */
@SuppressWarnings("serial")
public class CacheManagementAction extends BopsBasicAction {

	private List<AbstractCacheSupport> cacheClientList ;
	
	private Object cacheObject ;
	
	private String cacheKey ;
	
	private String regionName ;
	
	private String keyParam ;
	
	@Override
	public String execute() throws Exception {
		
		cacheClientList = AbstractCacheSupport.getMgrCacheClientList() ;
		
		if(CollectionUtils.isNotEmpty(cacheClientList) && StringUtils.isNotBlank(cacheKey)) {
			AbstractCacheSupport cacheClient = cacheClientList.iterator().next() ;
			cacheObject = cacheClient.getCacheByKey(cacheKey) ;
		}
		
		return SUCCESS ;
	}

	public List<AbstractCacheSupport> getCacheClientList() {
		return cacheClientList;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public Object getCacheObject() {
		return cacheObject;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getKeyParam() {
		return keyParam;
	}

	public void setKeyParam(String keyParam) {
		this.keyParam = keyParam;
	}
	
	
}
