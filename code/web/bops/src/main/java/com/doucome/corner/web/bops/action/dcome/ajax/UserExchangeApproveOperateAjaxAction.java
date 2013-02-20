package com.doucome.corner.web.bops.action.dcome.ajax;

import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.service.DcUserExchangeApproveService;
import com.doucome.corner.biz.dcome.service.DcUserIntegralRecordService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

@SuppressWarnings("serial")
public class UserExchangeApproveOperateAjaxAction extends BopsBasicAction {

	@Autowired
	private DcUserExchangeApproveService dcUserExchangeApproveService ;
	
	@Autowired
	private DcUserIntegralRecordService dcUserIntegralRecordService ;
	
	private String userMemo ;
	
	private String toStatus ;
	
	private Long approveId ;
	
	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Override
	public String execute() throws Exception {
		
		DcExchangeApproveStatusEnums statusEnum = DcExchangeApproveStatusEnums.get(toStatus) ;
		
		if(statusEnum == DcExchangeApproveStatusEnums.UNKNOWN) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.approve.status.error") ;
			return SUCCESS ;
		}
		
		if(IDUtils.isNotCorrect(approveId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.exchange.approve.id.error") ;
			return SUCCESS ;
		}
		
		if(StringUtils.isNotBlank(userMemo)) {
			userMemo = URLDecoder.decode(userMemo, "UTF-8") ;
		} else {
			userMemo = null ;
		}
		
		dcUserExchangeApproveService.updateStatusAndUserMemoById(approveId, statusEnum, userMemo) ;
		dcUserIntegralRecordService.updateStatusAndUserMemoByExApproveId(approveId, statusEnum, userMemo) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		return SUCCESS ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}

	public void setApproveId(Long approveId) {
		this.approveId = approveId;
	}
	
	
}
