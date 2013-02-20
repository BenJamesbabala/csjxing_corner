package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionSnapInfoDO;

public interface DcPromotionSnapInfoDAO {

	/**
	 * ����
	 * @param snapInfo
	 * @return
	 */
	long insertPromotionSnap(DcPromotionSnapInfoDO snapInfo) ;
	
	/**
	 * ��ѯ
	 * @return
	 */
	List<DcPromotionSnapInfoDO> queryPromotionSnapWithPagination(Long promotionId , int start , int size) ;
	
	/**
	 * ����һ����Ʒ��Ը����
	 * @param promotionId
	 * @param itemId
	 * @return
	 */
	int incrWishCountByPromotionAndItem(Long promotionId , Long itemId , int count) ;
}
