package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcSceneDTO;

/**
 * ��������
 * @author shenjia.caosj 2012-7-23
 *
 */
public interface DcSceneCache {

	/**
	 * ��ȡ����
	 * @return
	 */
	DcSceneDTO getCache(Long sceneId) ;
	
	/**
	 * ���û���
	 * @param itemList
	 */
	void setCache(DcSceneDTO scene) ;
	
	/**
	 * ��ջ���
	 */
	void remove(Long sceneId) ;
}
