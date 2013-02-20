package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dcome.business.DcUserShareBO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 分享ajax.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcShareAjaxAction extends QBasicAction {
	
	private JsonModel<Integer> json = new JsonModel<Integer>();
	
	private Long id;
	
	private String inviteeOpenIds;
	
	@Autowired
	private DcUserShareBO dcUserShareBO;
	
	/**
	 * 分享活动.
	 * @return
	 */
	public String sharePromotion() {
		DcPromotionItemDTO promItem = getMyPromotionItem();
		if (promItem == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.promitem");
			return SUCCESS;
		}
		if (promItem.isShared()) {
			json.setFail(JsonModel.CODE_FAIL, "repeat.share");
			return SUCCESS;
		}
		ResultModel<Integer> result = dcUserShareBO.sharePromotion(getUser(), promItem);
		if (result.isSuccess()) {
			json.setSuccess(result.getCode(), result.getData());
		} else {
			json.setFail(result.getCode(), result.getDetail());
		}
		return SUCCESS;
	}
	
	/**
	 * 分享应用.
	 * @return
	 */
	public String shareApp() {
		try {
			DcUserDTO user = getUser();
			Date now = Calendar.getInstance().getTime();
			if (DateUtils.isSameDay(user.getGmtLastShare(), now)) {
				json.setFail(JsonModel.CODE_FAIL, "share.repeat");
				return SUCCESS;
			}
			Integer integral = dcUserShareBO.shareApp(user);
			json.setSuccess(JsonModel.CODE_SUCCESS, integral);
		} catch (Exception e) {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	/**
	 * 分享应用.
	 * @return
	 */
	public String shareDcItem() {
		try {
			if (IDUtils.isNotCorrect(id)) {
				json.setFail(JsonModel.CODE_FAIL, "invalid.id");
				return SUCCESS;
			}
			DcUserDTO user = getUser();
			Integer integral = dcUserShareBO.shareDcItem(user, id);
			json.setSuccess(JsonModel.CODE_SUCCESS, integral);
		} catch (Exception e) {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	/**
	 * 向朋友发送邀请.
	 * @return
	 */
	public String sendFriendInvite() {
		try {
			if (StringUtils.isEmpty(inviteeOpenIds)) {
				json.setFail(JsonModel.CODE_FAIL, "no.invitee");
				return SUCCESS;
			}
			DcUserDTO user = getUser();
			Integer integral = dcUserShareBO.sendFriendInvite(user, inviteeOpenIds);
			json.setSuccess(JsonModel.CODE_SUCCESS, integral);
		} catch (Exception e) {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	/**
	 * 判断用户今日是否能分享活动.
	 * @return
	 */
//	public String isUserCanSharePromotion() {
//		DcPromotionItemDTO promItem = getMyPromotionItem();
//		boolean canShare = promItem != null && DcShareStatusEnum.NO.getValue().equals(promItem.getShareStatus());
//		json.setSuccess(JsonModel.CODE_SUCCESS, canShare);
//		return SUCCESS;
//	}
	
	/**
	 * 判断用户今日是否分享了应用.
	 * @return
	 */
//	public String isUserSharedApp() {
//		DcUserDTO user = getUser();
//		Date now = Calendar.getInstance().getTime();
//		json.setSuccess(JsonModel.CODE_SUCCESS, DateUtils.isSameDay(user.getGmtLastShare(), now));	
//		return SUCCESS;
//	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setInviteeOpenIds(String inviteeOpenIds) {
		this.inviteeOpenIds = inviteeOpenIds;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}
}
