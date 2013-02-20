package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;

/**
 * 中奖信息
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionAwardDAO {

	/**
	 * 新建收货信息
	 * @param delivery
	 * @return
	 */
	long insertAward(DcPromotionAwardDO award) ;
	
	/**
	 * 根据userId查询
	 * 
	 * @param userId
	 * @return
	 */
	List<DcPromotionAwardDO> queryAwardByUserId(long userId) ;
	/**
	 * 获取活动开奖情况.
	 * @param promotionId
	 * @return
	 */
	List<DcPromotionAwardDO> getAwardsByPromId(Long promotionId);
	/**
	 * 
	 * @param searchCondition
	 * @return
	 */
	List<DcPromotionAwardDO> queryAwardsNoPage(DcPromotionAwardCondition condition);
	/**
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionAwardDO> queryAwardsWithPagination(DcPromotionAwardCondition searchCondition , int start , int size );
	
	/**
	 * 
	 * @param searchCondition
	 * @return
	 */
	int countAwardsWithPagination(DcPromotionAwardCondition searchCondition);
	
	/**
	 * 根据ID更新reviewStatus
	 * @param promotionId
	 * @param status
	 * @return
	 */
	int updateReviewStatusById(Long awardId , String status) ;
	
	/**
	 * 根据ID更新SendStatus
	 * @param awardId
	 * @param status
	 * @return
	 */
	int updateSendStatusById(Long awardId, String status);

	/**
	 * 根据ID查询
	 * @param awardId
	 * @return
	 */
	DcPromotionAwardDO queryAwardById(long awardId);
	/**
	 * 更新领奖分享状态.
	 * @param awardId
	 * @param shareStatus
	 * @return
	 */
	int updateShareStatus(Long awardId, Long userId, String shareStatus);
	/**
	 * 更新领奖人信息.
	 * @param awardDO
	 * @return
	 */
	int updateAwardAddrInfo(DcPromotionAwardDO awardDO);
}
