package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcSceneCache;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;

public class DcSceneCacheImpl extends AbstractCacheSupport implements DcSceneCache{

	@Override
	public DcSceneDTO getCache(Long sceneId) {
		String key = buildCachekeyWithArgs(sceneId) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcSceneDTO> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}

	@Override
	public void setCache(DcSceneDTO scene) {
		if(scene == null || scene.getId() == null){
			throw new IllegalArgumentException("scene cant be null.") ;
		}
		String key = buildCachekeyWithArgs(scene.getId()) ;
		CacheClient cc = getCacheClient();
		cc.put(key, new InternalStoreItem<DcSceneDTO>(scene) , ONE_MINUTES_MILLISECONDS ) ; //1∑÷÷”ª∫¥Ê
	}

	@Override
	public void remove(Long sceneId) {
		String key = buildCachekeyWithArgs(sceneId) ;
		CacheClient cc = getCacheClient();
		cc.delete(key) ;
	}

}
