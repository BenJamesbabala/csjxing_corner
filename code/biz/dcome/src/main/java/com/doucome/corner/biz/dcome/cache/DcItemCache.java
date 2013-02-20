package com.doucome.corner.biz.dcome.cache;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.core.exception.CacheFailedException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;

/**
 * ��Ʒ����
 * @author langben 2012-7-29
 *
 */
public interface DcItemCache {

	/**
	 * ����IDȡ��Ʒ
	 * @param id
	 * @return
	 */
	DcItemDTO get(Long id) ;
	
	/**
	 * ������ȡItem
	 * @param idList
	 * @return
	 */
	Map<Long,DcItemDTO> getCacheMap(List<Long> idList) ;
	
	/**
	 * ���û���
	 * @param item
	 */
	void set(DcItemDTO item) ;
	
	/**
	 * �������
	 * @param id
	 */
	void remove(Long id) ;
	
	/**
	 * ��������, �����������, ��ǰ�µ�ֵ��������Ч
	 * @param userId �û�id.
	 * @param tbItemId ��Ʒ���Ա�����id.
	 * @param expireTime ʧЧʱ��.
	 * @return ���������Ƿ�ɹ�.
	 */
	boolean putIfAbsent(Long userId, String tbItemId, long expireTime) throws CacheFailedException;
}
