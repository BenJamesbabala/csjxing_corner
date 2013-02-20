package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * pgc商品参与活动异步接口.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcUgcAjaxAction extends QBasicAction {
	
	private JsonModel<Object> json = new JsonModel<Object>();
	
	@Autowired
	DcUserIntegralDetailService dcUserIntegralDetailService;
	
	@Autowired
	private DcItemService dcItemService;
	
	/**
	 * 获取ugc相关的积分奖励.UGC_ITEM,ACCEPT_UGC,BUY_UGC
	 * @return
	 */
	public String queryUgcAwards() {
		if (getUserId() == null) {
			json.setFail(JsonModel.CODE_FAIL, "no.login");
			return SUCCESS;
		}
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.addSource(DcIntegralSourceEnums.UGC_ITEM.getValue());
		condition.addSource(DcIntegralSourceEnums.ACCEPT_UGC.getValue());
		condition.addSource(DcIntegralSourceEnums.BUY_UGC.getValue());
		List<DcUserIntegralDetailDTO> integralDetails =
			dcUserIntegralDetailService.getIntegralDetails(condition, new Pagination(1, 20));
		json.setSuccess(JsonModel.CODE_SUCCESS, integralDetails);
		return SUCCESS;
	}
	
	public String queryUgcExample() {
		DcItemSearchCondition condition = new DcItemSearchCondition();
		condition.setGenWay(DcItemGenWayEnums.PROFESSIONAL.getValue());
		condition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
		List<DcItemDTO> items = dcItemService.getItemsNoPagination(condition, new Pagination(1, 1));
		if (CollectionUtils.isNotEmpty(items)) {
			json.setSuccess(JsonModel.CODE_SUCCESS, items.get(0));
		} else {
			json.setFail(JsonModel.CODE_FAIL, "no.item");
		}
		return SUCCESS;
	}
	
	public JsonModel<Object> getJson() {
		return json;
	}
}
