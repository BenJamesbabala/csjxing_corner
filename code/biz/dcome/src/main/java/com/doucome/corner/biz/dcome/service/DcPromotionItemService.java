package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;

/**
 * 
 * @author ze2200
 *
 */
public interface DcPromotionItemService {
	
	/**
	 * 
	 * @param promotionItemDO
	 * @return
	 */
	Long createPromotionItem(DcPromotionItemDO promotionItemDO) throws DcDuplicateKeyException;
	
	/**
	 * 根据ID查询
	 * @param promotionItemId
	 * @return
	 */
	DcPromotionItemDTO getPromotionItemById(Long promotionItemId);
	
	/**
	 * 根据条件查询参与活动的商品
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionItemDTO> getPromotionItemsNoPagination(DcPromotionItemSearchCondition condition , Pagination pagination) ;
	
	/**
	 * 根据条件查询参与活动的商品
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionItemDTO> getPromotionItemsNoPagination(DcPromotionItemSearchCondition condition , int start , int size) ;
	
	/**
	 * 功能与getPromotionItemsNoPagination一样，直接从DB获取数据，不走cache
	 * @param condition
	 * @param pagination
	 * @return
	 */
	List<DcPromotionItemDTO> getPromotionItemsFromDB(DcPromotionItemSearchCondition condition , Pagination pagination) ;
	
	/**
	 * 根据User增加投票数
	 * @param promotionId
	 * @param itemId
	 */
	int incrRateCountByIdAndUser(Long promotionItemId,Long userId) ;
	
	/**
	 * 增加投票数
	 * @param promotionId
	 * @param itemId
	 */
	int incrRateCountById(Long promotionItemId) ;
	/**
	 * 减少投票数
	 * @param promotionId
	 * @param itemId
	 */
	int decrRateCountById(Long promotionItemId) ;
	
	/**
	 * 根据小时和活动领取愿望值
	 * @param promotionItemId
	 * @param hour 8-22 之间的一个数字
	 * @return
	 */
	int drawRateByHour(Long promotionItemId, int hour) ;
	
	/**
	 * 减少可以支取的愿望值
	 * @param promotionId
	 * @param itemId
	 */
	int decrDrawCountByHourAndId(Long promotionItemId, int hour);
	
	/**
	 * 获取用户参与的活动.
	 * @param userId
	 * @param pageNum
	 * @return
	 */
	List<DcPromotionItemDTO> getUsersPromItems(Long userId, int pageNum);
	/**
	 * 获取用户参与的某个活动.
	 * @param userId
	 * @param promotionId
	 * @return
	 */
	DcPromotionItemDTO getUsersPromItems(Long userId, Long promotionId);
	/**
	 * 
	 * @param promItemId
	 * @return
	 */
	Integer markPromItemShared(Long promItemId);
	/**
	 * 获取排名.
	 * @param promotionId
	 * @return
	 */
	int getPromItemRank(Long promotionId, Long promItemId);
	
	/**
	 * 更新引导值
	 * @param promItemId
	 * @param newGuide
	 * @return
	 */
	int updateNewGuide(Long promItemId , Long newGuide) ;
	
	/**
	 * 更新昵称
	 * @param promItemId
	 * @param userNick
	 * @return
	 */
	int updateUserNickById(Long promItemId , String userNick) ;
	
	/**
	 * 更新票数
	 * @param promItemId
	 * @param rateCount
	 * @return
	 */
	int updateRateCountById(Long promItemId , int rateCount) ;
}
