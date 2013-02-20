package com.doucome.corner.biz.core.cache;

import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

/**
 * banner
 * @author langben 2012-9-27
 *
 */
public interface BannerConfigCache {

	/**
	 * 
	 * @param bannerId
	 * @param banner
	 */
	void setCache(BannerConfigDO banner) ;
	
	/**
	 * 
	 * @param bannerId
	 * @return
	 */
	BannerConfigDO getCache(String bannerId) ;
	
	/**
	 * 
	 * @param bannerId
	 */
	void remove(String bannerId) ;
}
