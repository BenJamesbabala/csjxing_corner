package com.doucome.corner.biz.dcome.cache;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.dcome.model.DcCategoryDTO;

/**
 * 类目缓存
 * @author langben 2012-7-28
 *
 */
public interface DcCategoryCache {

	/**
	 * 根据ID获取类目
	 * @param catId
	 * @return
	 */
	DcCategoryDTO get(Long catId) ;
	
	/**
	 * 批量获取Category
	 * @param idList
	 * @return
	 */
	Map<Long,DcCategoryDTO> getCacheMap(List<Long> idList) ;
	
	/**
	 * 设置
	 * @param cat
	 */
	void set(DcCategoryDTO cat) ;
	
	/**
	 * 移除
	 * @param catId
	 */
	void remove(Long catId) ;
}
