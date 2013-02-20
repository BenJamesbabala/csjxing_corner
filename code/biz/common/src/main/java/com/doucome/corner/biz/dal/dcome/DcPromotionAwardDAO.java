package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;

/**
 * �н���Ϣ
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionAwardDAO {

	/**
	 * �½��ջ���Ϣ
	 * @param delivery
	 * @return
	 */
	long insertAward(DcPromotionAwardDO award) ;
	
	/**
	 * ����userId��ѯ
	 * 
	 * @param userId
	 * @return
	 */
	List<DcPromotionAwardDO> queryAwardByUserId(long userId) ;
	/**
	 * ��ȡ��������.
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
	 * ����ID����reviewStatus
	 * @param promotionId
	 * @param status
	 * @return
	 */
	int updateReviewStatusById(Long awardId , String status) ;
	
	/**
	 * ����ID����SendStatus
	 * @param awardId
	 * @param status
	 * @return
	 */
	int updateSendStatusById(Long awardId, String status);

	/**
	 * ����ID��ѯ
	 * @param awardId
	 * @return
	 */
	DcPromotionAwardDO queryAwardById(long awardId);
	/**
	 * �����콱����״̬.
	 * @param awardId
	 * @param shareStatus
	 * @return
	 */
	int updateShareStatus(Long awardId, Long userId, String shareStatus);
	/**
	 * �����콱����Ϣ.
	 * @param awardDO
	 * @return
	 */
	int updateAwardAddrInfo(DcPromotionAwardDO awardDO);
}
