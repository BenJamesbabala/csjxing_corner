package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcAutoExchangeModel;
import com.doucome.corner.biz.dcome.service.DcAutoExchangeService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcAutoExchangeSubmitAjaxAction extends QBasicAction {
	
	private JsonModel<String> json = new JsonModel<String>();
	
	private DcAutoExchangeModel exchangeModel = new DcAutoExchangeModel();
	
	@Autowired
	private DcAutoExchangeService dcAutoExchangeService;
	
	/**
	 * 
	 */
	public String execute() {
		DcUserDTO user = getUser();
		if (user == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		ResultModel<Boolean> tempModel = exchangeModel.validate();
		if (!tempModel.isSuccess()) {
			json.setFail(JsonModel.CODE_FAIL, tempModel.getDetail());
			return SUCCESS;
		}
		if (exchangeModel.getExIntegral() > user.getIntegralCount()) {
			json.setFail(JsonModel.CODE_FAIL, "integral.less");
			json.setData(String.valueOf(exchangeModel.getExIntegral()));
			return SUCCESS;
		}
		exchangeModel.setUserId(user.getUserId());
		exchangeModel.setUserNick(user.getNick());
		Long id = dcAutoExchangeService.createAutoExchange(exchangeModel);
		json.setSuccess(JsonModel.CODE_SUCCESS, String.valueOf(id));
		return SUCCESS;
	}
	
	public JsonModel<String> getJson() {
		return json;
	}
	
	public void setItemUrl(String itemUrl) throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(itemUrl)) {
			exchangeModel.setItemUrl(URLDecoder.decode(itemUrl, "utf-8"));
		}
	}
	
	public void setItemSize(String itemSize) throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(itemSize)) {
			exchangeModel.setItemSize(URLDecoder.decode(itemSize, "utf-8"));
		}
	}
	
	public void setItemColor(String itemColor) throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(itemColor)) {
			exchangeModel.setItemColor(URLDecoder.decode(itemColor, "utf-8"));
		}
	}
	
	public void setItemPrice(String itemPrice) throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(itemPrice)) {
			exchangeModel.setItemPrice(URLDecoder.decode(itemPrice, "utf-8"));
		}
	}
	
	public void setPostalFee(String postalFee) throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(postalFee)) {
			exchangeModel.setPostalFee(URLDecoder.decode(postalFee, "utf-8"));
		}
	}
	
	public void setMemo(String memo) throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(memo)) {
		    exchangeModel.setMemo(URLDecoder.decode(memo, "utf-8"));
		}
	}
}
