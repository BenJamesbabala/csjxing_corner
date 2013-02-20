package com.doucome.corner.biz.dcome.cache.impl;

import java.util.ArrayList;
import java.util.List;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.dcome.cache.DcMessageCache;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;

public class DcMessageCacheImpl extends AbstractCacheSupport implements DcMessageCache {
	@Override
	public boolean addMessageCache(Long id, DcMessageDTO message) {
		List<DcMessageDTO> messages = getMessages(id);
		if (message == null) {
			messages = new ArrayList<DcMessageDTO>();
		}
		messages.add(message);
		return cacheMessagesTenMinute(id, messages);
	}
	
//	@Override
	private boolean cacheMessages(Long id, List<DcMessageDTO> messages, Long millTime) {
		CacheClient client = getCacheClient();
		String key = buildCachekeyWithArgs(id);
		InternalStoreItem<List<DcMessageDTO>> storeItem = new InternalStoreItem<List<DcMessageDTO>>(messages);
		return client.put(key, storeItem, millTime);
	}
	
	@Override
	public boolean cacheMessagesTenMinute(Long id, List<DcMessageDTO> messages) {
		return cacheMessages(id, messages, TEN_MINUTES_MILLISECONDS);
	}

	@Override
	public boolean cacheMessagesOneHour(Long id, List<DcMessageDTO> messages) {
		return cacheMessages(id, messages, ONE_HOUR_MILLISECONDS);
	}
	
	@Override
	public List<DcMessageDTO> getMessages(Long id) {
		CacheClient client = getCacheClient();
		String key = buildCachekeyWithArgs(id);
		InternalStoreItem<List<DcMessageDTO>> storeItem = client.get(key);
		if (storeItem == null) {
			return null;
		}
		return storeItem.getItem();
	}
}
