package com.doucome.corner.biz.dal.dcome;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;

/**
 * pk 活动
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionDAO {

	/**
	 * 新建活动
	 * @param promotion
	 * @return
	 */
	Long insertPromotion(DcPromotionDO promotion) ;
	/**
	 * 更新活动
	 * @param promotion
	 * @return
	 */
	int updatePromotion(DcPromotionDO promotion) ;
	/**
	 * 活动ID
	 * @param promotionId
	 * @return
	 */
	DcPromotionDO queryPromotionById(Long promotionId) ;
	/**
	 * 根据日期获取活动数据.
	 * @param date
	 * @return
	 */
	DcPromotionDO queryPromotionByDate(Date date) ;
	/**
	 * 获取活动列表分页数据.
	 * @param condition
	 * @param pageStart
	 * @param pageSize
	 */
	List<DcPromotionDO> getPromotionsWithPage(DcPromotionCondition condition, int pageStart, int pageSize);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	Integer countPromotions(DcPromotionCondition condition);
	/**
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionDO> queryPromotionsWithPagination(DcPromotionSearchCondition searchCondition , int start , int size);
}
