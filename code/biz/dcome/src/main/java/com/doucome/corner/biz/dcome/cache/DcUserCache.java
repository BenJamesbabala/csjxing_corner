package com.doucome.corner.biz.dcome.cache;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.dcome.model.DcUserDTO;

/**
 * �û�����
 * @author langben 2012-8-9
 *
 */
public interface DcUserCache {

	/**
	 * ��ȡUser
	 * @param userId
	 * @return
	 */
	DcUserDTO get(Long userId) ;
	
	/**
	 * ����
	 * @param user
	 */
	boolean set(DcUserDTO user) ;
	
	/**
	 * �Ƴ�
	 * @param userId
	 */
	void remove(Long userId);
	
	Map<Long, DcUserDTO> getCacheMap(List<Long> userIds);
}
