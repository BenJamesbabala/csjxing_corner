package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcPromotionDTO;

/**
 * �����
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionCache {
	
	/**
	 * ��ȡ��ǰ�
	 * @return
	 */
	DcPromotionDTO getCurrentPromotion() ;
	
	/**
	 * ���õ�ǰ�����
	 * @param item
	 */
	void setCurrentPromotion(DcPromotionDTO item) ;

	/**
	 * ����IDȡ
	 * @param id
	 * @return
	 */
	DcPromotionDTO get(Long id) ;
	
	/**
	 * ���û���
	 * @param item
	 */
	void set(DcPromotionDTO item) ;
	
	/**
	 * �������
	 * @param id
	 */
	void remove(Long id) ;
}
