package com.doucome.corner.biz.zhe.cache.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.zhe.cache.BuyingRecommendItemCache;
import com.doucome.corner.biz.zhe.model.TaobaokeReportFacade;

public class BuyingRecommendItemCacheImpl extends AbstractCacheSupport implements BuyingRecommendItemCache {

	
	
	@Override
	public void setCache(List<TaobaokeReportFacade> items) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		InternalStoreItem<List<TaobaokeReportFacade>> store = new InternalStoreItem<List<TaobaokeReportFacade>>() ; 
		store.setGmtCreate(new Date()) ;
		store.setItem(items) ;
		
		cc.put(key, store) ;
	}

	@Override
	public List<TaobaokeReportFacade> getItems() {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		InternalStoreItem<List<TaobaokeReportFacade>> store = cc.get(key) ;
		if(isExpire(store)) {
			return null ;
		}
		return store.getItem();
	}
	
	/**
	 * »º´æÊÇ·ñ¹ýÆÚ
	 * @param internalItem
	 * @return
	 */
	public boolean isExpire(InternalStoreItem<List<TaobaokeReportFacade>> store){
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

	@Override
	public void clear() {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		cc.delete(key) ;
	}

}
