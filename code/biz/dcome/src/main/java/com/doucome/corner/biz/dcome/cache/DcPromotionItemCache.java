package com.doucome.corner.biz.dcome.cache;

import java.util.Map;

import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;

/**
 * 活动商品缓存
 * @author langben 2012-8-13
 *
 */
public interface DcPromotionItemCache {
	
	/**
	 * 设置缓存
	 * @param item
	 */
	void set(DcPromotionItemDTO item) ;
	
	/**
	 * 取缓存
	 * @param promItemId
	 * @return
	 */
	DcPromotionItemDTO get(Long promItemId) ;
	
	/**
	 * 移除
	 * @param condition
	 * @param pagination
	 */
	void remove(Long promItemId);
	/**
	 * 缓存排名前N的promitemid
	 * @param promItems
	 */
	void cachePromRanks(Long promotionId, Map<Long, Integer> ranks);
	/**
	 * 
	 * @param promotionId
	 * @return
	 */
	Map<Long, Integer> getPromRanks(Long promotionId);
}
