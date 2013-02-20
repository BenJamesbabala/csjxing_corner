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
 * ���־��ķ���
 * @author ze2200
 *
 */
public interface DcAuctionItemService {
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
	DcAuctionItemDTO getAuctionItem(Long id);
	/**
	 * �������¾�����Ϣ.
	 * @return
	 */
	int updateBidInfo(Long id, DcUserDTO user, int bidIntegral);
	
	boolean updatePromoInfoById(DcAuctionItemDTO autionItemDTO);
	
	QueryResult<DcAuctionItemDTO> queryAuctionItemWithPagination(DcAuctionItemSearchCondition searchCondition , Pagination pagination );
	/**
	 * ��ѯ��Ӧ״̬������.
	 * �������ѽ���ʱ�䵹�����У�δ��ʼ���ѿ�ʼʱ����������.
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
