package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcSceneDTO;

/**
 * ≥°æ∞ª∫¥Ê
 * @author shenjia.caosj 2012-7-23
 *
 */
public interface DcSceneCache {

	/**
	 * ªÒ»°ª∫¥Ê
	 * @return
	 */
	DcSceneDTO getCache(Long sceneId) ;
	
	/**
	 * …Ë÷√ª∫¥Ê
	 * @param itemList
	 */
	void setCache(DcSceneDTO scene) ;
	
	/**
	 * «Âø’ª∫¥Ê
	 */
	void remove(Long sceneId) ;
}
