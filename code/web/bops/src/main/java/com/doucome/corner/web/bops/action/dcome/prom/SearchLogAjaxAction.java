package com.doucome.corner.web.bops.action.dcome.prom;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.condition.dcome.DcSearchLogCondition;
import com.doucome.corner.biz.dcome.business.DcExchangeBO;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.enums.DcAutoExchangeStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.param.DcNewExItemModel;
import com.doucome.corner.biz.dcome.service.DcSearchLogService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 活动detail.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class SearchLogAjaxAction extends BopsBasicAction {
	
	private JsonModel<Object> json = new JsonModel<Object>();
	
	private DcNewExItemModel newExModel = new DcNewExItemModel();
	
	private String[] nativeIds;
	
	private Long userId;
	
	@Autowired
	private DcItemBO dcItemBO;
	@Autowired
	private DcExchangeBO dcExchangeBO;
	@Autowired
	private DcSearchLogService dcSearchLogService;
	
	public String queryItemFromTb() {
		List<DcItemDTO> items = dcItemBO.getDcItemsFromTB(nativeIds);
		json.setSuccess(JsonModel.CODE_SUCCESS, items);
		return SUCCESS;
	}
	
	/**
	 * 添加兑换.
	 * @return
	 */
	public String addUgcToExchange() {
		if (nativeIds == null || nativeIds.length == 0) {
			json.setFail(JsonModel.CODE_FAIL, "no.item");
			return SUCCESS;
		}
		ResultModel<Long> result = dcExchangeBO.addUgcToExchange(nativeIds[0], userId, newExModel);
		if (!result.isSuccess()) {
			json.setFail(ResultModel.CODE_FAIL, result.getDetail());
			return SUCCESS;
		}
		//将用户今日提交的兑换数据值为已审核.
		DcSearchLogCondition condition = new DcSearchLogCondition();
		condition.setUserId(newExModel.getUserId());
//		Date[] dates = DateUtils.getDayStartEnd(time);
//		condition.setGmtStart(dates[0]);
//		condition.setGmtEnd(dates[1]);
		dcSearchLogService.updateSearchLogStatus(condition, DcAutoExchangeStatusEnum.AUDITED);
		json.setSuccess(JsonModel.CODE_SUCCESS, result.getData());
		return SUCCESS;
	}
	
	public JsonModel<Object> getJson() {
		return json;
	}

	public void setNativeIds(String nativeIds) {
		if (StringUtils.isEmpty(nativeIds)) {
			return ;
		}
		this.nativeIds = nativeIds.split(",");
	}

	public void setUserId(Long userId) {
		this.userId = userId;
		newExModel.setUserId(userId);
	}

	public void setPrice(BigDecimal price) {
		newExModel.setPrice(price);
	}

	public void setPostalFee(BigDecimal postalFee) {
		newExModel.setPostalFee(postalFee);
	}
	
	public void setItemType(String itemType) {
		newExModel.setItemType(itemType);
	}
}