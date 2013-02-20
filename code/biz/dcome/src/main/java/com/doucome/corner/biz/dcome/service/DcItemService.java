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
     * ͳ������
     * @param condition
     * @return
     */
    long countItemsWithPagination(DcItemSearchCondition condition);
	/**
	 * �½���Ʒ
	 * @param item
	 */
	Long createItem(DcItemDO item) throws DcDuplicateKeyException;
	/**
	 * ������Ʒ.
	 * @param item
	 * @return
	 */
	int updateItem(DcItemDO item);
	/**
	 * ͨ��ID��ѯ��Ʒ
	 * @param itemId
	 * @return
	 */
	DcItemDTO getItemById(long itemId);
	/**
	 * ������Ʒ���ⲿ��Ż�ȡ��ޢ��Ʒ.
	 * @param externalId
	 * @return
	 */
	List<DcItemDTO> getItemsByExtlId(String externalId);
	/**
	 * ���ݴ����ߺ��Ա���Ʒid��ȡ��Ʒ.
	 * @param creator 
	 * @param tbItemId
	 * @return
	 */
	DcItemDTO getItemByCreatorAndExtItemId(Long creator, String extItemId);
	/**
	 * ����IDS��ѯ
	 * @param ids
	 * @return
	 */
	List<DcItemDTO> getItemsByIds(List<Long> idList) ;
	/**
	 * ��ֵ��Ʒ״̬.
	 * @param status
	 * @return
	 */
	int resetItemStatus(Long itemId, DcItemStatusEnum status);
	
	/**
	 * ��Ʒ״̬.
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
	 * ͨ��������ҳ��ѯ��Ʒ
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcItemDTO> getItemsNoPagination(DcItemSearchCondition searchCondition , Pagination pagination);
	
	/**
	 * ͨ��������ҳ��ѯ��Ʒ
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<DcItemDTO> getItemsWithPagination(DcItemSearchCondition searchCondition , Pagination pagination);
	
	/**
	 * ϲ��+1
	 * @param itemId
	 */
	int incrLoveCount(long itemId) ;
	
	/**
	 * ����+1
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
	 * ����-1
	 * @param itemId
	 * @return
	 */
	int descCommentCount(long itemId) ;
	
	/**
	 * ͶƱ+1
	 * @param itemId
	 */
	int incrRateCount(long itemId) ;
	
	/**
	 * ͳ�Ƹ�����Ŀ������
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids);
	/**
	 * �û��Ƿ��ظ��ύ��Ʒ���ɱ�.
	 * @param userId
	 * @param tbItemId
	 * @return
	 * @throws CacheFailedException ����ʧ��.
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
	 * �����Ƿ�����ֶ�.
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
	 * ������ʾ���򵽶�
	 * @param itemId
	 * @return
	 */
	int updateDisplayOrderById(Long itemId) ;
	
	/**
	 * �Ա�������
	 * @param itemId
	 * @param count
	 * @return
	 */
	int incrTaokeSellCount(long itemId , int count) ;
	
	/**
	 * ����NATIVE_IDS��ѯ
	 * @param nativeIds
	 * @return
	 */
	List<DcItemDTO> getItemsByNativeIds(List<String> nativeIds) ;
	
	/**
	 * ����ͼƬ����
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
