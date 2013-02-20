package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcAuctionItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;
import com.doucome.corner.biz.dcome.enums.DcAuctionReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromScheduleEnum;
import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;

/**
 * 积分竞拍服务
 * @author ze2200
 *
 */
public interface DcAuctionItemService {
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
	DcAuctionItemDTO getAuctionItem(Long id);
	/**
	 * 更新最新竞价信息.
	 * @return
	 */
	int updateBidInfo(Long id, DcUserDTO user, int bidIntegral);
	
	boolean updatePromoInfoById(DcAuctionItemDTO autionItemDTO);
	
	QueryResult<DcAuctionItemDTO> queryAuctionItemWithPagination(DcAuctionItemSearchCondition searchCondition , Pagination pagination );
	/**
	 * 查询相应状态的拍卖.
	 * 结束的已结束时间倒叙排列，未开始的已开始时间升序排列.
	 * @param status
	 * @param page
	 * @return
	 */
	List<DcAuctionItemDTO> queryAuctionItems(DcPromScheduleEnum status, Pagination page);
	
	/**
	 * 
	 * @param id
	 * @param reviewStatus
	 * @return
	 */
	int updateReviewStatusById(Long id, DcAuctionReviewStatusEnums reviewStatus) ;
}
