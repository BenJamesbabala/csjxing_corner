package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;


/**
 * 豆蔻商品
 * @author shenjia.caosj 2012-7-21
 *
 */
public interface DcItemDAO {
	
	/**
	 * 新建商品
	 * @param item
	 */
	Long insertItem(DcItemDO item) ;
	
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
	DcItemDO queryItemById(long itemId) ;
	
	/**
	 * 根据IDS查询
	 * @param ids
	 * @return
	 */
	List<DcItemDO> queryItemsByIds(List<Long> ids) ;
	
	/**
	 * 通过条件分页查询商品
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcItemDO> queryItemsWithPagination(DcItemSearchCondition searchCondition , int start , int size);
	
	/**
	 * 通过条件统计商品
	 * @param searchCondition
	 * @return
	 */
	int countItemsWithPagination(DcItemSearchCondition searchCondition) ;
	
	/**
	 * 喜欢+1
	 * @param itemId
	 */
	int incrLoveCount(long itemId) ;
	
	/**
	 * 评论+1
	 * @param itemId
	 */
	int incrCommentCount(long itemId, int count) ;
	/**
	 * 更新商品状态.
	 * @param itemId
	 * @param status
	 * @return
	 */
	int updateItemStatus(Long itemId, String status);
	
	/**
	 * 评论-1
	 * @param itemId
	 */
	int descCommentCount(long itemId);
	
	/**
	 * 统计各个类目的数量
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) ;

	
}
