package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcAuctionBO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * ����ʱ�첽�ӿ���.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcCountdownTimeAjaxAction extends DComeBasicAction {
	
	private JsonModel<Map<String, Long>> json = new JsonModel<Map<String, Long>>();
	
	@Autowired
	private DcAuctionBO dcAuctionBO;
	
	@Autowired
	private DcPromotionService dcPromotionService;
	
	/**
	 * ��ȡ�����˵�����ʱtipʱ��.
	 * pk�������.
	 * @return
	 */
	public String queryMenuCDTime() {
		Map<String, Long> result = new HashMap<String, Long>();
		result.put("pk", queryPkCountdownTime());
		result.put("auction", dcAuctionBO.getAuctionCountdownTime());
		json.setSuccess(JsonModel.CODE_SUCCESS, result);
		return SUCCESS;
	}
	
	/**
	 * ��ѯ�����ʱ.
	 * @return
	 */
	private Long queryPkCountdownTime() {
		DcPromotionDTO promotion = dcPromotionService.getCurPromotion();
		if (promotion == null) {
			return null;
		}
		return promotion.getEndTime().getTime() - new Date().getTime();
	}

	public JsonModel<Map<String, Long>> getJson() {
		return json;
	}
}
