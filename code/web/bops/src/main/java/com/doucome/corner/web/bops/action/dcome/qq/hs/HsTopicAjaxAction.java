package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.service.horoscope.HsTopicService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * »’‘À ∆
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsTopicAjaxAction extends BopsBasicAction {

	private JsonModel<Object> json = new JsonModel<Object>();
	@Autowired
	private HsTopicService hsTopicService;
	
	private Long topicId;
	
	public String deleteHsTopic() {
		int count = hsTopicService.deleteHsTopic(topicId);
		if (count == 1) {
			json.setSuccess(JsonModel.CODE_SUCCESS, "1");
		} else {
			json.setFail(JsonModel.CODE_FAIL, String.valueOf(count));
		}
		return SUCCESS;
	}

	public JsonModel<Object> getJson() {
		return json;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
}
