package com.doucome.corner.web.bops.action.dcome.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcPromItemModel;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;

public class TakeinPromotionAjaxAction extends BopsBasicAction {

	private JsonModel<String> json = new JsonModel<String>() ;
	
	private Long userId ;
	
	private Long itemId ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Autowired
	private DcPromotionBO dcPromotionBO ;
	
	@Override
	public String execute() throws Exception {
		
		if(IDUtils.isNotCorrect(userId) || IDUtils.isNotCorrect(itemId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.userOrItem.required") ;
			return SUCCESS ;
		}
		
		try {
			
			ResultModel<Long> checkResult = dcPromotionBO.dcItemTakeinPromCheck(itemId, userId);
			
			DcUserDTO user = dcUserService.getUser(userId) ;
			
			if(user == null){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.user.required") ;
				return SUCCESS ;
			}
			
			if (!checkResult.isSuccess()) {
				json.setFail(checkResult.getCode(), checkResult.getDetail());
				json.setData(String.valueOf(checkResult.getData()));
				return SUCCESS;
			}
			
			DcPromItemModel pkItem = new DcPromItemModel();
			pkItem.setItemId(itemId);
			pkItem.setUserId(userId);
			pkItem.setUserNick(user.getNick());
			ResultModel<Long> result = dcPromotionBO.takeinPromotion(pkItem);
			json.setData(result.getCode()) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<String> getJson() {
		return json;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	
}
