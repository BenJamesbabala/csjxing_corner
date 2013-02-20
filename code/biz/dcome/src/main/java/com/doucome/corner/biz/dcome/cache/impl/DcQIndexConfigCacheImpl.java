package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcQIndexConfigCache;
import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;

public class DcQIndexConfigCacheImpl extends AbstractCacheSupport implements DcQIndexConfigCache {

	@Override
	public void set(DcQIndexConfigDTO config) {
		if(config == null || config.getId() == null){
			throw new IllegalArgumentException("config cant be null.") ;
		}
		String key = buildCachekeyWithArgs(config.getId(),config.getPubStatus()) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcQIndexConfigDTO> store = new InternalStoreItem<DcQIndexConfigDTO>(config) ; 
		cc.put(key, store , ONE_DAY_MILLISECONDS ) ; //»º´æyitian
	}

	@Override
	public DcQIndexConfigDTO get(Long sysId, String puStatus) {
		String key = buildCachekeyWithArgs(sysId , puStatus) ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<DcQIndexConfigDTO> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}

	@Override
	public void remove(Long sysId,String puStatus) {
		String key = buildCachekeyWithArgs(sysId,puStatus) ;
		CacheClient cc = getCacheClient() ;
		cc.delete(key);
	}

}
