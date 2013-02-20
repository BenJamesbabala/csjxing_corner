package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.service.DcMessageService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 
 * @author ze2200
 *
 */
public class DcGrepevineAjaxAction extends DComeBasicAction {
	
	private static final long serialVersionUID = 1L;

	private JsonModel<Boolean> json = new JsonModel<Boolean>();
	
	@Autowired
	private DcMessageService dcMessageService;
	
	private String type;
	
	private String message;
	
	public String execute() {
		try {
			Long userId = getUserId();
			if (userId == null || (userId != 10003l && userId != 10008l)) {
				json.setFail(JsonModel.CODE_FAIL, "");
				return SUCCESS;
			}
			if (StringUtils.isEmpty(message)) {
				json.setFail(JsonModel.CODE_FAIL, "no.message");
				return SUCCESS;
			}
			DcIntegralSourceEnums source = DcIntegralSourceEnums.get(type);
			Boolean result = dcMessageService.addAppGrepevine(message, source);
			json.setSuccess(JsonModel.CODE_SUCCESS, result);
		} catch (Exception e) {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public JsonModel<Boolean> getJson() {
		return this.json;
	}
}
