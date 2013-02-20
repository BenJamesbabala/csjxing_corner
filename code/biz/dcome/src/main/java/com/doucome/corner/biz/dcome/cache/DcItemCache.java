package com.doucome.corner.biz.dcome.cache;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.core.exception.CacheFailedException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;

/**
 * 商品缓存
 * @author langben 2012-7-29
 *
 */
public interface DcItemCache {

	/**
	 * 根据ID取商品
	 * @param id
	 * @return
	 */
	DcItemDTO get(Long id) ;
	
	/**
	 * 批量获取Item
	 * @param idList
	 * @return
	 */
	Map<Long,DcItemDTO> getCacheMap(List<Long> idList) ;
	
	/**
	 * 设置缓存
	 * @param item
	 */
	void set(DcItemDTO item) ;
	
	/**
	 * 清除缓存
	 * @param id
	 */
	void remove(Long id) ;
	
	/**
	 * 缓存数据, 如果已有数据, 当前新的值将不能生效
	 * @param userId 用户id.
	 * @param tbItemId 商品的淘宝数字id.
	 * @param expireTime 失效时间.
	 * @return 缓存数据是否成功.
	 */
	boolean putIfAbsent(Long userId, String tbItemId, long expireTime) throws CacheFailedException;
}
