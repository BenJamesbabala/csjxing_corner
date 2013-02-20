package com.doucome.corner.biz.dcome.cache;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;

/**
 * 
 * @author langben 2012-11-8
 *
 */
public interface DcPromotionSnapInfoCache {

	/**
	 * 
	 * @param promotionId
	 * @param start
	 * @param size
	 */
	void setCache(Long promotionId , int start , int size ,  List<DcPromotionSnapInfoDTO> list) ;
	
	/**
	 * 
	 * @param promotionId
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionSnapInfoDTO> getCache(Long promotionId , int start , int size) ;
	
	/**
	 * 
	 * @param promotionId
	 * @param start
	 * @param size
	 */
	void remove(Long promotionId , int start , int size) ;
}
