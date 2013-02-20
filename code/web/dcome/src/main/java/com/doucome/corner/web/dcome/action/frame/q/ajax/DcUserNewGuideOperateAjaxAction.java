package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils.BitEnums;
import com.doucome.corner.biz.dcome.enums.DcUserNewGuideEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 用户新手引导标志
 * @author langben 2012-9-6
 *
 */
@SuppressWarnings("serial")
public class DcUserNewGuideOperateAjaxAction  extends DComeBasicAction {

	private String guideName ;
	
	private JsonModel<Boolean> json = new JsonModel<Boolean>();
	
	@Autowired
	private DcUserService dcUserService ;
	
	public String addGuide() throws Exception {
		
		DcUserNewGuideEnums guideEnums = DcUserNewGuideEnums.getInstanceByName(guideName) ;
		if(guideEnums == DcUserNewGuideEnums.UNKNOWN){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.user.newGuide.name.required") ;
			return SUCCESS;
		}
		
		long userId = dcAuthz.getUserId() ;
		if(IDUtils.isNotCorrect(userId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.user.userId.required") ;
			return SUCCESS;
		}
		try {
			DcUserDTO userDTO = dcUserService.getUser(userId) ;
			
			boolean isGuided = userDTO.isGuided(guideName) ;
			if(!isGuided){
				long guideValue = userDTO.getNewGuide() ;
				//将对应位设置值
				guideValue = BitOperateUtils.setBit(guideValue, guideEnums.getIndex(), BitEnums.bit1) ;
				dcUserService.updateNewGuide(userId, guideValue) ;
			}
			
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
	
	
}
