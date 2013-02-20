package com.doucome.corner.biz.dcome.cache;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcMessageDTO;

/**
 * 
 * @author ze2200
 *
 */
public interface DcMessageCache {
	/**
	 * 
	 * @param id
	 * @param message
	 * @return
	 */
	boolean addMessageCache(Long id, DcMessageDTO message);
	/**
	 * ������Ϣ. id = 0l��ʾ��ϵͳ��Ϣ.
	 * @param userDynamic
	 * @return
	 */
	boolean cacheMessagesTenMinute(Long id, List<DcMessageDTO> messages);
	/**
	 * 
	 * @param id
	 * @param messages
	 * @return
	 */
	boolean cacheMessagesOneHour(Long id, List<DcMessageDTO> messages);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<DcMessageDTO> getMessages(Long userId);
}
