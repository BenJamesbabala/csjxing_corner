package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;


/**
 * ��ޢ��Ʒ
 * @author shenjia.caosj 2012-7-21
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
	 * ����IDS��ѯ
	 * @param ids
	 * @return
	 */
	List<DcItemDO> queryItemsByIds(List<Long> ids) ;
	
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
	int incrLoveCount(long itemId) ;
	
	/**
	 * ����+1
	 * @param itemId
	 */
	int incrCommentCount(long itemId, int count) ;
	/**
	 * ������Ʒ״̬.
	 * @param itemId
	 * @param status
	 * @return
	 */
	int updateItemStatus(Long itemId, String status);
	
	/**
	 * ����-1
	 * @param itemId
	 */
	int descCommentCount(long itemId);
	
	/**
	 * ͳ�Ƹ�����Ŀ������
	 * @return
	 */
	List<DcCatCountDO> groupCategoryCountByIds(List<Long> ids) ;

	
}
