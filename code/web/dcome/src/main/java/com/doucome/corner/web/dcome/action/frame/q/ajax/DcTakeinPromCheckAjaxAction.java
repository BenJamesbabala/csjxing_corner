package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.param.DcUgcItemModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * ugc商品参与活动校验类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcTakeinPromCheckAjaxAction extends DComeBasicAction  {

	private DcUgcItemModel ugcInfo = new DcUgcItemModel(); 
	
	private JsonModel<Long> json = new JsonModel<Long>();
	
	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	/**
	 * 检查参与活动的商品是否符合规则.
	 * @return json数据说明.
	 *   code: success, data: null/dc_item.id(已有用户创建过商品)
	 *   code: failed, detail:
	 *            itemurl.invalid 无效商品链接. 
	 *            promotion.ended 活动已结束
	 *            promotion.not.start 活动未开始
	 *            takein.repeat(已参与), data: dc_promotion_item.id
	 *            item.not.exist 商品不存在.
	 *            itemPrice.more(商品价格过高)
	 *            internal.error(服务器内部错误)
	 */
	public String ugcTakeinPromCheck() {
		ResultModel<Boolean> temp = ugcInfo.validate();
		if (!temp.isSuccess()) {
			json.setFail(temp.getCode(), temp.getDetail());
			return SUCCESS;
		}
		ugcInfo.setCreatorId(getUserId());
		ugcInfo.setCreatorNick(getPfNick());
		ResultModel<Long> checkResult = dcPromotionBO.ugcTakeinPromCheck(ugcInfo);
		
		if (checkResult.isSuccess()) {
			json.setSuccess(ResultModel.CODE_SUCCESS, checkResult.getData());
		} else {
			json.setFail(checkResult.getCode(), checkResult.getDetail());
			json.setData(checkResult.getData());
		}
		return SUCCESS;
	}

	public JsonModel<Long> getJson() {
		return json;
	}
	
	public void setItemUrl(String itemUrl) {
		ugcInfo.setItemUrl(itemUrl);
	}
}
