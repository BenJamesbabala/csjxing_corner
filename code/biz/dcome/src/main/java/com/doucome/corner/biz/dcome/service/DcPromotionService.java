package com.doucome.corner.biz.dcome.service;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;

/**
 * ��ޢ�����.
 * @author ze2200
 *
 */
public interface DcPromotionService {

	/**
	 * �½��
	 * @param promotion
	 * @return
	 */
	Long createPromotion(DcPromotionDO promotion) ;
	/**
     * ���»
     * @param promotion
     * @return
     */
    int updatePromotion(DcPromotionDTO promotion) ;
	/**
	 * ��ȡ ��ǰ���ڽ��еĻ.
	 * @return
	 */
	DcPromotionDTO getCurPromotion() ;
	/**
	 * �ID
	 * @param promotionId
	 * @return
	 */
	DcPromotionDTO getPromotionById(Long promotionId);
	/**
	 * �������ڻ�ȡ�����
	 * @param promotionId
	 * @return
	 */
	DcPromotionDTO getPromotionByDate(Date date);
	/**
	 * 
	 * @param condition
	 * @param pageNum
	 * @return
	 */
	QueryResult<DcPromotionDTO> getPromotionsWithPage(DcPromotionCondition condition, int pageNum);
	/**
	 * 
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	List<DcPromotionDTO> getPromotionsNoPagination(DcPromotionSearchCondition searchCondition , Pagination pagination) ;
	
}
