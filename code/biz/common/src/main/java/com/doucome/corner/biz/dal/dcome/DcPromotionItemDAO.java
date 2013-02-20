package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;

/**
 * 
 * @author ze2200
 *
 */
public interface DcPromotionItemDAO {
	
	/**
	 * 
	 * @param promotionItemDO
	 * @return
	 */
	Long insertPromotionItem(DcPromotionItemDO promotionItemDO);
	
	/**
	 * 根据ID查询
	 * @param promotionItemId
	 * @return
	 */
	DcPromotionItemDO queryPromotionItemById(Long promotionItemId) ;
	
	/**
	 * 根据条件查询参与活动的商品
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionItemDO> queryPromotionItemsWithPagination(DcPromotionItemSearchCondition condition , int start , int size) ;
	
	/**
	 * 增加投票数
	 * @param promotionId
	 * @param itemId
	 */
	int incrRateCountById(Long promotionItemId , int count) ;
	
	/**
	 * 减少投票数
	 * @param promotionId
	 * @param itemId
	 */
	int decrRateCountById(Long promotionItemId, int count);
	
	/**
	 * 根据小时和活动支取愿望值
	 * @param promotionItemId
	 * @param count -1 表示减少至0
	 * @param hour 8-22 之间的一个数字
	 * @return
	 */
	int drawRateCountByHour(Long promotionItemId, int hour) ;
	
	/**
	 * 减少可以支取的愿望值
	 * @param promotionId
	 * @param itemId
	 */
	int decrDrawCountByHourAndId(Long promotionItemId, int hour , int count);

	/**
	 * 增加投票数
	 * @param promotionItemId
	 * @param userId
	 * @return
	 */
	int incrRateCountByIdAndUser(Long promotionItemId,int count, Long userId);
    /**
     * 
     * @param userId
     * @param pageStart
     * @param pageSize
     * @return
     */
	List<DcPromotionItemDO> getUsersPromItems(Long userId, int pageStart, int pageSize);
	/**
	 * 
	 * @param userId
	 * @param promotionId
	 * @return
	 */
	List<DcPromotionItemDO> getUsersPromItems(Long userId, Long promotionId);
	/**
	 * 
	 * @param promItemId
	 * @return
	 */
	Integer updatePromItemShareStatus(Long promItemId, String shareStatus);

	/**
	 * 更新新手引导
	 * @param promItemId
	 * @param newGuide
	 * @return
	 */
	int updateNewGuide(Long promItemId, Long newGuide);

	/**
	 * 更新票数
	 * @param promItemId
	 * @param rateCount
	 * @return
	 */
	int updateRateCountById(Long promItemId, int rateCount);

	/**
	 * 更新昵称
	 * @param promItemId
	 * @param userNick
	 * @return
	 */
	int updateUserNickById(Long promItemId, String userNick);
}
