package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcGameBO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.param.DcGameAwardModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 
 * @author ze2200
 *
 */
public class DcGameAjaxAction extends QBasicAction {
	
	private static final long serialVersionUID = -5626438995204282080L;
	
	private JsonModel<DcGameAwardModel> json = new JsonModel<DcGameAwardModel>();
	@Autowired
	private DcGameBO dcGameBO;
	
	/**
	 * ÔÒ½ðµ°
	 * @return
	 */
	public String smashEgg() {
		Long userId = getUserId();
		DcPromotionItemDTO promItem = getMyPromotionItem();
		if (IDUtils.isNotCorrect(userId) || promItem == null) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.userId|promItemId");
			return SUCCESS;
		}
		ResultModel<DcGameAwardModel> result = dcGameBO.smashEgg(userId, promItem.getId());
		if (result.isSuccess()) {
			json.setSuccess(result.getCode(), result.getData());
		} else {
			json.setFail(result.getCode(), result.getDetail());
		}
		return SUCCESS;
	}
	
	public JsonModel<DcGameAwardModel> getJson() {
		return this.json;
	}

}
