package com.doucome.corner.web.bops.action.dcome.prom;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.service.DcMessageService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 积分兑换ajax.
 * @author ze2200
 *
 */
public class BopsMessageAjaxAction extends BopsBasicAction {
	
	private static final long serialVersionUID = 1L;

	private JsonModel<String> json = new JsonModel<String>();
	
	@Autowired
	private DcMessageService dcMessageService;
	
	private Long userId;
	
	private String message;
	
	/**
	 * 向用户发送消息.
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String sendMessageToUser() throws UnsupportedEncodingException {
		message = URLDecoder.decode(message, "UTF-8");
		Long id = dcMessageService.sendMessageToUser(userId, message);
		if (id > 0l) {
			json.setSuccess(JsonModel.CODE_SUCCESS, String.valueOf(id));
		} else {
			json.setFail(JsonModel.CODE_FAIL, "");
		}
		return SUCCESS;
	}

	public JsonModel<String> getJson() {
		return json;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
