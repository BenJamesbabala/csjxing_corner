package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.dcome.business.DcUserBO;
import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.facade.PfModel;
import com.doucome.corner.biz.dcome.model.facade.QQPfModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 用户相关异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcQzoneAjaxAction extends QBasicAction {
	
	private JsonModel<Map<String, Object>> json = new JsonModel<Map<String,Object>>();
	
	@Autowired
	private QqQueryService qqQueryService;
	
	@Autowired
	private DcUserBO dcUserBO;
	/**
	 * 是否是认证空间粉丝.
	 * @return
	 */
	public String isQzoneFans() {
		PfModel pfm = dcAuthz.getPfModel() ;
		if (pfm == null) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.user");
			return SUCCESS;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if(pfm != null && (pfm.getPf() == DcLoginSourceEnums.Pengyou || pfm.getPf() == DcLoginSourceEnums.Qzone)){
			QQPfModel qpfm = (QQPfModel) pfm ;
			//过滤敏感词
			boolean isFans = qqQueryService.isQzoneFans(qpfm.getPf().getValue(), qpfm.getOpenId(), qpfm.getOpenKey());
			result.put("isFans", isFans);
		} else {
			result.put("isFans", false);
		}
		
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}
	
	/**
	 * 关注空间.
	 * @return
	 */
	public String followQzone() {
		if (!qzoneFans()) {
			json.setFail(JsonModel.CODE_FAIL, "unfollow");
			return SUCCESS;
		}
		DcUserDTO user = getUser();
		Map<String, Object> result = new HashMap<String, Object>();
		//重复关注
		if (user.getGmtFollowQzone() != null) {
			result.put("integral", 0);
			json.setSuccess(JsonModel.CODE_SUCCESS, result);
			return SUCCESS;
		}
		int integral = dcUserBO.followQzone(user);
		if (integral > 0) {
			result.put("integral", integral);
			json.setSuccess(JsonModel.CODE_SUCCESS, result);
		} else {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	private boolean qzoneFans() {
		QQPfModel pfm = (QQPfModel) dcAuthz.getPfModel();
		return qqQueryService.isQzoneFans(pfm.getPf().getValue(), pfm.getOpenId(), pfm.getOpenKey());
	}

	public JsonModel<Map<String, Object>> getJson() {
		return json;
	}
}
