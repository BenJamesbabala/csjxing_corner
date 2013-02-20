package com.doucome.corner.web.dcome.action.frame.q;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

@SuppressWarnings("serial")
public class QBasicAction extends DComeBasicAction {

	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	@Autowired
	private DcPromotionService  dcPromotionService ;
	
	private DcPromotionDTO promotion ;
	private boolean promotion_init = false ;
	
	private DcPromotionItemDTO myPromotionItem ;
	private boolean myPromotionItem_init = false ;
	
	private DcUserDTO userDTO ;
	private boolean userDTO_init = false ;
	
	public DcPromotionDTO getPromotion(){
		if(promotion_init){
			return promotion ;
		}
		promotion = dcPromotionService.getCurPromotion();
		promotion_init = true ;
		return promotion ;
	}
	
	
	public DcPromotionItemDTO getMyPromotionItem() {
		if(myPromotionItem_init){
			return myPromotionItem ;
		}
		myPromotionItem = dcPromotionBO.getUserCurPromotionItem(getUserId());
		myPromotionItem_init = true;
		return myPromotionItem ;
	}
	
	@Override
	public DcUserDTO getUser(){
		if(userDTO_init){
			return userDTO ;
		}
		userDTO = super.getUser() ;
		userDTO_init = true ;
		return userDTO ;
	}
	
}
