package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcAuctionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcBidInfo;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;
import com.doucome.corner.biz.dal.dcome.DcAuctionItemDAO;
import com.doucome.corner.biz.dcome.cache.DcAuctionItemCache;
import com.doucome.corner.biz.dcome.enums.DcAuctionReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromScheduleEnum;
import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcAuctionItemService;

/**
 * 积分竞拍服务类.
 * @author ze2200
 *
 */
public class DcAuctionItemServiceImpl implements DcAuctionItemService {
	@Autowired
	private DcAuctionItemDAO dcAuctionItemDAO;
	
	@Autowired
	private DcAuctionItemCache dcAuctionItemCache;
	
	@Override
	public Long insertAuctionItem(DcAuctionItemDO autionItemDO) {
		return dcAuctionItemDAO.insertAuctionItem(autionItemDO);
	}

	@Override
	public DcAuctionItemDTO getAuctionItem(Long id) {
		DcAuctionItemDTO auctionItem = dcAuctionItemCache.getAuctionItem(id);
		if (auctionItem != null){
			if (auctionItem.getStatus() != DcPromScheduleEnum.ONGOING) {
				dcAuctionItemCache.removeAuctionItem(id);
			}
			return auctionItem;
		}
		DcAuctionItemDO temp = dcAuctionItemDAO.getAuctionItem(id);
		auctionItem = new DcAuctionItemDTO(temp);
		if (auctionItem.getStatus() == DcPromScheduleEnum.ONGOING) {
			dcAuctionItemCache.cacheAuctionItem(auctionItem);
		}
		return auctionItem;
	}

	@Override
	public int updateBidInfo(Long id, DcUserDTO user, int bidIntegral) {
		DcBidInfo bidInfo = new DcBidInfo();
		bidInfo.setUserId(user.getUserId());
		bidInfo.setUserNick(user.getNick());
		bidInfo.setIntegral(bidIntegral);
		int count = dcAuctionItemDAO.updateBidInfo(id, bidInfo);
		if (count == 1) {
			DcAuctionItemDTO auctionItem = dcAuctionItemCache.getAuctionItem(id);
			if (auctionItem != null){
				auctionItem.setBidUserId(bidInfo.getUserId());
				auctionItem.setBidUserNick(bidInfo.getUserNick());
				auctionItem.setBidIntegral(bidInfo.getIntegral());
				dcAuctionItemCache.cacheAuctionItem(auctionItem);
			}
		}
		return count;
	}

	@Override
	public QueryResult<DcAuctionItemDTO> queryAuctionItemWithPagination(
			DcAuctionItemSearchCondition searchCondition, Pagination pagination) {
		int totalRecords = dcAuctionItemDAO.countAuctionItemWithPagination(searchCondition);
        if (totalRecords <= 0) {
            return new QueryResult<DcAuctionItemDTO>(new ArrayList<DcAuctionItemDTO>(), pagination, totalRecords);
        }
		
		List<DcAuctionItemDO> list = dcAuctionItemDAO.queryAuctionItemWithPagination(searchCondition, pagination.getStart(), pagination.getSize()) ;
		List<DcAuctionItemDTO> dtoList = convert(list);
		return new QueryResult<DcAuctionItemDTO>(dtoList, pagination, totalRecords);
	}
	
	@Override
	public List<DcAuctionItemDTO> queryAuctionItems(DcPromScheduleEnum status, Pagination page) {
		Date timeStamp = new Date();
		List<DcAuctionItemDO> temps = null;
		switch (status) {
		case ONGOING:
			temps = dcAuctionItemDAO.getAuctionItemInIng(timeStamp, page.getStart() - 1, page.getSize());
			break;
		case ENDED:
			temps = dcAuctionItemDAO.getAuctionItemInOver(timeStamp, page.getStart() - 1, page.getSize());
			break;
		case FUTURE:
			temps = dcAuctionItemDAO.getAuctionItemInFuture(timeStamp, page.getStart() - 1, page.getSize());
			break;
		default:
			temps = new ArrayList<DcAuctionItemDO>();
		}
		return convert(temps);
	}
	
	private List<DcAuctionItemDTO> convert(List<DcAuctionItemDO> auctionItems) {
		List<DcAuctionItemDTO> dtoList = new ArrayList<DcAuctionItemDTO>() ;
		if(CollectionUtils.isNotEmpty(auctionItems)) {
			for(DcAuctionItemDO auctionItemDO : auctionItems){
				dtoList.add(new DcAuctionItemDTO(auctionItemDO)) ; 
			}
		}
		return dtoList;
	}

	@Override
	public boolean updatePromoInfoById(DcAuctionItemDTO autionItemDTO) {
		if (autionItemDTO == null) {
			return false;
		}
		DcAuctionItemDO auctionItemDO = new DcAuctionItemDO();
		auctionItemDO.setId(autionItemDTO.getId());
		auctionItemDO.setBaseIntegral(autionItemDTO.getBaseIntegral());
		auctionItemDO.setIntegralPer(autionItemDTO.getIntegralPer());
		auctionItemDO.setGmtStart(autionItemDTO.getGmtStart());
		auctionItemDO.setGmtEnd(autionItemDTO.getGmtEnd());
		return dcAuctionItemDAO.updatePromoInfoById(auctionItemDO) > 0;
	}

	@Override
	public int updateReviewStatusById(Long id, DcAuctionReviewStatusEnums reviewStatus) {
		return dcAuctionItemDAO.updateReviewStatusById(id, reviewStatus.getStatus()) ;
	}
}
