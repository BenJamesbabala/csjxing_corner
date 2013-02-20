package com.doucome.corner.biz.dcome.cache.impl;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcAuctionItemCache;
import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;

public class DcAuctionItemCacheImpl extends AbstractCacheSupport implements DcAuctionItemCache {
	
	@Override
	public DcAuctionItemDTO getAuctionItem(Long id) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(id) ;
		InternalStoreItem<DcAuctionItemDTO> store = cc.get(key);
		if (store != null) {
			return store.getItem();
		}
		return null;
	}
	
	@Override
	public boolean cacheAuctionItem(DcAuctionItemDTO auctionItem) {
		CacheClient cc = getCacheClient() ;
		String key = buildCachekeyWithArgs(auctionItem.getId()) ;
		InternalStoreItem<DcAuctionItemDTO> store = new InternalStoreItem<DcAuctionItemDTO>(auctionItem) ;
		cc.put(key, store , ONE_HOUR_MILLISECONDS) ;
		return true;
	}
	
	@Override
	public boolean removeAuctionItem(Long id) {
		CacheClient cc = getCacheClient();
		String key = buildCachekeyWithArgs(id);
		cc.delete(key);
		return true;
	}
}
