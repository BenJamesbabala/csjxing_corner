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
		String key = buildCachekeyWithArgs("") ;
		InternalStoreItem<List<TaobaokeShopFacade>> store = cc.get(key) ;
		if(isExpire(store)) {
			return null ;
		}
		return store.getItem();
	}

	@Override
	public void setCache(List<TaobaokeShopFacade> items) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		InternalStoreItem<List<TaobaokeShopFacade>> store = new InternalStoreItem<List<TaobaokeShopFacade>>() ;
		store.setGmtCreate(new Date()) ;
		store.setItem(items) ;
		cc.put(key, store) ;
	}

	@Override
	public void clear() {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		cc.delete(key) ;
	}
	
	private boolean isExpire(InternalStoreItem<List<TaobaokeShopFacade>> store){
		if(store == null){
			return true ;
		}
		
		Date gmtCreate = store.getGmtCreate() ;
		if(gmtCreate == null){
			return true ;
		}
		
		Date now = new Date() ;
		
		if(DateUtils.isSameDay(gmtCreate, now)){
			return false ;
		}
		
		return true ;
	}

}
