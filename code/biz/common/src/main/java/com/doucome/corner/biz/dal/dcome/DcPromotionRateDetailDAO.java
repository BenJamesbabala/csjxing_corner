package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionRateDetailCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionRateDetailDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRobOtherActivityDO;

/**
 * 活动投票明细
 * @author langben 2012-8-13
 *
 */
public interface DcPromotionRateDetailDAO {

	/**
	 * 新增一个投票
	 * @param rateDetail
	 * @return
	 */
	Long insertRateDetail(DcPromotionRateDetailDO rateDetail) ;
	
	/**
	 * 查询投票
	 * @param itemId
	 * @param rateUserId
	 * @return
	 */
	DcPromotionRateDetailDO queryRateDetailByPromItemAndRateUser(Long promotionItemId , Long rateUserId) ;
	
	/**
	 * 根据条件查询活动投票
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionRateDetailDO> queryRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition , int start , int size) ;
	
	/**
	 * 根据条件统计活动投票
	 * @return
	 */
	int countRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition) ;
	/**
	 * 获取用户相关的抢心愿行为.
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
