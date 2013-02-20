package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcPromotionAwardBO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 分享获奖
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcDrawAwardAjaxAction extends QBasicAction {
	
	private JsonModel<DcPromotionAwardDTO> json = new JsonModel<DcPromotionAwardDTO>();
	
	@Autowired
	private DcPromotionAwardBO dcPromotionAwardBO;
	
	private Long awardId;
	
	private String inviteeOpenIds;
	
	/**
	 * 分享获奖.
	 * @return
	 */
	public String shareAward() {
		DcUserDTO user = getUser();
		if (user == null || IDUtils.isNotCorrect(awardId)) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.param");
			return SUCCESS;
		}
		ResultModel<String> result = dcPromotionAwardBO.shareAward(user, awardId, inviteeOpenIds);
		if (result.isSuccess()) {
			json.setSuccess(result.getCode(), null);
		} else {
			json.setFail(result.getCode(), result.getDetail());
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String queryAwardAddr() {
		Long userId = getUserId();
		if (userId == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		ResultModel<DcPromotionAwardDTO> result = dcPromotionAwardBO.queryAwardAddr(awardId, userId);
		if (result.isSuccess()) {
			json.setSuccess(JsonModel.CODE_SUCCESS, result.getData());
		} else {
			json.setFail(JsonModel.CODE_FAIL, result.getDetail());
		}
		return SUCCESS;
	}
	
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	
	public void setInviteeOpenIds(String inviteeOpenIds) {
		this.inviteeOpenIds = inviteeOpenIds;
	}

	public JsonModel<DcPromotionAwardDTO> getJson() {
		return json;
	}
}
