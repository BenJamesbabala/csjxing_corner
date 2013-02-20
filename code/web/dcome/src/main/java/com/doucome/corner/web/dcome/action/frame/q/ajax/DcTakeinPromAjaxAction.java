package com.doucome.corner.web.dcome.action.frame.q.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionBO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.param.DcPromItemModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.frame.q.QBasicAction;

/**
 * pgc��Ʒ�����첽�ӿ�.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcTakeinPromAjaxAction extends QBasicAction {
	
	private Long itemId;
	
	private JsonModel<Long> json = new JsonModel<Long>();
	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	public String isUserTakeinProm() {
		DcPromotionItemDTO promItem = getMyPromotionItem();
		if (promItem == null) {
			json.setFail(JsonModel.CODE_FAIL, null);
		} else {
			json.setSuccess(JsonModel.CODE_SUCCESS, promItem.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * pgc��Ʒ����.
	 * @return json����˵����
	 * code: success, data: dc_promotion_item��id, detail: null/takein.item.repeat
	 * code: fail, detail:
	 *                 promotion.ended ��ѽ���
	 *                 promotion.not.start �δ��ʼ
	 *                 takein.repeat(�Ѳ���), data: dc_promotion_item.id
	 *                 item.not.exist ��Ʒ������.
	 *                 itemPrice.more(��Ʒ�۸����)
	 *                 internal.error(�������ڲ�����)
	 */
	public String dcItemTakeinProm() {
		try {
			Long userId = getUserId();
			ResultModel<Long> checkResult = dcPromotionBO.dcItemTakeinPromCheck(itemId, userId);
			if (!checkResult.isSuccess()) {
				json.setFail(checkResult.getCode(), checkResult.getDetail());
				json.setData(checkResult.getData());
				return SUCCESS;
			}
			DcPromItemModel pkItem = new DcPromItemModel();
			pkItem.setItemId(itemId);
			pkItem.setUserId(getUserId());
			pkItem.setUserNick(getPfNick());
			json = dcPromotionBO.takeinPromotion(pkItem);
		} catch (Exception e) {
			json.setFail(JsonModel.CODE_FAIL, ResultModel.DETAIL_INTERNAL_ERROR);
		}
		return SUCCESS;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public JsonModel<Long> getJson() {
		return json;
	}
}
