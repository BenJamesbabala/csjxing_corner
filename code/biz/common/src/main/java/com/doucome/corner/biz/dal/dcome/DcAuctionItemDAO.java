package com.doucome.corner.biz.dal.dcome;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcAuctionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcBidInfo;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;

/**
 * ���־��Ļ
 * @author ze2200
 *
 */
public interface DcAuctionItemDAO {
	/**
	 * ���뾺����Ʒ.
	 * @param autionItemDO
	 * @return
	 */
	Long insertAuctionItem(DcAuctionItemDO autionItemDO);
	/**
	 * 
	 * @param id
	 * @return
	 */
	DcAuctionItemDO getAuctionItem(Long id);
	/**
	 * ���»��Ϣ���������������ÿ���������ӷ�������ʼ����ʱ�䣩
	 * @param autionItemDO
	 * @return
	 */
	int updatePromoInfoById(DcAuctionItemDO autionItemDO);
	/**
	 * �������¾�����Ϣ.
	 * @return
	 */
	int updateBidInfo(Long id, DcBidInfo bidInfo);
	/**
	 * ������ѯ���Ļ
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcAuctionItemDO> queryAuctionItemWithPagination(DcAuctionItemSearchCondition searchCondition , int start , int size );
	
	int countAuctionItemWithPagination(DcAuctionItemSearchCondition searchCondition);
	/**
	 * ��ȡ��ĳ��ʱ�������е�����.
	 * @return
	 */
	List<DcAuctionItemDO> getAuctionItemInIng(Date timeStamp, int start, int size);
	/**
	 * ��ȡĳ��ʱ����ѽ���������.
	 * @return
	 */
	List<DcAuctionItemDO> getAuctionItemInOver(Date timeStamp, int start, int size);
	/**
	 * ��ȡĳ��ʱ��㻹δ��ʼ������.
	 * @return
	 */
	List<DcAuctionItemDO> getAuctionItemInFuture(Date timeStamp, int start, int size);
	
	/**
	 * 
	 * @param id
	 * @param reviewStatus
	 * @return
	 */
	int updateReviewStatusById(Long id , String reviewStatus) ;
}
