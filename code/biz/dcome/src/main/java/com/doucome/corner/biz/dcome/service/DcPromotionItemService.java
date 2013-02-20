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
	 * ����ID��ѯ
	 * @param promotionItemId
	 * @return
	 */
	DcPromotionItemDTO getPromotionItemById(Long promotionItemId);
	
	/**
	 * ����������ѯ��������Ʒ
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionItemDTO> getPromotionItemsNoPagination(DcPromotionItemSearchCondition condition , Pagination pagination) ;
	
	/**
	 * ����������ѯ��������Ʒ
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcPromotionItemDTO> getPromotionItemsNoPagination(DcPromotionItemSearchCondition condition , int start , int size) ;
	
	/**
	 * ������getPromotionItemsNoPaginationһ����ֱ�Ӵ�DB��ȡ���ݣ�����cache
	 * @param condition
	 * @param pagination
	 * @return
	 */
	List<DcPromotionItemDTO> getPromotionItemsFromDB(DcPromotionItemSearchCondition condition , Pagination pagination) ;
	
	/**
	 * ����User����ͶƱ��
	 * @param promotionId
	 * @param itemId
	 */
	int incrRateCountByIdAndUser(Long promotionItemId,Long userId) ;
	
	/**
	 * ����ͶƱ��
	 * @param promotionId
	 * @param itemId
	 */
	int incrRateCountById(Long promotionItemId) ;
	/**
	 * ����ͶƱ��
	 * @param promotionId
	 * @param itemId
	 */
	int decrRateCountById(Long promotionItemId) ;
	
	/**
	 * ����Сʱ�ͻ��ȡԸ��ֵ
	 * @param promotionItemId
	 * @param hour 8-22 ֮���һ������
	 * @return
	 */
	int drawRateByHour(Long promotionItemId, int hour) ;
	
	/**
	 * ���ٿ���֧ȡ��Ը��ֵ
	 * @param promotionId
	 * @param itemId
	 */
	int decrDrawCountByHourAndId(Long promotionItemId, int hour);
	
	/**
	 * ��ȡ�û�����Ļ.
	 * @param userId
	 * @param pageNum
	 * @return
	 */
	List<DcPromotionItemDTO> getUsersPromItems(Long userId, int pageNum);
	/**
	 * ��ȡ�û������ĳ���.
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
	 * ��ȡ����.
	 * @param promotionId
	 * @return
	 */
	int getPromItemRank(Long promotionId, Long promItemId);
	
	/**
	 * ��������ֵ
	 * @param promItemId
	 * @param newGuide
	 * @return
	 */
	int updateNewGuide(Long promItemId , Long newGuide) ;
	
	/**
	 * �����ǳ�
	 * @param promItemId
	 * @param userNick
	 * @return
	 */
	int updateUserNickById(Long promItemId , String userNick) ;
	
	/**
	 * ����Ʊ��
	 * @param promItemId
	 * @param rateCount
	 * @return
	 */
	int updateRateCountById(Long promItemId , int rateCount) ;
}
