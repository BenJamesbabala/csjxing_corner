package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dcome.business.DcPromotionAwardBO;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 奖品异步接口类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcAwardAjaxAction extends DComeBasicAction {
	
	private JsonModel<List<DcPromotionAwardDTO>> json = new JsonModel<List<DcPromotionAwardDTO>>();
	
	@Autowired
	private DcPromotionAwardBO dcPromotionAwardBO;
	
	public String execute() {
		DcPromotionAwardCondition awardCondition = new DcPromotionAwardCondition();
		awardCondition.setReviewStatus(DcPromotionAwardReviewStatusEnums.SUCCESS.getStatus());
		List<DcPromotionAwardDTO> awardList = dcPromotionAwardBO.getAwardDetails(awardCondition, new Pagination(1 , 15));
		if (awardList != null) {
			json.setSuccess(JsonModel.CODE_SUCCESS, awardList);
		} else {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}

	public JsonModel<List<DcPromotionAwardDTO>> getJson() {
		return json;
	}
}
