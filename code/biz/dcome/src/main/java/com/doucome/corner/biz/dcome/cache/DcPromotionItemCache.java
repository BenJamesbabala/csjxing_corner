package com.doucome.corner.biz.dcome.cache;

import java.util.Map;

import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;

/**
 * ���Ʒ����
 * @author langben 2012-8-13
 *
 */
public interface DcPromotionItemCache {
	
	/**
	 * ���û���
	 * @param item
	 */
	void set(DcPromotionItemDTO item) ;
	
	/**
	 * ȡ����
	 * @param promItemId
	 * @return
	 */
	DcPromotionItemDTO get(Long promItemId) ;
	
	/**
	 * �Ƴ�
	 * @param condition
	 * @param pagination
	 */
	void remove(Long promItemId);
	/**
	 * ��������ǰN��promitemid
	 * @param promItems
	 */
	void cachePromRanks(Long promotionId, Map<Long, Integer> ranks);
	/**
	 * 
	 * @param promotionId
	 * @return
	 */
	Map<Long, Integer> getPromRanks(Long promotionId);
}
