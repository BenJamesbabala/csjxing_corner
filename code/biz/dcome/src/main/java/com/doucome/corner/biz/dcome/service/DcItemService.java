package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.exception.CacheFailedException;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemRecommTypeEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;

/**
 * itemService
 * @author langben 2012-7-22
 *
 */
public interface DcItemService {
	
    /**
     * 统计总数
     * @param condition
     * @return
     */
    long countItemsWithPagination(DcItemSearchCondition condition);
	/**
	 * 新建商品
	 * @param item
	 */
	Long createItem(DcItemDO item) throws DcDuplicateKeyException;
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
	 * 根据商品的外部编号获取豆蔻商品.
	 * @param externalId
	 * @return
	 */
	List<DcItemDTO> getItemsByExtlId(String externalId);
	/**
	 * 根据创建者和淘宝商品id获取商品.
	 * @param creator 
	 * @param tbItemId
	 * @return
	 */
	DcItemDTO getItemByCreatorAndExtItemId(Long creator, String extItemId);
	/**
	 * 根据IDS查询
	 * @param ids
	 * @return
	 */
	List<DcItemDTO> getItemsByIds(List<Long> idList) ;
	/**
	 * 充值商品状态.
	 * @param status
	 * @return
	 */
	int resetItemStatus(Long itemId, DcItemStatusEnum status);
	
	/**
	 * 商品状态.
	 * @param idList
	 * @param status
	 * @return
	 */
	int updateItemStatusByIds(List<Long> idList , DcItemStatusEnum status) ;
	
	/**
	 * 
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	List<Long> getItemIdsNoPagination(DcItemSearchCondition searchCondition, Pagination pagination) ;
	
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
	 * 投票+1
	 * @param itemId
	 */
	int incrRateCount(long itemId) ;
	
	/**
	 * 统计各个类目的数量
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids);
	/**
	 * 用户是否重复提交商品生成表单.
	 * @param userId
	 * @param tbItemId
	 * @return
	 * @throws CacheFailedException 缓存失败.
	 */
	boolean isUserReSubmitItem(Long userId, String tbItemId) throws CacheFailedException;
	
	/**
	 * updateRecommTypeById
	 * @param itemId
	 * @param recommType
	 * @return
	 */
	int updateRecommTypeById(Long itemId , DcItemRecommTypeEnums recommType) ;
	/**
	 * 更新是否包邮字段.
	 * @param itemId
	 * @param postalEnable
	 * @return
	 */
	int updatePostalEnable(Long itemId, DcYesOrNoEnum postalEnable);
	
	/**
	 * 
	 * @param items
	 * @return
	 */
	int batchUpdateSyncItemInfo(List<DcItemDO> items);
	
	/**
	 * 更新显示排序到顶
	 * @param itemId
	 * @return
	 */
	int updateDisplayOrderById(Long itemId) ;
	
	/**
	 * 淘宝客销量
	 * @param itemId
	 * @param count
	 * @return
	 */
	int incrTaokeSellCount(long itemId , int count) ;
	
	/**
	 * 根据NATIVE_IDS查询
	 * @param nativeIds
	 * @return
	 */
	List<DcItemDTO> getItemsByNativeIds(List<String> nativeIds) ;
	
	/**
	 * 更新图片属性
	 * @param itemId
	 * @param picUrls
	 * @param picHeightProps
	 * @return
	 */
	int updatePicUrlsById(Long itemId, List<DcItemPicModel> picUrls) ;
	/**
	 * 
	 * @param userId
	 * @param genWay
	 * @return
	 */
	int updateGenWay(Long itemId, DcItemGenWayEnums genWay, String picUrl);
	/**
	 * 
	 * @param itemId
	 * @param categoryId
	 * @return
	 */
	int updateCategoryId(Long itemId, Long categoryId);
	
	int batchUpdateCategoryId(List<Long> itemIds, Long categoryId);
}
