package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;
import com.doucome.corner.biz.dcome.service.DcMessageService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

@SuppressWarnings("serial")
public class DcMessageAjaxAction extends DComeBasicAction {
	
	private JsonModel<List<DcMessageDTO>> json = new JsonModel<List<DcMessageDTO>>();
	@Autowired
	private DcMessageService dcMessageService;
	
	private int page;
	
	private static final Log logger = LogFactory.getLog(DcMessageAjaxAction.class);
	
	/**
	 * 获取用户信息.
	 * @return
	 */
	public String queryUserMessages() {
		try {
			Long userId = getUserId();
			if (!IDUtils.isCorrect(userId)) {
				json.setSuccess(JsonModel.CODE_SUCCESS, new ArrayList<DcMessageDTO>());
				return SUCCESS;
			}
			List<DcMessageDTO> userMessages = dcMessageService.getUserMessages(userId, page);
			json.setSuccess(JsonModel.CODE_SUCCESS, userMessages);
		} catch(Exception e) {
			logger.error(e);
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String queryAppMessages() {
		try {
			List<DcMessageDTO> appMessages = dcMessageService.getAppMessages(1);
			json.setSuccess(JsonModel.CODE_SUCCESS, appMessages);
		} catch (Exception e) {
			logger.error(e);
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	/**
	 * 获取系统通知.
	 * @return
	 */
	public String queryNotifyMessages() {
		try {
			List<DcMessageDTO> messages = dcMessageService.getAppGrepevines();
			json.setSuccess(JsonModel.CODE_SUCCESS, messages);
		} catch(Exception e) {
			logger.error(e);
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	public JsonModel<List<DcMessageDTO>> getJson() {
		return json;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
}
