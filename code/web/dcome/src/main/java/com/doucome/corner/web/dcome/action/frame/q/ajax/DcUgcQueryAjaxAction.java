package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcSearchLogDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcUgcItemModel;
import com.doucome.corner.biz.dcome.service.DcSearchLogService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * pgc商品参与活动异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcUgcQueryAjaxAction extends QBasicAction {
	
	private JsonModel<DcItemDTO> json = new JsonModel<DcItemDTO>();
	
	private DcUgcItemModel ugcInfo = new DcUgcItemModel();
	
	@Autowired
	private DcItemBO dcItemBO;
	
	@Autowired
	private DcSearchLogService dcSearchLogService;
	
	@Override
	public String execute() {
		DcUserDTO user = getUser();
		if (user == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		ugcInfo.setCreatorId(user.getUserId());
		ugcInfo.setCreatorNick(user.getNick());
		ResultModel<Boolean> checkResult = ugcInfo.validate();
		if (!checkResult.isSuccess()) {
			json.setFail(JsonModel.CODE_FAIL, checkResult.getDetail());
			return SUCCESS;
		}
		try {
			DcItemDTO item = dcItemBO.getUgcDcItem(ugcInfo);
			logUgcSearch(user, item);
			if (item != null) {
				json.setSuccess(JsonModel.CODE_SUCCESS, item);
				int ownerSign = DcItemUtils.getItemOwerSign(item.getCreatorUserId(), user.getUserId());
				item.setOwnerSign(ownerSign);
			} else {
				json.setFail(JsonModel.CODE_FAIL, "item.not.exist");
			}
		} catch (Exception e) {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	private void logUgcSearch(DcUserDTO user, DcItemDTO item) {
		DcSearchLogDTO searchLog = new DcSearchLogDTO();
		searchLog.setUserId(user.getUserId());
		searchLog.setNativeId(item.getNativeId());
		searchLog.setTitle(item.getItemTitle());
		searchLog.setPicture(item.getPicUrl80x80());
		searchLog.setPrice(item.getItemPromPrice() != null? item.getItemPromPrice(): item.getItemPrice());
		searchLog.setCommissionRate(item.getCommissionRate());
		try {
		    dcSearchLogService.insertSearchLog(searchLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setItemUrl(String itemUrl) {
		ugcInfo.setItemUrl(itemUrl);
	}
	
	public JsonModel<DcItemDTO> getJson() {
		return json;
	}
}
