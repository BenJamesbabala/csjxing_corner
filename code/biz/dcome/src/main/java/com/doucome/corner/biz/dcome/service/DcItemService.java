package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;

/**
 * itemService
 * @author shenjia.caosj 2012-7-22
 *
 */
public interface DcItemService {
	
	/**
	 * 新建商品
	 * @param item
	 */
	Long createItem(DcItemDO item) ;
	/**
	 * 更新商品.
	 * @param item
	 * @return
	 */
	int updateItem(DcItemDO item);

	/**
	 * 通过ID查询商品
	 * @param itemId
	 * @return
	 */
	DcItemDTO getItemById(long itemId);
	
	/**
	 * 根据IDS查询
	 * @param ids
	 * @return
	 */
	List<DcItemDTO> getItemsByIds(List<Long> ids) ;
	/**
	 * 充值商品状态.
	 * @param status
	 * @return
	 */
	int resetItemStatus(Long itemId, DcItemStatusEnum status);
	
	/**
	 * 通过条件分页查询商品
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcItemDTO> getItemsNoPagination(DcItemSearchCondition searchCondition , Pagination pagination);
	
	/**
	 * 通过条件分页查询商品
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<DcItemDTO> getItemsWithPagination(DcItemSearchCondition searchCondition , Pagination pagination);
	
	/**
	 * 喜欢+1
	 * @param itemId
	 */
	int incrLoveCount(long itemId) ;
	
	/**
	 * 评论+1
	 * @param itemId
	 */
	int incrCommentCount(long itemId) ;
	/**
	 * 
	 * @param itemId
	 * @param count
	 * @return
	 */
	int incrCommentCount(long itemId, int count);
	
	/**
	 * 评论-1
	 * @param itemId
	 * @return
	 */
	int descCommentCount(long itemId) ;
	
	/**
	 * 统计各个类目的数量
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) ;
}
