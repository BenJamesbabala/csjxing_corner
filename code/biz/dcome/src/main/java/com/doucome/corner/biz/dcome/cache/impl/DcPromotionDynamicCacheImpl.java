package com.doucome.corner.biz.dcome.cache.impl;

import java.util.List;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcPromotionDynamicCache;
import com.doucome.corner.biz.dcome.model.DcPromotionDynamicDTO;

public class DcPromotionDynamicCacheImpl extends AbstractCacheSupport implements DcPromotionDynamicCache {

	@Override
	public List<DcPromotionDynamicDTO> getCache(Long promotionId) {
		String key = buildCachekeyWithArgs(promotionId) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<DcPromotionDynamicDTO>> store = cc.get(key) ;
		if(store == null){
			return null ;
		}
		
		return store.getItem() ;
	}

	@Override
	public void setCache(Long promotionId, List<DcPromotionDynamicDTO> dynamicList) {
		String key = buildCachekeyWithArgs(promotionId) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<DcPromotionDynamicDTO>> store = new InternalStoreItem<List<DcPromotionDynamicDTO>>(dynamicList) ;
		cc.put(key, store , ONE_MONTH_MILLISECONDS );
	}

	@Override
	public void remove(Long promotionId) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(promotionId) ;
		cc.delete(key) ;
	}

}
