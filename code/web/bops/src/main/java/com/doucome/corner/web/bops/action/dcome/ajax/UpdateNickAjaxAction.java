package com.doucome.corner.web.bops.action.dcome.ajax;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

public class UpdateNickAjaxAction extends BopsBasicAction {

	private JsonModel<Integer> json = new JsonModel<Integer>() ;
	
	private Long promItemId ;
	
	private String userNick ;
	
	@Autowired
	private DcPromotionItemService dcPromotionItemService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Override
	public String execute() throws Exception {
		if(IDUtils.isNotCorrect(promItemId)) {
			
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promItemId.required") ;
			return SUCCESS ;
		
		}
		
		if(StringUtils.isBlank(userNick)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.user.nick.required") ;
			return SUCCESS ;
		}
		
		userNick = URLDecoder.decode(userNick, "utf-8") ;
		
		DcPromotionItemDTO promotionItemDTO = dcPromotionItemService.getPromotionItemById(promItemId) ;
		if(promotionItemDTO == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promItemId.required") ;
			return SUCCESS ;
		}
		
		Long userId = promotionItemDTO.getUserId() ;
		try {
			dcUserService.updateNick(userId, userNick) ;
			dcPromotionItemService.updateUserNickById(promItemId, userNick) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
		} catch (DcDuplicateKeyException e){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.user.nick.duplicate") ;
			return SUCCESS ;
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		
		return SUCCESS ;
	}

	public void setPromItemId(Long promItemId) {
		this.promItemId = promItemId;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
}
