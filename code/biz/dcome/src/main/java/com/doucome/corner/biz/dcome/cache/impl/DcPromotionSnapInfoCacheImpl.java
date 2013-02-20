package com.doucome.corner.biz.dcome.cache.impl;

import java.util.List;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcPromotionSnapInfoCache;
import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;

public class DcPromotionSnapInfoCacheImpl extends AbstractCacheSupport implements DcPromotionSnapInfoCache {

	@Override
	public void setCache(Long promotionId, int start, int size , List<DcPromotionSnapInfoDTO> list) {
		if(IDUtils.isNotCorrect(promotionId)){
			throw new IllegalArgumentException("promotionId cant be null.") ;
		}
		String key = buildCachekeyWithArgs(promotionId , start , size) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<DcPromotionSnapInfoDTO>> store = new InternalStoreItem<List<DcPromotionSnapInfoDTO>>(list) ;
		cc.put(key, store , ONE_HOUR_MILLISECONDS) ; //»º´æ1Ð¡Ê±
	}

	@Override
	public List<DcPromotionSnapInfoDTO> getCache(Long promotionId, int start, int size) {
		if(IDUtils.isNotCorrect(promotionId)){
			throw new IllegalArgumentException("promotionId cant be null.") ;
		}
		String key = buildCachekeyWithArgs(promotionId , start , size) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<DcPromotionSnapInfoDTO>> store = cc.get(key) ;
		if(store == null){
			return null ;
		}
		List<DcPromotionSnapInfoDTO> list = store.getItem() ;
		return list ;
	}

	@Override
	public void remove(Long promotionId, int start, int size) {
		if(IDUtils.isNotCorrect(promotionId)){
			throw new IllegalArgumentException("promotionId cant be null.") ;
		}
		String key = buildCachekeyWithArgs(promotionId , start , size) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key) ;
	}

}
