package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.facade.DcSceneQIndexFacade;

/**
 * Q�ռ���ҳ
 * @author shenjia.caosj 2012-7-28
 *
 */
public interface DcQIndexCache {

	/**
	 * ���û���
	 * @param sceneId
	 * @param item
	 */
	void setSceneCache(Long sceneId , DcSceneQIndexFacade item) ;
	
	/**
	 * ȡ����
	 * @param sceneId
	 * @return
	 */
	DcSceneQIndexFacade getSceneCache(Long sceneId) ;
	
	/**
	 * remove
	 * @param sceneId
	 */
	void removeSceneCache(Long sceneId) ;
}
