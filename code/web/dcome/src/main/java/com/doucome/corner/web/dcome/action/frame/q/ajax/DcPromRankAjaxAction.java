package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 活动拍名异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcPromRankAjaxAction extends QBasicAction {
	
//	private Integer topN;
	
	private JsonModel<List<DcPromotionItemDTO>> json = new JsonModel<List<DcPromotionItemDTO>>();
	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	public String execute() {
		if (getUserId() == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		DcPromotionDTO promotion = getPromotion();
		if (promotion == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.promotion");
			return SUCCESS;
		}
		List<DcPromotionItemDTO> rankPromItems = dcPromotionBO.getPromotionTopRanks(promotion.getId(), 5);
		json.setSuccess(JsonModel.CODE_SUCCESS, rankPromItems);
		return SUCCESS;
	}
	
	public JsonModel<List<DcPromotionItemDTO>> getJson() {
		return json;
	}
	
//	public void setTopN(Integer topN) {
//		this.topN = topN;
//	}
}
