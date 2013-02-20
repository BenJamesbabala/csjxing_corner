package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcExchangeBO;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 积分竞价异步接口类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcExchangeAjaxAction extends DComeBasicAction {
	
	private JsonModel<Integer> json = new JsonModel<Integer>();
	@Autowired
	private DcExchangeBO dcExchangeBO;
	
	private Long exchangeId;
	
	private Integer exCount;
	@Override
	public String execute() {
		DcUserDTO user = getUser();
		if (IDUtils.isNotCorrect(exchangeId) || user == null) {
			json.setFail(JsonModel.CODE_FAIL, "param.error");
			return SUCCESS;
		}
		ResultModel<Integer> result = dcExchangeBO.exchangeItem(user, exchangeId, exCount);
		if (result.isSuccess()) {
			json.setSuccess(result.getCode(), result.getData());
		} else {
			json.setFail(result.getCode(), result.getDetail());
			json.setData(result.getData());
		}
 		return SUCCESS;
	}
	
	public void setExchangeId(Long exchangeId) {
		this.exchangeId = exchangeId;
	}
	
	public void setExCount(Integer exCount) {
		this.exCount = exCount;
	}
	
	public JsonModel<Integer> getJson() {
		return json;
	}
}
