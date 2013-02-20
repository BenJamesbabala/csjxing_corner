package com.doucome.corner.web.bops.action;

import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.web.bops.model.JsonModel;

@SuppressWarnings("serial")
public class CacheOperateAjaxAction extends BopsBasicAction {

	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;

	private String cacheKey ;
		
	public String delete() throws Exception {
		
		if(StringUtils.isBlank(cacheKey)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("cacheKey  cant be blank.") ;
			return SUCCESS ;
		}
		
		AbstractCacheSupport cacheClient = AbstractCacheSupport.getMgrCacheClientList().iterator().next();
		
		boolean delResult = cacheClient.removeCacheByKey(cacheKey) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(delResult) ;
		
		return SUCCESS ;
	}
	
	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	
	
}
