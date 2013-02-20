package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils.BitEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionItemNewGuideEnums;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 用户新手引导标志
 * @author langben 2012-9-6
 *
 */
@SuppressWarnings("serial")
public class DcPromotionItemNewGuideOperateAjaxAction  extends DComeBasicAction {

	private String guideName ;
	
	private String guideNames ;
	
	private Long promItemId ;
	
	private JsonModel<Boolean> json = new JsonModel<Boolean>();
	
	@Autowired
	private DcPromotionItemService dcPromotionItemService ;
	
	@Override
	public String execute() throws Exception {
		
		List<String> guideNameList = ArrayStringUtils.toList(guideNames) ;
		if(guideNameList == null){
			guideNameList = new ArrayList<String>() ;
		}
		if(StringUtils.isNotBlank(guideName)){
			guideNameList.add(guideName) ;
		}
		
		if(CollectionUtils.isEmpty(guideNameList)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promotionItem.newGuide.name.required") ;
			return SUCCESS;
		}
		
		long userId = dcAuthz.getUserId() ;
		if(IDUtils.isNotCorrect(userId) || IDUtils.isNotCorrect(promItemId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.user.userAndPromItem.required") ;
			return SUCCESS;
		}
		
		
		
		
		try {
			
			DcPromotionItemDTO promItemDTO = dcPromotionItemService.getPromotionItemById(promItemId) ;
			boolean userIdEqual = Long.valueOf(userId).equals(promItemDTO.getUserId()) ;
			if(promItemDTO == null ||  !userIdEqual){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.user.userAndPromItem.required") ;
				return SUCCESS;
			}
			//设置对应的标志位
			long guideValue = promItemDTO.getNewGuide() ;
			for(String guideNameStr : guideNameList){
				DcPromotionItemNewGuideEnums guideEnums = DcPromotionItemNewGuideEnums.getInstanceByName(guideNameStr) ;
				if(guideEnums == DcPromotionItemNewGuideEnums.UNKNOWN){
					continue ;
				}
				boolean isGuided = promItemDTO.isGuided(guideNameStr) ;
				if(!isGuided){
					//将对应位设置值
					guideValue = BitOperateUtils.setBit(guideValue, guideEnums.getIndex(), BitEnums.bit1) ;
				}
			}
			
			dcPromotionItemService.updateNewGuide(promItemId, guideValue) ;
			
			json.setCode(JsonModel.CODE_SUCCESS) ;
		}catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public void setPromItemId(Long promItemId) {
		this.promItemId = promItemId;
	}

	public void setGuideNames(String guideNames) {
		this.guideNames = guideNames;
	}
		
}
