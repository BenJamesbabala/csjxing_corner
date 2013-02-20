package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;


/**
 * 豆蔻商品
 * @author langben 2012-7-21
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
	 * 根据商品的外部id获取豆蔻商品.
	 * @param externalId
	 * @return
	 */
	List<DcItemDO> getItemsByExtId(String externalId);
	/**
	 * 
	 * @param creatorId
	 * @param tbItemId
	 * @return
	 */
	DcItemDO getItemByCreatorAndExtItemId(Long creatorId, String extItemId);
	/**
	 * 根据IDS查询
	 * @param ids
	 * @return
	 */
	List<DcItemDO> queryItemsByIds(List<Long> ids) ;
	
	/**
	 * 根据NATIVE_IDS查询
	 * @param ids
	 * @return
	 */
	List<DcItemDO> queryItemsByNativeIds(List<String> nativeIds) ;
	
	/**
	 * 通过条件分页查询商品ID
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<Long> queryItemIdsWithPagination(DcItemSearchCondition searchCondition , int start , int size) ;
	
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
	int incrLoveCount(long itemId , int count) ;
	
	/**
	 * 评论+1
	 * @param itemId
	 */
	int incrCommentCount(long itemId, int count) ;
	
	/**
	 * 评论+1
	 * @param itemId
	 */
	int incrRateCount(long itemId, int count) ;
	
	/**
	 * 更新商品状态.
	 * @param itemId
	 * @param status
	 * @return
	 */
	int updateItemStatus(Long itemId, String status);
	
	/**
	 * updateRecommTypeById
	 * @param itemId
	 * @param recommType
	 * @return
	 */
	int updateRecommTypeById(Long itemId , String recommType) ;
	
	/**
	 * 评论-1
	 * @param itemId
	 */
	int descCommentCount(long itemId,int count);
	
	/**
	 * 淘宝客销量
	 * @param itemId
	 * @param count
	 * @return
	 */
	int incrTaokeSellCount(long itemId , int count) ;
	
	/**
	 * 统计各个类目的数量
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids);
	/**
	 * 同步商品信息
	 * @param items
	 * @return
	 */
	Integer batchUpdateSyncItemInfo(List<DcItemDO> items);
	/**
	 * 更新是否包邮字段.
	 * @param itemId
	 * @param postalEnable
	 * @return
	 */
	int updatePostalEnable(Long itemId, String postalEnable);
	
	/**
	 * 更新显示排序到顶
	 * @param itemId
	 * @return
	 */
	int updateDisplayOrderById(Long itemId) ;

	/**
	 * 更新商品状态
	 * @param idList
	 * @param value
	 * @return
	 */
	int updateItemStatusByIds(List<Long> idList, String value);
	
	/**
	 * 更新图片属性
	 * @param itemId
	 * @param picUrls
	 * @param picHeightProps
	 * @return
	 */
	int updatePicUrlsById(Long itemId , String picUrls);
	/**
	 * 
	 * @param userId
	 * @param genWay
	 * @return
	 */
	int updateGenWay(Long itemId, String genWay, String picUrl);
	/**
	 * 
	 * @param itemId
	 * @param categoryId
	 * @return
	 */
	int updateCategoryId(Long itemId, Long categoryId);
	/**
	 * 
	 * @param itemIds
	 * @param categoryId
	 * @return
	 */
	int batchUpdateCategoryId(List<Long> itemIds, Long categoryId);
}
