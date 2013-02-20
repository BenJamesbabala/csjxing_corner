package com.doucome.corner.biz.dcome.cache.impl;

import java.util.Map;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcPromotionItemCache;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;

public class DcPromotionItemCacheImpl extends AbstractCacheSupport implements DcPromotionItemCache {

	@Override
	public void remove(Long promItemId) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(promItemId) ;
		cc.delete(key) ;
	}


	@Override
	public void set(DcPromotionItemDTO item) {
		if(item == null){
			return ;
		}
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(item.getId()) ;
		InternalStoreItem<DcPromotionItemDTO> store = new InternalStoreItem<DcPromotionItemDTO>(item) ;
		cc.put(key, store , ONE_HOUR_MILLISECONDS );
	}

	@Override
	public DcPromotionItemDTO get(Long promItemId) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(promItemId) ;
		InternalStoreItem<DcPromotionItemDTO> store = cc.get(key) ;
		if(store == null){
			return null ;
		}
		return store .getItem() ;
	}

	@Override
	public void cachePromRanks(Long promotionId, Map<Long, Integer> ranks) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(promotionId);
		InternalStoreItem<Map<Long, Integer>> store = new InternalStoreItem<Map<Long, Integer>>(ranks) ;
		cc.put(key, store , TEN_SECOND_MILLISECONDS);
	}
	
	@Override
	public Map<Long, Integer> getPromRanks(Long promotionId) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(promotionId) ;
		InternalStoreItem<Map<Long, Integer>> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store .getItem() ;
	}
}
