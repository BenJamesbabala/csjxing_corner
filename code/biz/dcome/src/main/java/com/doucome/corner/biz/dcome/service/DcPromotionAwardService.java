package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;

/**
 * 用户收货信息
 * @author langben 2012-8-10
 *
 */
public interface DcPromotionAwardService {
	
	/**
	 * 新建收货信息
	 * @param delivery
	 * @return
	 */
	long createAward(DcPromotionAwardDO award);
	
	/**
	 * 根据ID查询
	 * @param awardId
	 * @return
	 */
	DcPromotionAwardDTO getAwardById(long awardId) ;
	
	/**
	 * 获取用户的中奖情况.
	 * 
	 * @param userId
	 * @return
	 */
	List<DcPromotionAwardDTO> getAwardByUserId(long userId) ;
	/**
	 * 获取活动的开奖情况.
	 * @param promotionId 活动id.
	 * @return
	 */
	List<DcPromotionAwardDTO> getAwardsByPromId(Long promotionId);
	/**
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionAwardDTO> getAwardsNoPagination(DcPromotionAwardCondition condition , Pagination pagination ) ;
	
	/**
	 * 获取中将记录
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DcPromotionAwardDTO> getAwardsWithPagination(DcPromotionAwardCondition searchCondition , Pagination pagination ) ;
	
	/**
	 * 根据ID更新reviewStatus
	 * @param promotionId
	 * @param status
	 * @return
	 */
	int updateReviewStatusById(Long awardId, DcPromotionAwardReviewStatusEnums status) ;
	
	/**
	 * 根据ID更新sendStatus
	 * @param promotionId
	 * @param status
	 * @return
	 */
	int updateSendStatusById(Long awardId, DcPromotionAwardSendStatusEnums status) ;
	
	/**
	 * 更新领奖分享状态.
	 * @param awardId
	 * @param userId
	 * @param shareStatus
	 * @return
	 */
	int updateShareStatus(Long awardId, Long userId, DcShareStatusEnum shareStatus);
	/**
	 * 更新领奖人信息.
	 * @param awardDTO
	 * @return
	 */
	int updateAwardAddrInfo(DcPromotionAwardDTO awardDTO);
}
