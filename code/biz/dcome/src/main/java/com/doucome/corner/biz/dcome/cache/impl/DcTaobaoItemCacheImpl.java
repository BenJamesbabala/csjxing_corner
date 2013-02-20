package com.doucome.corner.biz.dcome.cache.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.dcome.cache.DcTaobaoItemCache;

/**
 * Ã‘±¶…Ã∆∑ª∫¥Ê.
 * @author ze2200
 *
 */
public class DcTaobaoItemCacheImpl extends AbstractCacheSupport  implements DcTaobaoItemCache {

	private static final Log logger = LogFactory.getLog(DcTaobaoItemCacheImpl.class);
	
	public static final String TAOBAO_KEY = "TB";
	public static final String TAOKE_KEY = "TK";
	
	@Override
	public boolean putItem(TaobaoItemDTO tbItem) {
		try {
			if (tbItem == null || tbItem.getNumIid() == null) {
				return false;
			}
			String key = buildCachekeyWithArgs(tbItem.getNumIid(), TAOBAO_KEY);
			InternalStoreItem<TaobaoItemDTO> data = new InternalStoreItem<TaobaoItemDTO>(tbItem);
		    getCacheClient().asyncPut(key, data, TEN_MINUTES_MILLISECONDS);
		    return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	@Override
	public boolean putTaokeItem(TaobaokeItemDTO taokeItem, String outCode) {
		try {
			if (taokeItem == null || taokeItem.getNumIid() == null) {
				return false;
			}
			String key = buildCachekeyWithArgs(taokeItem.getNumIid(), outCode, TAOKE_KEY);
			InternalStoreItem<TaobaokeItemDTO> data = new InternalStoreItem<TaobaokeItemDTO>(taokeItem);
			getCacheClient().asyncPut(key, data, TEN_MINUTES_MILLISECONDS);
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	@Override
	public TaobaoItemDTO getItem(String tbItemId) {
		if (StringUtils.isEmpty(tbItemId)) {
			return null;
		}
		String key = buildCachekeyWithArgs(tbItemId, TAOBAO_KEY);
		InternalStoreItem<TaobaoItemDTO> data = getCacheClient().get(key);
		if (data == null) {
			return null;
		}
		return data.getItem();
	}

	@Override
	public TaobaokeItemDTO getTaokeItem(String tbItemId, String outCode) {
		if (StringUtils.isEmpty(tbItemId)) {
			return null;
		}
		String key = buildCachekeyWithArgs(tbItemId, outCode, TAOKE_KEY);
		InternalStoreItem<TaobaokeItemDTO> data = getCacheClient().get(key);
		if (data == null) {
			return null;
		}
		return data.getItem();
	}
}
