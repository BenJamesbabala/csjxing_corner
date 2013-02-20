package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionRateDetailCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionRateDetailDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRobOtherActivityDO;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;

public interface DcPromotionRateDetailService {

	/**
	 * 新增一个投票
	 * @param rateDetail
	 * @return
	 */
	Long createRateDetail(DcPromotionRateDetailDO rateDetail) throws DuplicateOperateException ;
	
	/**
	 * 查询投票
	 * @param itemId
	 * @param rateUserId
	 * @return
	 */
	DcPromotionRateDetailDO getRateDetailByPromItemAndRateUser(Long promotionItemId , Long rateUserId) ;

	/**
	 * 查询投票
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DcPromotionRateDetailDO> getRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition , Pagination pagination) ;
	/**
	 * 
	 * @param userId
	 * @param pageNum
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
