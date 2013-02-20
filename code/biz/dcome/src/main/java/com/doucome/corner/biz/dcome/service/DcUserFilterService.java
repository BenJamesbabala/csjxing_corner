package com.doucome.corner.biz.dcome.service;

import java.util.List;

/**
 * 
 * @author ze2200
 *
 */
public interface DcUserFilterService {
	/**
	 * �Ƿ��ǰ������û�.
	 * @param userId
	 * @return
	 */
	boolean isWhiteListUser(Long userId);
	/**
	 * �û��Ƿ�����Ϣ��������.
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
