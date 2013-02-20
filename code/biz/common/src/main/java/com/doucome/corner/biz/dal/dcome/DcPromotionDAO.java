package com.doucome.corner.biz.dal.dcome;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;

/**
 * pk �
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionDAO {

	/**
	 * �½��
	 * @param promotion
	 * @return
	 */
	Long insertPromotion(DcPromotionDO promotion) ;
	/**
	 * ���»
	 * @param promotion
	 * @return
	 */
	int updatePromotion(DcPromotionDO promotion) ;
	/**
	 * �ID
	 * @param promotionId
	 * @return
	 */
	DcPromotionDO queryPromotionById(Long promotionId) ;
	/**
	 * �������ڻ�ȡ�����.
	 * @param date
	 * @return
	 */
	DcPromotionDO queryPromotionByDate(Date date) ;
	/**
	 * ��ȡ��б��ҳ����.
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
