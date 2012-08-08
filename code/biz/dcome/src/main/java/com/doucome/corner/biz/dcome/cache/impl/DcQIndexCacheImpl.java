package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcQIndexCache;
import com.doucome.corner.biz.dcome.model.facade.DcSceneQIndexFacade;

public class DcQIndexCacheImpl extends AbstractCacheSupport implements DcQIndexCache {

	@Override
	public void setSceneCache(Long sceneId, DcSceneQIndexFacade item) {
		String key = buildCachekeyWithArgs(sceneId) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcSceneQIndexFacade> store = new InternalStoreItem<DcSceneQIndexFacade>(item) ;
		cc.put(key, store , ONE_MINUTES_MILLISECONDS * 5) ;//5∑÷÷”ª∫¥Ê
	}

	@Override
	public DcSceneQIndexFacade getSceneCache(Long sceneId) {
		String key = buildCachekeyWithArgs(sceneId) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcSceneQIndexFacade> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
		
	}

	@Override
	public void removeSceneCache(Long sceneId) {
		String key = buildCachekeyWithArgs(sceneId) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key) ;
	}

}
