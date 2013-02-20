package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionSnapInfoDO;

public interface DcPromotionSnapInfoDAO {

	/**
	 * 插入
	 * @param snapInfo
	 * @return
	 */
	long insertPromotionSnap(DcPromotionSnapInfoDO snapInfo) ;
	
	/**
	 * 查询
	 * @return
	 */
	List<DcPromotionSnapInfoDO> queryPromotionSnapWithPagination(Long promotionId , int start , int size) ;
	
	/**
	 * 增加一个商品的愿望数
	 * @param promotionId
	 * @param itemId
	 * @return
	 */
	int incrWishCountByPromotionAndItem(Long promotionId , Long itemId , int count) ;
}
