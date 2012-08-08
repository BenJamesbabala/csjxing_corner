package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcItemDTO;

/**
 * 商品缓存
 * @author shenjia.caosj 2012-7-29
 *
 */
public interface DcItemCache {

	/**
	 * 根据ID取商品
	 * @param id
	 * @return
	 */
	DcItemDTO getCache(Long id) ;
	
	/**
	 * 设置缓存
	 * @param item
	 */
	void setCache(DcItemDTO item) ;
	
	/**
	 * 清除缓存
	 * @param id
	 */
	void remove(Long id) ;
}
