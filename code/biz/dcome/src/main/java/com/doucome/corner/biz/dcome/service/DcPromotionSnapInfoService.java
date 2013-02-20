package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionSnapInfoDO;
import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;

public interface DcPromotionSnapInfoService {

	/**
	 * ≤Â»Î
	 * @param snapInfo
	 * @return
	 */
	long createPromotionSnap(DcPromotionSnapInfoDO snapInfo);
	/**
	 * 
	 * @param promotionId
	 * @param page
	 * @return
	 */
	List<DcPromotionSnapInfoDTO> getPromotionSnapInfosDesc(Long promotionId);
	
	
	/**
	 * 
	 * @param promotionId
	 * @param itemId
	 * @param count
	 * @return
	 */
	int incrWishCountByPromotionAndItem(Long promotionId, Long itemId) ;
	
	
	List<DcPromotionSnapInfoDTO> getPromotionSnapsFromDb(Long promotionId, Pagination page)  ;
}
