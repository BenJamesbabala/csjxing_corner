package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionRateDetailCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionRateDetailDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRobOtherActivityDO;

/**
 * �ͶƱ��ϸ
 * @author langben 2012-8-13
 *
 */
public interface DcPromotionRateDetailDAO {

	/**
	 * ����һ��ͶƱ
	 * @param rateDetail
	 * @return
	 */
	Long insertRateDetail(DcPromotionRateDetailDO rateDetail) ;
	
	/**
	 * ��ѯͶƱ
	 * @param itemId
	 * @param rateUserId
	 * @return
	 */
	DcPromotionRateDetailDO queryRateDetailByPromItemAndRateUser(Long promotionItemId , Long rateUserId) ;
	
	/**
	 * ����������ѯ�ͶƱ
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionRateDetailDO> queryRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition , int start , int size) ;
	
	/**
	 * ��������ͳ�ƻͶƱ
	 * @return
	 */
	int countRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition) ;
	/**
	 * ��ȡ�û���ص�����Ը��Ϊ.
	 * @param userId
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	List<DcRobOtherActivityDO> getUserRobActivities(Long promotionItemId);
	/**
	 * 
	 * @param promotionId
	 * @return
	 */
	List<DcRobOtherActivityDO> getPromRobActivities(Long promotionId);
}
