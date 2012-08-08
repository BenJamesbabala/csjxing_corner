package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;
import com.doucome.corner.biz.dcome.cache.DcCategoryCache;

public class DcCategoryCacheImpl extends AbstractCacheSupport implements DcCategoryCache {

	@Override
	public DcCategoryDO get(Long catId) {
		String key = buildCachekeyWithArgs(catId) ;
		CacheClient cc = getCacheClient() ;
		return cc.get(key) ;
	}
 
	@Override
	public void set(DcCategoryDO cat) {
		if(cat == null){
			return ;
		}
		String key = buildCachekeyWithArgs(cat.getId()) ;
		CacheClient cc = getCacheClient() ;
		cc.put(key, cat , ONE_MINUTES_MILLISECONDS * 10) ;
	}

	@Override
	public void remove(Long catId) {
		String key = buildCachekeyWithArgs(catId) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key);
	}

}
