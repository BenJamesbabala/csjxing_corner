package com.doucome.corner.biz.dcome.cache.impl;

import java.util.List;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcWinnerGameConfigCache;
import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;

public class DcWinnerGameConfigCacheImpl extends AbstractCacheSupport implements DcWinnerGameConfigCache{

	@Override
	public List<DcWinnerGameConfigDTO> get() {
		String key = buildCachekeyWithArgs() ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<DcWinnerGameConfigDTO>> store = cc.get(key) ;
		if(store == null) {
			return null ;
		}
		
		return store.getItem() ;
	}

	@Override
	public void set(List<DcWinnerGameConfigDTO> configList) {
		String key = buildCachekeyWithArgs() ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<DcWinnerGameConfigDTO>> store = new InternalStoreItem<List<DcWinnerGameConfigDTO>>(configList) ;
		cc.put(key, store , ONE_HOUR_MILLISECONDS );
	}

}
