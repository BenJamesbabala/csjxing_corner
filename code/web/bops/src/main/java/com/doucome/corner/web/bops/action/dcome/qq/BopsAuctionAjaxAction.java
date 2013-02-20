package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dcome.constant.DcAwardConstant;
import com.doucome.corner.biz.dcome.enums.DcAuctionReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionTypeEnum;
import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcAuctionItemService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

public class BopsAuctionAjaxAction extends BopsBasicAction {

	@Autowired
	private DcItemService dcItemService;
	
	@Autowired
	private DcAuctionItemService dcAuctionItemService;
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService;
	
	@Autowired
	private DcUserService dcUserService ;

	private JsonModel<String> json = new JsonModel<String>();
	private Long itemId;

	private Long auctionId;
	private Integer baseIntegral;
	private Integer integralPer;
	private Date gmtStart;
	private Date gmtEnd;

	public String addToAuction() {
		if (itemId == null) {
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("itemId required");
			return SUCCESS;
		}

		DcItemDTO itemDTO = dcItemService.getItemById(itemId);
		if (itemDTO == null) {
			json.setCode(JsonModel.CODE_FAIL);
			json.setDetail("item not found!");
		}

		DcAuctionItemDO auctionDO = new DcAuctionItemDO();
		auctionDO.setItemId(itemDTO.getId());
		auctionDO.setItemPrice(itemDTO.getItemPrice());
		auctionDO.setItemTitle(itemDTO.getItemTitle());
		auctionDO.setItemPictures(itemDTO.getPicUrls());
		auctionDO.setReviewStatus(DcAuctionReviewStatusEnums.UNVIEW.getStatus());

		dcAuctionItemService.insertAuctionItem(auctionDO);
		json.setCode(JsonModel.CODE_SUCCESS);
		return SUCCESS;
	}

	public String updateAuction() {
		if (auctionId == null || baseIntegral == null || integralPer == null
				|| gmtStart == null || gmtEnd == null) {
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("auctionId、baseIntegral、integralPer、gmtStart、gmtEnd required");
			return SUCCESS;
		}

		DcAuctionItemDTO auctionDTO = dcAuctionItemService
				.getAuctionItem(auctionId);
		auctionDTO.setBaseIntegral(baseIntegral);
		auctionDTO.setIntegralPer(integralPer);
		auctionDTO.setGmtStart(gmtStart);
		auctionDTO.setGmtEnd(gmtEnd);
		dcAuctionItemService.updatePromoInfoById(auctionDTO);
		json.setCode(JsonModel.CODE_SUCCESS);
		return SUCCESS;
	}
	
	/**
	 * 开奖
	 * @return
	 */
	public String kaijiang(){
		if (IDUtils.isNotCorrect(auctionId)){
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("dcome.auction.id.required");
			return SUCCESS;
		}
		
		DcAuctionItemDTO auctionDTO = dcAuctionItemService.getAuctionItem(auctionId) ;
		
		if(auctionDTO == null){
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("dcome.auction.id.required");
			return SUCCESS;
		}
		
		String reviewStatus = auctionDTO.getReviewStatus() ;
		
		//已经审核了，也算审核成功
		if(StringUtils.equals(reviewStatus , DcAuctionReviewStatusEnums.SUCCESS.getStatus())) {
			json.setCode(JsonModel.CODE_SUCCESS);
			return SUCCESS ;
		}

		try {
			
			//插入一条中奖记录给用户
			Integer bidIntegral = auctionDTO.getBidIntegral() ;
			String bidUserNick = auctionDTO.getBidUserNick() ;
			Long bidUserId = auctionDTO.getBidUserId() ;
			Long itemId = auctionDTO.getItemId() ;
			
			DcPromotionAwardDO awardDO = new DcPromotionAwardDO() ;
			awardDO.setItemId(itemId) ;
			awardDO.setPromotionId(auctionDTO.getId()) ;
			awardDO.setPromotionItemId(DcAwardConstant.INVALID_ITEM_ID) ;
			awardDO.setPromotionType(DcPromotionTypeEnum.AUCTION.getType()) ;
			awardDO.setSendStatus(DcPromotionAwardSendStatusEnums.UNSEND.getStatus()) ;
			awardDO.setCheckCode(UUIDUtils.random20Num()) ;
			awardDO.setRateCount(bidIntegral);
			awardDO.setUserId(bidUserId);
			awardDO.setUserNick(bidUserNick) ;
			awardDO.setReviewStatus(DcPromotionAwardReviewStatusEnums.SUCCESS.getStatus()) ;
			long createAwardId = dcPromotionAwardService.createAward(awardDO);
			
			//扣除冻结积分
			if(bidIntegral == null ){
				bidIntegral = 0 ;
			}
			
			dcUserService.descFrozenIntegralByUser(bidUserId, bidIntegral) ;
			
			int effectCount = dcAuctionItemService.updateReviewStatusById(auctionId, DcAuctionReviewStatusEnums.SUCCESS) ;
			if(effectCount > 0){
									
				//给用户发送中奖消息
				dcUserService.incrUnreadMsgCountByUser(bidUserId, 1) ; //添加一条未读消息
				json.setCode(JsonModel.CODE_SUCCESS);
				
			} else {
				json.setCode(JsonModel.CODE_FAIL);
				json.setDetail("dcome.auction.review.fail");
			}
		} catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL);
			json.setDetail(e.getMessage());
		}
		return SUCCESS ;
	}

	public JsonModel<String> getJson() {
		return json;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setBaseIntegral(int baseIntegral) {
		this.baseIntegral = baseIntegral;
	}

	public void setIntegralPer(int integralPer) {
		this.integralPer = integralPer;
	}

	public void setGmtStart(Date gmtStart) {
		this.gmtStart = gmtStart;
	}

	public void setGmtEnd(Date gmtEnd) {
		this.gmtEnd = gmtEnd;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

}
