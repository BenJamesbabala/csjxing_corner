package com.doucome.corner.biz.zhe.cache.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.doucome.corner.biz.zhe.cache.BuyingRecommendItemCache;

public class BuyingRecommendItemCacheImpl extends AbstractCacheSupport implements BuyingRecommendItemCache {

	
	
	@Override
	public void setCache(List<DdzRecommendDO> items) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		InternalStoreItem<List<DdzRecommendDO>> store = new InternalStoreItem<List<DdzRecommendDO>>() ; 
		store.setGmtCreate(new Date()) ;
		store.setItem(items) ;
		
		cc.put(key, store) ;
	}

	@Override
	public List<DdzRecommendDO> getItems() {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		Object o = cc.get(key) ;
		if(o == null){
			return null ;
		}
		InternalStoreItem<List<DdzRecommendDO>> store = (InternalStoreItem<List<DdzRecommendDO>>)o ;
		//Calendar create = DateUtils.getCalendar(store.getGmtCreate()) ;
		return store.getItem();
	}

	@Override
	public InternalStoreItem<List<DdzRecommendDO>> getInternalItems() {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs("") ;
		return cc.get(key) ;
	}
	
	
	/**
	 * »º´æÊÇ·ñ¹ýÆÚ
	 * @param internalItem
	 * @return
	 */
	public static boolean isCacheExpire(InternalStoreItem<List<DdzRecommendDO>> internalItem){
		if(internalItem == null){
			return true ;
		}
		Date gmtCreate = internalItem.getGmtCreate() ;
		//
		if(gmtCreate == null){
			return true ;
		}
		if(DateUtils.isSameDay(gmtCreate, new Date())){
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
