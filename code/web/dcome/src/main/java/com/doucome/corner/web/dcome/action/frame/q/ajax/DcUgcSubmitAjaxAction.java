package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.business.DcUserBO;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcUserGuideEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcUgcItemModel;
import com.doucome.corner.biz.dcome.model.param.DcUserGuideModel;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcUgcSubmitAjaxAction extends QBasicAction {
	
	private JsonModel<String> json = new JsonModel<String>();
	
	private DcUgcItemModel ugcInfo = new DcUgcItemModel();
	
	@Autowired
	private DcItemBO dcItemBO;
	
	@Autowired
	private DcUserBO dcUserBO;
	
	@Autowired
	private DcUserIntegralDetailService dcUserIntegralDetailService;
	
	/**
	 * 
	 */
	public String execute() {
		DcUserDTO user = getUser();
		ugcInfo.setCreatorId(user.getUserId());
		ugcInfo.setCreatorNick(user.getNick());
		JsonModel<DcItemDTO> newResult = dcItemBO.createUgcItem(ugcInfo);
		if (!newResult.isSuccess()) {
			json.setFail(newResult.getCode(), newResult.getDetail());
			return SUCCESS;
		}
		//因为ugc浮层第一次主动弹出后，相应的guide字段已置1，此处判断ugc是否完成，需要通过integral_detail表
		if (newResult.isSuccess() && !isUgcAwarded(user.getUserId())) {
			DcUserGuideModel guideModel = new DcUserGuideModel();
			guideModel.setUserId(user.getUserId());
			guideModel.setUserNick(user.getNick());
			guideModel.setGuideResultId(newResult.getData().getId());
			guideModel.setGuideResultName(newResult.getData().getItemTitle());
			guideModel.setGuideEnum(DcUserGuideEnum.UGC_LAYER);
			dcUserBO.markGuideDone(user, guideModel);
			json.setDetail("ugc.done");
		}
		DcItemDTO dcItem = newResult.getData();
		String taokeUrl = dcItemBO.getDcTaokeUrl(dcItem, ugcInfo.getCreatorId());
		json.setSuccess(JsonModel.CODE_SUCCESS, taokeUrl);
		return SUCCESS;
	}
	
	public boolean isUgcAwarded(Long userId) {
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(userId);
		condition.setSource(DcIntegralSourceEnums.UGC_ITEM.getValue());
		int count = dcUserIntegralDetailService.countIntegralDetails(condition);
		return count > 0;
	}
	
	public JsonModel<String> getJson() {
		return json;
	}
	
	public void setItemUrl(String itemUrl) {
		ugcInfo.setItemUrl(itemUrl);
	}
	
	public void setRecommandReason(String recommandReason) {
		ugcInfo.setRecommandReason(recommandReason);
	}
	
	public void setShareTo(Boolean isShare) {
		ugcInfo.setIsShare(isShare);
	}
}
