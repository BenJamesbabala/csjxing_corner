package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcSceneDTO;

/**
 * ��������
 * @author langben 2012-7-23
 *
 */
public interface DcSceneCache {

	/**
	 * ��ȡ����(����detail)
	 * @return
	 */
	DcSceneDTO get(Long sceneId) ;
	
	/**
	 * ���û���(����detail)
	 * @param itemList
	 */
	void set(DcSceneDTO scene) ;
	
	/**
	 * ��ջ���
	 */
	void remove(Long sceneId) ;
}
