package com.doucome.corner.biz.dcome.service;

import java.util.List;

/**
 * 
 * @author ze2200
 *
 */
public interface DcUserFilterService {
	/**
	 * 是否是白名单用户.
	 * @param userId
	 * @return
	 */
	boolean isWhiteListUser(Long userId);
	/**
	 * 用户是否在消息白名单中.
	 * @param userId 
	 * @return
	 */
	boolean isMessageWhiteListUser(Long userId);
	
	/**
	 * 
	 * @return
	 */
	List<Long> getWhiteListUser() ;
}
