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
	 * ����ID��ѯ
	 * @param promotionItemId
	 * @return
	 */
	DcPromotionItemDO queryPromotionItemById(Long promotionItemId) ;
	
	/**
	 * ����������ѯ��������Ʒ
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionItemDO> queryPromotionItemsWithPagination(DcPromotionItemSearchCondition condition , int start , int size) ;
	
	/**
	 * ����ͶƱ��
	 * @param promotionId
	 * @param itemId
	 */
	int incrRateCountById(Long promotionItemId , int count) ;
	
	/**
	 * ����ͶƱ��
	 * @param promotionId
	 * @param itemId
	 */
	int decrRateCountById(Long promotionItemId, int count);
	
	/**
	 * ����Сʱ�ͻ֧ȡԸ��ֵ
	 * @param promotionItemId
	 * @param count -1 ��ʾ������0
	 * @param hour 8-22 ֮���һ������
	 * @return
	 */
	int drawRateCountByHour(Long promotionItemId, int hour) ;
	
	/**
	 * ���ٿ���֧ȡ��Ը��ֵ
	 * @param promotionId
	 * @param itemId
	 */
	int decrDrawCountByHourAndId(Long promotionItemId, int hour , int count);

	/**
	 * ����ͶƱ��
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
	 * ������������
	 * @param promItemId
	 * @param newGuide
	 * @return
	 */
	int updateNewGuide(Long promItemId, Long newGuide);

	/**
	 * ����Ʊ��
	 * @param promItemId
	 * @param rateCount
	 * @return
	 */
	int updateRateCountById(Long promItemId, int rateCount);

	/**
	 * �����ǳ�
	 * @param promItemId
	 * @param userNick
	 * @return
	 */
	int updateUserNickById(Long promItemId, String userNick);
}
