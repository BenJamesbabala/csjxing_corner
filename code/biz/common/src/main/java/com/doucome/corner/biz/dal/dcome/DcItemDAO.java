package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;


/**
 * ��ޢ��Ʒ
 * @author langben 2012-7-21
 *
 */
public interface DcItemDAO {
	
	/**
	 * �½���Ʒ
	 * @param item
	 */
	Long insertItem(DcItemDO item) ;
	
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
	DcItemDO queryItemById(long itemId) ;
	/**
	 * ������Ʒ���ⲿid��ȡ��ޢ��Ʒ.
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
	 * ����IDS��ѯ
	 * @param ids
	 * @return
	 */
	List<DcItemDO> queryItemsByIds(List<Long> ids) ;
	
	/**
	 * ����NATIVE_IDS��ѯ
	 * @param ids
	 * @return
	 */
	List<DcItemDO> queryItemsByNativeIds(List<String> nativeIds) ;
	
	/**
	 * ͨ��������ҳ��ѯ��ƷID
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<Long> queryItemIdsWithPagination(DcItemSearchCondition searchCondition , int start , int size) ;
	
	/**
	 * ͨ��������ҳ��ѯ��Ʒ
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcItemDO> queryItemsWithPagination(DcItemSearchCondition searchCondition , int start , int size);
	
	/**
	 * ͨ������ͳ����Ʒ
	 * @param searchCondition
	 * @return
	 */
	int countItemsWithPagination(DcItemSearchCondition searchCondition) ;
	
	/**
	 * ϲ��+1
	 * @param itemId
	 */
	int incrLoveCount(long itemId , int count) ;
	
	/**
	 * ����+1
	 * @param itemId
	 */
	int incrCommentCount(long itemId, int count) ;
	
	/**
	 * ����+1
	 * @param itemId
	 */
	int incrRateCount(long itemId, int count) ;
	
	/**
	 * ������Ʒ״̬.
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
	 * ����-1
	 * @param itemId
	 */
	int descCommentCount(long itemId,int count);
	
	/**
	 * �Ա�������
	 * @param itemId
	 * @param count
	 * @return
	 */
	int incrTaokeSellCount(long itemId , int count) ;
	
	/**
	 * ͳ�Ƹ�����Ŀ������
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids);
	/**
	 * ͬ����Ʒ��Ϣ
	 * @param items
	 * @return
	 */
	Integer batchUpdateSyncItemInfo(List<DcItemDO> items);
	/**
	 * �����Ƿ�����ֶ�.
	 * @param itemId
	 * @param postalEnable
	 * @return
	 */
	int updatePostalEnable(Long itemId, String postalEnable);
	
	/**
	 * ������ʾ���򵽶�
	 * @param itemId
	 * @return
	 */
	int updateDisplayOrderById(Long itemId) ;

	/**
	 * ������Ʒ״̬
	 * @param idList
	 * @param value
	 * @return
	 */
	int updateItemStatusByIds(List<Long> idList, String value);
	
	/**
	 * ����ͼƬ����
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
