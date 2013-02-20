package com.doucome.corner.biz.dcome.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcCategoryCache;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;

public class DcCategoryCacheImpl extends AbstractCacheSupport implements DcCategoryCache {

	@Override 
	public DcCategoryDTO get(Long catId) {
		String key = buildCachekeyWithArgs(catId) ;
		CacheClient cc = getCacheClient() ;
		return cc.get(key) ;
	}
	
	@Override
	public Map<Long, DcCategoryDTO> getCacheMap(List<Long> idList) {
		if(CollectionUtils.isEmpty(idList)){
			throw new IllegalArgumentException("idList cant be empty .") ;
		}
		CacheClient cc = getCacheClient() ;
		List<String> keyList = new ArrayList<String>() ;
		for(Long id : idList){
			String key = buildCachekeyWithArgs(id) ;
			keyList.add(key) ;
		}
		Map<String,Object> storeMap = cc.getBulk(keyList) ;
		if(MapUtils.isEmpty(storeMap)){
			return null ;
		}
		Map<Long,DcCategoryDTO> returnMap = new HashMap<Long,DcCategoryDTO>() ;
		Set<String> keySet = storeMap.keySet() ;
		for(String key : keySet){
			DcCategoryDTO store = (DcCategoryDTO)storeMap.get(key) ;
			if(store != null){
				returnMap.put(store.getId(), store) ;
			}
		}
		return returnMap ;
	}
 
	@Override
	public void set(DcCategoryDTO cat) {
		if(cat == null){
			return ;
		}
		String key = buildCachekeyWithArgs(cat.getId()) ;
		CacheClient cc = getCacheClient() ;
		cc.put(key, cat , ONE_DAY_MILLISECONDS) ;
	}

	@Override
	public void remove(Long catId) {
		String key = buildCachekeyWithArgs(catId) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key);
	}

	

}
