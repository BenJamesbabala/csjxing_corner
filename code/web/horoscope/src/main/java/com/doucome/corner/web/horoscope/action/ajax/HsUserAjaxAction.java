package com.doucome.corner.web.horoscope.action.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.dcome.service.horoscope.HsUserService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.horoscope.action.HsBasicAction;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsUserAjaxAction extends HsBasicAction {
	
	private JsonModel<Object> json = new JsonModel<Object>();
	
	private QqQueryService hsQqQueryService;
	
	@Autowired
	private HsUserService hsUserService;
	/**
	 * ÅÐ¶ÏÊÇ·ñÊÇ¿Õ¼ä·ÛË¿
	 * @return
	 */
	public String qzoneFans() {
		if (IDUtils.isNotCorrect(getUserId())) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		Boolean result = isQzoneFans();
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String followQzone() {
		Long userId = getUserId();
		if (IDUtils.isNotCorrect(userId)) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		if (!isQzoneFans()) {
			json.setFail(JsonModel.CODE_FAIL, "no.follow");
			return SUCCESS;
		}
		int count = hsUserService.updateFollowQzone(getUserId());
		if (count > 0) {
			json.setSuccess(JsonModel.CODE_SUCCESS, null);
		} else {
			json.setFail(JsonModel.CODE_FAIL, "update.error");
		}
		return SUCCESS;
	}
	
	private boolean isQzoneFans() {
		String openId = getOpenId();
		String openKey = getOpenKey();
		String pf = getPf();
		return hsQqQueryService.isQzoneFans(pf, openId, openKey);
	}
	
	public void setHsQqQueryService(QqQueryService hsQqQueryService) {
		this.hsQqQueryService = hsQqQueryService;
	}

	public JsonModel<Object> getJson() {
		return this.json;
	}
}
