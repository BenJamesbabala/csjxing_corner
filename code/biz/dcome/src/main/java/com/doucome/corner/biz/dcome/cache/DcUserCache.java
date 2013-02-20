package com.doucome.corner.biz.dcome.cache;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.dcome.model.DcUserDTO;

/**
 * 用户缓存
 * @author langben 2012-8-9
 *
 */
public interface DcUserCache {

	/**
	 * 获取User
	 * @param userId
	 * @return
	 */
	DcUserDTO get(Long userId) ;
	
	/**
	 * 设置
	 * @param user
	 */
	boolean set(DcUserDTO user) ;
	
	/**
	 * 移除
	 * @param userId
	 */
	void remove(Long userId);
	
	Map<Long, DcUserDTO> getCacheMap(List<Long> userIds);
}
