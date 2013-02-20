package com.doucome.corner.biz.zhe.cache.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.zhe.cache.BrandsCache;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;

public class BrandsCacheImpl extends AbstractCacheSupport implements BrandsCache {

		
	@Override
	public List<TaobaokeShopFacade> getItems() {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs() ;
		InternalStoreItem<List<TaobaokeShopFacade>> store = cc.get(key) ;
		if(store == null) {
			return null ;
		}
		return store.getItem();
	}

	@Override
	public void setCache(List<TaobaokeShopFacade> items) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs() ;
		InternalStoreItem<List<TaobaokeShopFacade>> store = new InternalStoreItem<List<TaobaokeShopFacade>>(items) ;
		cc.put(key, store , ONE_HOUR_MILLISECONDS * 6) ;
	}

	@Override
	public void clear() {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs() ;
		cc.delete(key) ;
	}
	
}
