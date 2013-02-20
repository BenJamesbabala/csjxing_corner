package com.doucome.corner.biz.dcome.cache.impl;

import java.util.List;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcCommentCache;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;

public class DcCommentCacheImpl extends AbstractCacheSupport implements DcCommentCache {

	@Override
	public List<DcCommentDTO> getFrontComments(long itemId, int pageSize) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(itemId , pageSize) ;
		InternalStoreItem<List<DcCommentDTO>> store = cc.get(key);
		if(store == null){
			return null ;
		}
		return store.getItem() ;
	}

	@Override
	public void setFrontComments(long itemId, int pageSize,List<DcCommentDTO> comments) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(itemId , pageSize) ;
		InternalStoreItem<List<DcCommentDTO>> store = new InternalStoreItem<List<DcCommentDTO>>(comments) ;
		cc.put(key, store , ONE_HOUR_MILLISECONDS) ;
	}

	@Override
	public void removeFrontComments(long itemId, int pageSize) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(itemId , pageSize) ;
		cc.delete(key) ;
	}

}
