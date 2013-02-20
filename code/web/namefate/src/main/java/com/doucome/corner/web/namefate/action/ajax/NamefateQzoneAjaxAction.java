package com.doucome.corner.web.namefate.action.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.apps.enums.AppLoginSourceEnums;
import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.namefate.action.NamefateBasicAction;

/**
 * 用户相关异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class NamefateQzoneAjaxAction extends NamefateBasicAction  {
	
	private JsonModel<Map<String, Object>> json = new JsonModel<Map<String,Object>>();
	
	@Autowired
    private QqQueryService namefateQqQueryService ;

	/**
	 * 是否是认证空间粉丝.
	 * @return
	 */
	public String isQzoneFans() {
		String openId = getOpenId() ;
		String openKey = getOpenKey() ;
		String pf = getPf() ;
		if(StringUtils.isBlank(pf) || StringUtils.isBlank(openId)) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.user");
			return SUCCESS;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		AppLoginSourceEnums loginSource = AppLoginSourceEnums.get(pf) ;
		if(loginSource == AppLoginSourceEnums.Pengyou || loginSource == AppLoginSourceEnums.Qzone){
			boolean isFans = namefateQqQueryService.isQzoneFans(pf, openId, openKey);
			result.put("isFans", isFans);
		} else {
			result.put("isFans", false);
		}
		
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}

	public JsonModel<Map<String, Object>> getJson() {
		return json;
	}
}
