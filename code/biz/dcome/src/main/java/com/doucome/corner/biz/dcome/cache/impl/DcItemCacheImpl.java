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
import com.doucome.corner.biz.core.exception.CacheFailedException;
import com.doucome.corner.biz.dcome.cache.DcItemCache;
import com.doucome.corner.biz.dcome.model.DcItemDTO;

public class DcItemCacheImpl extends AbstractCacheSupport implements DcItemCache {

	@Override
	public DcItemDTO get(Long id) {
		String key = buildCachekeyWithArgs(id) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcItemDTO> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}

	@Override
	public void set(DcItemDTO item) {
		if(item == null || item.getId() == null){
			throw new IllegalArgumentException("item cant be null.") ;
		}
		String key = buildCachekeyWithArgs(item.getId()) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcItemDTO> store = new InternalStoreItem<DcItemDTO>(item) ; 
		cc.put(key, store , ONE_HOUR_MILLISECONDS ) ; //ª∫¥Ê10√Î
		
	}
	
	@Override
	public Map<Long, DcItemDTO> getCacheMap(List<Long> idList) {
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
		Map<Long,DcItemDTO> returnMap = new HashMap<Long,DcItemDTO>() ;
		Set<String> keySet = storeMap.keySet() ;
		for(String key : keySet){
			@SuppressWarnings("unchecked")
			InternalStoreItem<DcItemDTO> store = (InternalStoreItem<DcItemDTO>)storeMap.get(key) ;
			if(store != null){
				DcItemDTO itemDTO = store.getItem() ;
				returnMap.put(itemDTO.getId(), itemDTO) ;
			}
		}
		return returnMap ;
	}

	@Override
	public void remove(Long id) {
		String key = buildCachekeyWithArgs(id) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key);
	}
	
	@Override
	public boolean putIfAbsent(Long userId, String tbItemId, long expireTime) throws CacheFailedException {
		String key = buildCachekeyWithArgs(userId);
		return getCacheClient().putIfAbsent(key, tbItemId, expireTime);
	}
}
