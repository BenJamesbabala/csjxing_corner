package com.doucome.corner.web.dcome.action.frame.q;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.param.DcPromItemModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcTakeinPromAction extends QBasicAction {
	
	private Long itemId;
	
	ResultModel<Long> result = new ResultModel<Long>();
	
	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	@Override
	public String execute() {
		try {
			Long userId = getUserId();
			ResultModel<Long> checkResult = dcPromotionBO.dcItemTakeinPromCheck(itemId, userId);
			if (!checkResult.isSuccess()) {
				result.setFail(checkResult.getCode(), checkResult.getDetail());
				return INPUT;
			} else {
				//用户已用此商品参与过活动
				if (checkResult.getData() != null) {
					result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_TAKEIN_REPEAT);
					result.setData(checkResult.getData());
					return INPUT;
				}
			}
			DcPromItemModel pkItem = new DcPromItemModel();
			pkItem.setItemId(itemId);
			pkItem.setUserId(getUserId());
			pkItem.setUserNick(getPfNick());
			result = dcPromotionBO.takeinPromotion(pkItem);
			return SUCCESS;
		} catch (Exception e) {
			result.setFail(JsonModel.CODE_FAIL, ResultModel.DETAIL_INTERNAL_ERROR);
			return INPUT;
		}
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public ResultModel<Long> getResult() {
		return result;
	}
}
