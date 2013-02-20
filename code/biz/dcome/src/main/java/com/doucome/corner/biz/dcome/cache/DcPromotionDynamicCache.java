package com.doucome.corner.biz.dcome.cache;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcPromotionDynamicDTO;

/**
 * 活动首页动态
 * @author langben 2012-8-29
 *
 */
public interface DcPromotionDynamicCache {

	/**
	 * 
	 * @return
	 */
	List<DcPromotionDynamicDTO> getCache(Long promotionId) ;
	
	/**
	 * 
	 * @param promotionId
	 * @param dynamic
	 */
	void setCache(Long promotionId, List<DcPromotionDynamicDTO> dynamicList) ;

	/**
	 * 
	 * @param promotionId
	 */
	void remove(Long promotionId) ;
}
