package com.doucome.corner.biz.dcome.cache.wryneck;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckTestDTO;

public class WryneckTestCacheImpl extends AbstractCacheSupport implements WryneckTestCache {

	@Override
	public void setCache(List<WryneckTestDTO> list) {
		if(CollectionUtils.isEmpty(list)){
			throw new IllegalArgumentException("list cant be empty.") ;
		}
		String key = buildCachekeyWithArgs() ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<WryneckTestDTO>> store = new InternalStoreItem<List<WryneckTestDTO>>(list) ; 
		cc.put(key, store , ONE_HOUR_MILLISECONDS ) ; //ª∫¥Ê10√Î
	}

	@Override
	public List<WryneckTestDTO> getCache() {
		String key = buildCachekeyWithArgs() ;
		CacheClient cc = getCacheClient() ;
		InternalStoreItem<List<WryneckTestDTO>> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}

}
