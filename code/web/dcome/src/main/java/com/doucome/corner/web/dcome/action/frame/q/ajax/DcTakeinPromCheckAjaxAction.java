package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.param.DcUgcItemModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * ugc��Ʒ����У����.
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
	 * ����������Ʒ�Ƿ���Ϲ���.
	 * @return json����˵��.
	 *   code: success, data: null/dc_item.id(�����û���������Ʒ)
	 *   code: failed, detail:
	 *            itemurl.invalid ��Ч��Ʒ����. 
	 *            promotion.ended ��ѽ���
	 *            promotion.not.start �δ��ʼ
	 *            takein.repeat(�Ѳ���), data: dc_promotion_item.id
	 *            item.not.exist ��Ʒ������.
	 *            itemPrice.more(��Ʒ�۸����)
	 *            internal.error(�������ڲ�����)
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
