package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;

/**
 * 类目缓存
 * @author shenjia.caosj 2012-7-28
 *
 */
public interface DcCategoryCache {

	/**
	 * 根据ID获取类目
	 * @param catId
	 * @return
	 */
	DcCategoryDO get(Long catId) ;
	
	/**
	 * 设置
	 * @param cat
	 */
	void set(DcCategoryDO cat) ;
	
	/**
	 * 移除
	 * @param catId
	 */
	void remove(Long catId) ;
}
