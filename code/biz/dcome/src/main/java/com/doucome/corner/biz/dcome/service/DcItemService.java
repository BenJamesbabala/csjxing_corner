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
	 * �½���Ʒ
	 * @param item
	 */
	Long createItem(DcItemDO item) ;
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
	 * ����IDS��ѯ
	 * @param ids
	 * @return
	 */
	List<DcItemDTO> getItemsByIds(List<Long> ids) ;
	/**
	 * ��ֵ��Ʒ״̬.
	 * @param status
	 * @return
	 */
	int resetItemStatus(Long itemId, DcItemStatusEnum status);
	
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
	 * ͳ�Ƹ�����Ŀ������
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) ;
}
