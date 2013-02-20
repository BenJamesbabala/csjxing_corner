package com.doucome.corner.biz.dal.dcome;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcAuctionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcBidInfo;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;

/**
 * 积分竞拍活动
 * @author ze2200
 *
 */
public interface DcAuctionItemDAO {
	/**
	 * 插入竞拍商品.
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
	 * 更新活动信息（最低拍卖分数、每次拍卖增加分数、开始结束时间）
	 * @param autionItemDO
	 * @return
	 */
	int updatePromoInfoById(DcAuctionItemDO autionItemDO);
	/**
	 * 更新最新竞价信息.
	 * @return
	 */
	int updateBidInfo(Long id, DcBidInfo bidInfo);
	/**
	 * 批量查询竞拍活动
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcAuctionItemDO> queryAuctionItemWithPagination(DcAuctionItemSearchCondition searchCondition , int start , int size );
	
	int countAuctionItemWithPagination(DcAuctionItemSearchCondition searchCondition);
	/**
	 * 获取在某个时间点进行中的拍卖.
	 * @return
	 */
	List<DcAuctionItemDO> getAuctionItemInIng(Date timeStamp, int start, int size);
	/**
	 * 获取某个时间点已结束的拍卖.
	 * @return
	 */
	List<DcAuctionItemDO> getAuctionItemInOver(Date timeStamp, int start, int size);
	/**
	 * 获取某个时间点还未开始的拍卖.
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
