package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcItemCache;
import com.doucome.corner.biz.dcome.model.DcItemDTO;

public class DcItemCacheImpl extends AbstractCacheSupport implements DcItemCache {

	@Override
	public DcItemDTO getCache(Long id) {
		String key = buildCachekeyWithArgs(id) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcItemDTO> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}

	@Override
	public void setCache(DcItemDTO item) {
		if(item == null || item.getId() == null){
			throw new IllegalArgumentException("item cant be null.") ;
		}
		String key = buildCachekeyWithArgs(item.getId()) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcItemDTO> store = new InternalStoreItem<DcItemDTO>(item) ; 
		cc.put(key, store , ONE_SECOND_MILLISECONDS * 10 ) ; //ª∫¥Ê10√Î
	}

	@Override
	public void remove(Long id) {
		String key = buildCachekeyWithArgs(id) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key);
	}

}
