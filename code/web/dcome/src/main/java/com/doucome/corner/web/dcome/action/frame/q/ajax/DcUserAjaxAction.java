package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dcome.business.DcUserBO;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcUserGuideEnum;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 积分竞价异步接口类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcUserAjaxAction extends DComeBasicAction {
	
	private JsonModel<Map<String, Object>> json = new JsonModel<Map<String, Object>>();
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService;
	@Autowired
	private DcUserBO dcUserBO;
	@Autowired
	private DcUserIntegralDetailService dcUserIntegralDetailService;
	@Autowired
	private DcUserService dcUserService;
	
	private Long messageId;
	
	/**
	 * 统计用户消息.
	 * @return
	 */
	public String countUserMessage() {
	    DcUserDTO user = getUser();
		if (user == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		int totalCount = 0;
		//还未送出奖品的用户消息.
		List<DcPromotionAwardDTO> awards = dcPromotionAwardService.getAwardByUserId(user.getUserId());
		if (CollectionUtils.isEmpty(awards)) {
			result.put("award", 0);
		} else {
			int count = 0;
			for (DcPromotionAwardDTO temp: awards) {
				if (!temp.isSend()) {
					count++;
				}
			}
			result.put("award", count);
			if (count > 0) {
			     totalCount++;
			}
		}
//		Map<String, Integer> taskDetail = dcUserBO.getUserUndrawIntegral(user);
//		result.putAll(taskDetail);
//		if (taskDetail.get("integral") > 0) {
//			totalCount++;
//		}
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(user.getUserId());
		condition.addSource(DcIntegralSourceEnums.APP_NOTIFICATION.getValue());
		condition.addSource(DcIntegralSourceEnums.UGC_ITEM.getValue());
		condition.addSource(DcIntegralSourceEnums.ACCEPT_UGC.getValue());
		condition.addSource(DcIntegralSourceEnums.BUY_UGC.getValue());
		condition.setReadStatus(DcYesOrNoEnum.NO.getValue());
		List<DcUserIntegralDetailDTO> integralDetails = dcUserIntegralDetailService.getIntegralDetails(condition, new Pagination(1, 3));
		if (CollectionUtils.isNotEmpty(integralDetails)) {
			result.put("message", integralDetails);
			totalCount += integralDetails.size();
		}
		result.put("totalCount", totalCount);
		
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}
	
	/**
	 * 获取用户今日积分领取情况.
	 * @return
	 */
	public String queryIntegralCollectStatus() {
		DcUserDTO user = getUser();
		if (user == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		try {
			Map<String, Integer> integralStatus = dcUserBO.getCurIntegralCollectStatus(user);
			Map<String, Object> tempMap = new HashMap<String, Object>();
			for (Map.Entry<String, Integer> temp: integralStatus.entrySet()) {
				tempMap.put(temp.getKey(), temp.getValue());
			}
			json.setSuccess(JsonModel.CODE_SUCCESS, tempMap);
		} catch (Exception e) {
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String updateMessageReadStatus() {
		Long userId = getUserId();
		if (messageId == null || userId == null) {
			json.setFail(JsonModel.CODE_FAIL, "param.error");
			return SUCCESS;
		}
		int count = dcUserIntegralDetailService.updateReadStatus(messageId, DcYesOrNoEnum.YES);
		if (count > 0) {
			json.setSuccess(JsonModel.CODE_SUCCESS, null);
		} else {
			json.setFail(JsonModel.CODE_FAIL, "no.update");
		}
		return SUCCESS;
	}
	
	/**
	 * 判断某个用户引导是否完成.
	 * @return
	 */
	public String isShowUgcLayer() {
		Map<String, Object> result = new HashMap<String, Object>();
		DcUserDTO user = getUser();
		if (DateUtils.getDiffInDays(new Date(), user.getGmtCreate()) > 3) {
			boolean isDone = DcUserGuideEnum.UGC_LAYER.isDone(user.getNewGuide());
			result.put("isShow", !isDone);
			if (!isDone) {
				dcUserService.updateNewGuide(user.getUserId(), DcUserGuideEnum.UGC_LAYER.getDoneValue(user.getNewGuide()));
			}
		} else {
			result.put("isShow", false);
		}
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}
	
	public JsonModel<Map<String, Object>> getJson() {
		return json;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
}
