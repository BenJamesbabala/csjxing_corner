package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;

/**
 * 美丽帮我选首页的缓存
 * @author langben 2012-8-9
 *
 */
public interface DcQIndexConfigCache {

	/**
	 * 设置缓存
	 * @param config
	 */
	void set(DcQIndexConfigDTO config) ;
	
	/**
	 * 获取
	 * @param sysId
	 * @return
	 */
	DcQIndexConfigDTO get(Long sysId , String puStatus) ;
	
	/**
	 * 移除
	 * @param sysId
	 */
	void remove(Long sysId,String puStatus) ;
}
