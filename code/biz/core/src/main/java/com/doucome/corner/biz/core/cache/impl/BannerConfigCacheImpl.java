package com.doucome.corner.biz.core.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.core.cache.BannerConfigCache;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

public class BannerConfigCacheImpl extends AbstractCacheSupport implements BannerConfigCache {

	@Override
	public void setCache(BannerConfigDO banner) {
		if (banner == null) {
			return;
		}
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(banner.getBannerId()) ;
		InternalStoreItem<BannerConfigDO> store = new InternalStoreItem<BannerConfigDO>(banner) ;
		cc.put(key, store , ONE_HOUR_MILLISECONDS) ;
	}

	@Override
	public BannerConfigDO getCache(String bannerId) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(bannerId) ;
		InternalStoreItem<BannerConfigDO> store = cc.get(key) ;
		if(store == null) {
			return null ;
		}
		return store.getItem();
	}

	@Override
	public void remove(String bannerId) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(bannerId) ;
		cc.delete(key) ;
	}

}
