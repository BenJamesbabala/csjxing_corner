package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcUserBO;
import com.doucome.corner.biz.dcome.enums.DcUserGuideEnum;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcUserGuideModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 积分竞价异步接口类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcUserGuideAjaxAction extends DComeBasicAction {
	
	private JsonModel<Map<String, Object>> json = new JsonModel<Map<String, Object>>();
	@Autowired
	private DcUserBO dcUserBO;
	
	private String guideStr;
	
	/**
	 * 
	 * @return
	 */
	public String queryFLayerShow() {
		DcUserDTO user = getUser();
		if (user == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		Map<String, Boolean> layerShows = dcUserBO.queryShowFLayer(user);
		Map<String, Object> result = new HashMap<String, Object>();
		for (Map.Entry<String, Boolean> temp: layerShows.entrySet()) {
			result.put(temp.getKey(), temp.getValue());
		}
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String markGuideDone() {
		DcUserDTO user = getUser();
		DcUserGuideEnum guideEnum = DcUserGuideEnum.toEnum(guideStr);
		if (guideEnum == DcUserGuideEnum.UNKNOW) {
			json.setFail(JsonModel.CODE_FAIL, "");
			return SUCCESS;
		}
		DcUserGuideModel model = new DcUserGuideModel();
		model.setGuideEnum(guideEnum);
		dcUserBO.markGuideDone(user, model);
		return SUCCESS;
	}
	
	public JsonModel<Map<String, Object>> getJson() {
		return json;
	}

	public void setGuideStr(String guideStr) {
		this.guideStr = guideStr;
	}
}
