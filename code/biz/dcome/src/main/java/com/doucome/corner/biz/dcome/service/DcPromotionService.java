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
 * 豆蔻活动服务.
 * @author ze2200
 *
 */
public interface DcPromotionService {

	/**
	 * 新建活动
	 * @param promotion
	 * @return
	 */
	Long createPromotion(DcPromotionDO promotion) ;
	/**
     * 更新活动
     * @param promotion
     * @return
     */
    int updatePromotion(DcPromotionDTO promotion) ;
	/**
	 * 获取 当前正在进行的活动.
	 * @return
	 */
	DcPromotionDTO getCurPromotion() ;
	/**
	 * 活动ID
	 * @param promotionId
	 * @return
	 */
	DcPromotionDTO getPromotionById(Long promotionId);
	/**
	 * 根据日期获取活动数据
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
