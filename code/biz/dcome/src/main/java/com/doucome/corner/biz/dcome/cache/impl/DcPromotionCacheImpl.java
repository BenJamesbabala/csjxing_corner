package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcPromotionCache;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;

public class DcPromotionCacheImpl extends AbstractCacheSupport implements DcPromotionCache {
	
	@Override
	public DcPromotionDTO getCurrentPromotion() {
		String key = buildCachekeyWithArgs("current") ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcPromotionDTO> store = cc.get(key);
		if(store == null){
			return null;
		}
		DcPromotionDTO dto = store.getItem();
		if(dto.isOnGoing()){
			return dto;
		}
		removeCurrentPromotion();
		return null ;
	}

	@Override
	public void setCurrentPromotion(DcPromotionDTO item) {
		if(item == null || IDUtils.isNotCorrect(item.getId())){
			throw new IllegalArgumentException("item cant be null.") ;
		}
		String key = buildCachekeyWithArgs("current") ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcPromotionDTO> store = new InternalStoreItem<DcPromotionDTO>(item) ; 
		cc.put(key, store , ONE_HOUR_MILLISECONDS * 12 ) ; //缓存1天
	}
	
	/**
	 * 移除当前活动缓存.
	 */
	private void removeCurrentPromotion() {
		String key = buildCachekeyWithArgs("current") ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key);
	}

	@Override
	public DcPromotionDTO get(Long id) {
		String key = buildCachekeyWithArgs(id) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcPromotionDTO> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}

	@Override
	public void set(DcPromotionDTO item) {
		if(item == null || IDUtils.isNotCorrect(item.getId())){
			throw new IllegalArgumentException("item cant be null.") ;
		}
		String key = buildCachekeyWithArgs(item.getId()) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcPromotionDTO> store = new InternalStoreItem<DcPromotionDTO>(item) ; 
		cc.put(key, store , ONE_DAY_MILLISECONDS ) ; //缓存1天
	}

	@Override
	public void remove(Long id) {
		String key = buildCachekeyWithArgs(id) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key);
	}

}
