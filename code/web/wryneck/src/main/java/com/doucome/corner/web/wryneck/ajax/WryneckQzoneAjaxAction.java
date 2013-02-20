package com.doucome.corner.web.wryneck.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.wryneck.action.WryneckBasicAction;

/**
 * 用户相关异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class WryneckQzoneAjaxAction extends WryneckBasicAction  {
	
	private JsonModel<Map<String, Object>> json = new JsonModel<Map<String,Object>>();
	
	@Autowired
    private QqQueryService wryneckQqQueryService ;

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
		DcLoginSourceEnums loginSource = DcLoginSourceEnums.get(pf) ;
		if(loginSource == DcLoginSourceEnums.Pengyou || loginSource == DcLoginSourceEnums.Qzone){
			boolean isFans = wryneckQqQueryService.isQzoneFans(pf, openId, openKey);
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
