package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcItemDTO;

/**
 * ��Ʒ����
 * @author shenjia.caosj 2012-7-29
 *
 */
public interface DcItemCache {

	/**
	 * ����IDȡ��Ʒ
	 * @param id
	 * @return
	 */
	DcItemDTO getCache(Long id) ;
	
	/**
	 * ���û���
	 * @param item
	 */
	void setCache(DcItemDTO item) ;
	
	/**
	 * �������
	 * @param id
	 */
	void remove(Long id) ;
}
