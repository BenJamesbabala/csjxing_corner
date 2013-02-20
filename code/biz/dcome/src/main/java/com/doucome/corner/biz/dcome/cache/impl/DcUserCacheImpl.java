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
import com.doucome.corner.biz.dcome.cache.DcUserCache;
import com.doucome.corner.biz.dcome.model.DcUserDTO;

public class DcUserCacheImpl extends AbstractCacheSupport implements DcUserCache  {

	@Override
	public DcUserDTO get(Long userId) {
		String key = buildCachekeyWithArgs(userId) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcUserDTO> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}
	
	@Override
	public Map<Long, DcUserDTO> getCacheMap(List<Long> userIds) {
		if(CollectionUtils.isEmpty(userIds)){
			return new HashMap<Long, DcUserDTO>();
		}
		CacheClient cc = getCacheClient() ;
		List<String> keyList = new ArrayList<String>() ;
		for(Long id : userIds){
			String key = buildCachekeyWithArgs(id) ;
			keyList.add(key) ;
		}
		Map<String,Object> storeMap = cc.getBulk(keyList) ;
		if(MapUtils.isEmpty(storeMap)){
			return null ;
		}
		Map<Long, DcUserDTO> returnMap = new HashMap<Long,DcUserDTO>() ;
		Set<String> keySet = storeMap.keySet() ;
		for(String key : keySet){
			InternalStoreItem<DcUserDTO> store = (InternalStoreItem<DcUserDTO>) storeMap.get(key) ;
			if(store != null){
				DcUserDTO userDTO = store.getItem() ;
				returnMap.put(userDTO.getUserId(), userDTO) ;
			}
		}
		return returnMap ;
	}

	@Override
	public boolean set(DcUserDTO user) {
		if(user == null || user.getUserId() == null){
			throw new IllegalArgumentException("user cant be null.") ;
		}
		String key = buildCachekeyWithArgs(user.getUserId()) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcUserDTO> store = new InternalStoreItem<DcUserDTO>(user) ; 
		return cc.put(key, store , ONE_HOUR_MILLISECONDS) ; //»º´æyitian
	}

	@Override
	public void remove(Long userId) {
		String key = buildCachekeyWithArgs(userId) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key) ;
	}

}
