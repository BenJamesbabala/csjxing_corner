package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcSceneDTO;

/**
 * 场景缓存
 * @author langben 2012-7-23
 *
 */
public interface DcSceneCache {

	/**
	 * 获取缓存(包括detail)
	 * @return
	 */
	DcSceneDTO get(Long sceneId) ;
	
	/**
	 * 设置缓存(包括detail)
	 * @param itemList
	 */
	void set(DcSceneDTO scene) ;
	
	/**
	 * 清空缓存
	 */
	void remove(Long sceneId) ;
}
