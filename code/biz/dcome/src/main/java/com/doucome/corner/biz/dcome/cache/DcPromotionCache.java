package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcPromotionDTO;

/**
 * 活动缓存
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionCache {
	
	/**
	 * 获取当前活动
	 * @return
	 */
	DcPromotionDTO getCurrentPromotion() ;
	
	/**
	 * 设置当前活动缓存
	 * @param item
	 */
	void setCurrentPromotion(DcPromotionDTO item) ;

	/**
	 * 根据ID取
	 * @param id
	 * @return
	 */
	DcPromotionDTO get(Long id) ;
	
	/**
	 * 设置缓存
	 * @param item
	 */
	void set(DcPromotionDTO item) ;
	
	/**
	 * 清除缓存
	 * @param id
	 */
	void remove(Long id) ;
}
