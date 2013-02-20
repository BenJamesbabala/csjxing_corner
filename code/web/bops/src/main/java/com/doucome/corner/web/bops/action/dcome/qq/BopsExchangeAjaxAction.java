package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcExchangeBO;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.model.param.DcNewExItemModel;
import com.doucome.corner.biz.dcome.service.DcExchangeItemService;
import com.doucome.corner.biz.dcome.utils.DcNumberUtils;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 积分兑换ajax.
 * @author ze2200
 *
 */
public class BopsExchangeAjaxAction extends BopsBasicAction {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DcExchangeBO dcExchangeBO;
	@Autowired
	private DcExchangeItemService dcExchangeItemService;

	private JsonModel<Map<String, Object>> json = new JsonModel<Map<String, Object>>();
	
	private DcExchangeItemDTO exchangeItem = new DcExchangeItemDTO();
	
	/**
	 * 添加积分兑换商品.
	 * @return
	 */
	public String addToExchange() {
		if (IDUtils.isNotCorrect(exchangeItem.getItemId())) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.param");
			return SUCCESS;
		}
		DcNewExItemModel newModel = new DcNewExItemModel();
		newModel.setPublic(true);
		ResultModel<Long> result = dcExchangeBO.addDcItemToExchange(exchangeItem.getItemId(), new DcNewExItemModel());
		if (result.isSuccess()) {
			json.setSuccess(result.getCode(), new HashMap<String, Object>());
		} else {
			json.setFail(JsonModel.CODE_FAIL, result.getDetail());
		}
		return SUCCESS;
	}
	
	/**
	 * 更新积分兑换.
	 * @return
	 */
	public String updateExchangeInfo() {
		int count = dcExchangeItemService.initExchangeInfo(exchangeItem);
		if (count > 0) {
			json.setSuccess(JsonModel.CODE_SUCCESS, new HashMap<String, Object>());
		} else {
			json.setFail(JsonModel.CODE_FAIL, "cant.find.exchangeitem");
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String delExchangeItem() {
		if (IDUtils.isNotCorrect(exchangeItem.getId())) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.param");
			return SUCCESS;
		}
		ResultModel<Integer> result = dcExchangeBO.delExchangeItem(exchangeItem.getId());
		if (result.isSuccess()) {
			json.setSuccess(JsonModel.CODE_SUCCESS, new HashMap<String, Object>());
		} else {
			json.setFail(result.getCode(), result.getDetail());
		}
		return SUCCESS;
	}
	
	/**
	 * 获取兑换明细.
	 * @return
	 */
	public String queryExchangeDetail() {
		if (IDUtils.isNotCorrect(exchangeItem.getId())) {
			json.setFail(JsonModel.CODE_FAIL, "param.error");
			return SUCCESS;
		}
		Map<String, Object> details = dcExchangeBO.getExchangeDetail(exchangeItem.getId());
		json.setSuccess(JsonModel.CODE_SUCCESS, details);
		return SUCCESS;
	}

	public JsonModel<Map<String, Object>> getJson() {
		return json;
	}

	public void setItemId(Long itemId) {
		exchangeItem.setItemId(itemId);
	}
	
	public void setExCount(Integer exCount) {
		exchangeItem.setExCount(exCount);
	}

	public void setExchangeId(Long exchangeId) {
		exchangeItem.setId(exchangeId);
	}

	public void setExIntegral(Integer exIntegral) {
		exchangeItem.setExIntegral(exIntegral);
	}

	public void setItemType(String itemType) {
		exchangeItem.setItemType(itemType);
	}
}
