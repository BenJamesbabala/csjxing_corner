package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * pgc商品参与活动异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcPromItemRankAjaxAction extends QBasicAction {
	
	private Long promItemId;
	
	private JsonModel<Integer> json = new JsonModel<Integer>();
	@Autowired
	private DcPromotionItemService dcPromotionItemService;
	
	private static final Log logger = LogFactory.getLog(DcPromItemRankAjaxAction.class);
	
	public String execute() {
		if (!IDUtils.isCorrect(promItemId) || !IDUtils.isCorrect(getUserId())) {
			json.setFail(JsonModel.CODE_FAIL, "illegal.id");
		}
		DcPromotionDTO promotion = getPromotion();
		if (promotion == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.promotion");
		}
		try {
			Integer rank = dcPromotionItemService.getPromItemRank(promotion.getId(), promItemId);
			json.setSuccess(JsonModel.CODE_SUCCESS, rank);
		} catch (Exception e) {
			logger.error(e);
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	public JsonModel<Integer> getJson() {
		return json;
	}
	
	public void setPromItemId(Long promItemId) {
		this.promItemId = promItemId;
	}
}
