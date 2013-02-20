package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dcome.enums.DcPromScheduleEnum;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.model.param.DcAuctionBidModel;
import com.doucome.corner.biz.dcome.service.DcAuctionItemService;
import com.doucome.corner.biz.dcome.service.DcMessageService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcConvertUtils;

/**
 * ��������ҵ���߼���.
 * @author ze2200
 *
 */
public class DcAuctionBO {
	
	@Autowired
	private DcAuctionItemService dcAuctionItemService;
	
	@Autowired
	private DcUserIntegralBO dcUserIntegralBO;
	
	@Autowired
	private DcMessageService dcMessageService;
	
	@Autowired
	private DcUserService dcUserService;
	
	/**
	 * 
	 * @return
	 */
	public List<DcAuctionItemDTO> queryAuctions() {
		List<DcAuctionItemDTO> result = new ArrayList<DcAuctionItemDTO>();
		//ÿ��ʱ��ֻ��һ������
		List<DcAuctionItemDTO> auctionItems =
			dcAuctionItemService.queryAuctionItems(DcPromScheduleEnum.ONGOING, new Pagination(1, 1));
		for (DcAuctionItemDTO temp: auctionItems) {
			List<DcMessageDTO> messages = getAuctionHistories(temp.getId(), temp.getBidIntegral() - temp.getIntegralPer() * 10);
			temp.setBidHistories(messages);
		}
		
		result.addAll(auctionItems);
		auctionItems = dcAuctionItemService.queryAuctionItems(DcPromScheduleEnum.FUTURE, new Pagination(1, 1));
		result.addAll(auctionItems);
		auctionItems = dcAuctionItemService.queryAuctionItems(DcPromScheduleEnum.ENDED, new Pagination(1, 5));
		result.addAll(auctionItems);
		return result;
	}
	
	/**
	 * ������Ʒ.
	 * @param auctionId
	 * @param user
	 * @param integral
	 * @return ���ۼ�¼.
	 */
	public DcMessageDTO bidItem(Long auctionId, DcUserDTO user, Integer bidIntegral) {
		DcMessageDTO bidMessage = new DcMessageDTO();
		bidMessage.setGmtCreate(new Date());
		if ((user.getIntegralCount() + user.getFrozenIntegralCount()) < bidIntegral) {
			bidMessage.setOtherInfo("integral.not.enough");
			return bidMessage;
		}
		DcAuctionItemDTO auctionItem = dcAuctionItemService.getAuctionItem(auctionId);
		if (auctionItem.getStatus() != DcPromScheduleEnum.ONGOING) {
			bidMessage.setOtherInfo("auction.end");
			return bidMessage;
		}
		if (!auctionItem.isBidValid(bidIntegral)) {
			bidMessage.setOtherInfo("bid.invalid");
			return bidMessage;
		}
		//��¼�û�����
		DcAuctionBidModel bidModel = new DcAuctionBidModel();
		bidModel.setUserId(user.getUserId());
		bidModel.setUserNick(user.getNick());
		bidModel.setAuction("auction");
		bidModel.setAuctionItemId(auctionId);
		bidModel.setBidIntegral(bidIntegral);
		DcUserIntegralDetailDTO integralDto = dcUserIntegralBO.doAuctionBid(bidModel);
		bidMessage = DcConvertUtils.convertToDcMessage(integralDto);
		bidMessage.setGmtCreate(new Date());
		//���۵�
		if (bidIntegral < auctionItem.getNextBidIntegral()) {
			bidMessage.setOtherInfo("bid.lower");
			return bidMessage;
		}
		int count = dcAuctionItemService.updateBidInfo(auctionId, user, bidIntegral);
		if (count > 0) {
			//�����û�����
			dcUserService.unfrozenIntegralByUser(auctionItem.getBidUserId(), auctionItem.getBidIntegral());
			dcUserService.frozenIntegralByUser(user.getUserId(), bidIntegral);
			bidMessage.setOtherInfo("success");
		} else {
			bidMessage.setOtherInfo("bid.lower");
		}
		return bidMessage;
	}
	
	/**
	 * 
	 * @param auctionId
	 * @return
	 */
	public DcMessageDTO getLatestBidDetail(Long auctionId) {
		DcAuctionItemDTO auctionItem = dcAuctionItemService.getAuctionItem(auctionId);
		if (auctionItem == null) {
			return null;
		}
		DcMessageDTO bidDetail = new DcMessageDTO();
		bidDetail.setUserId(auctionItem.getBidUserId());
		bidDetail.setUserNick(auctionItem.getBidUserNick());
		bidDetail.setActivity(DcIntegralSourceEnums.AUCTION_BID.getValue());
		bidDetail.setIntegral(auctionItem.getBidIntegral());
		bidDetail.setRelateObjId(auctionItem.getId());
		bidDetail.setRelateObjName("auction");
		bidDetail.setGmtCreate(new Date());
		return bidDetail;
	}
	
	/**
	 * ��ȡ������bidIntegral֮�ϵĳ��ۼ�¼.
	 * @param auctionId
	 * @param bidIntegral
	 * @return
	 */
	public List<DcMessageDTO> getAuctionHistories(Long auctionId, Integer bidIntegral) {
		List<DcMessageDTO> auctionBids = dcMessageService.getAuctionMessage(auctionId);
		orderByBidIntegral(auctionBids);
		removeUselessBids(auctionBids, bidIntegral);
		return auctionBids;
	}
	
	/**
	 * ��ȡ����������ʱ.
	 * @return
	 */
	public Long getAuctionCountdownTime() {
		List<DcAuctionItemDTO> auctionItems =
			dcAuctionItemService.queryAuctionItems(DcPromScheduleEnum.ONGOING, new Pagination(1, 1));
		if (CollectionUtils.isEmpty(auctionItems)) {
			auctionItems = dcAuctionItemService.queryAuctionItems(DcPromScheduleEnum.FUTURE, new Pagination(1, 1));
		}
		if (CollectionUtils.isNotEmpty(auctionItems)) {
			return auctionItems.get(0).getCountdownTimeStamp();
		}
		return null;
	}
	
	private void orderByBidIntegral(List<DcMessageDTO> auctionBids) {
		if (CollectionUtils.isEmpty(auctionBids)) {
			return;
		}
		Comparator<DcMessageDTO> comparator = new Comparator<DcMessageDTO>() {
			@Override
			public int compare(DcMessageDTO o1, DcMessageDTO o2) {
				return o1.getIntegral() - o2.getIntegral();
			}
		};
		Collections.sort(auctionBids, comparator);
	}
	
	/**
	 * �Ƴ�������ĳ��ۼ�¼�����������ظ��ļ�¼�ͳ��۵͵ļ�¼
	 * @param auctionBids
	 * @param bidIntegral
	 */
	private void removeUselessBids(List<DcMessageDTO> auctionBids, Integer bidIntegral) {
		if (CollectionUtils.isEmpty(auctionBids)) {
			return;
		}
		//���˳���С��bidIntegral�ļ�¼.
		for (int i = auctionBids.size() - 1; i >= 0; i--) {
			if (bidIntegral >= auctionBids.get(i).getIntegral()) {
				auctionBids.remove(i);
			}
		}
		//���˳����ظ��ļ�¼.
		if (auctionBids.size() > 1) {
			Integer tempInegral = auctionBids.get(auctionBids.size() - 1).getIntegral();
			for (int i = auctionBids.size() - 2; i >= 0; i--) {
				if (tempInegral.equals(auctionBids.get(i).getIntegral())) {
					auctionBids.remove(i);
				} else {
					tempInegral = auctionBids.get(i).getIntegral();
				}
			}
		}
		while (auctionBids.size() > 10) {
			auctionBids.remove(auctionBids.size() - 1);
		}
	}
}