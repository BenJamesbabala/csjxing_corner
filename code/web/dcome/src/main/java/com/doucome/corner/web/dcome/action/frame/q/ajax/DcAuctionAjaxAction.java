package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcAuctionBO;
import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 积分竞价异步接口类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcAuctionAjaxAction extends DComeBasicAction {
	
	private JsonModel<List<DcAuctionItemDTO>> json = new JsonModel<List<DcAuctionItemDTO>>();
	
	@Autowired
	private DcAuctionBO dcAuctionBO;
	
	/**
	 * 
	 * @return
	 */
	public String queryAuctions() {
		List<DcAuctionItemDTO> auctionItems = dcAuctionBO.queryAuctions();
		json.setSuccess(JsonModel.CODE_SUCCESS, auctionItems);
		return SUCCESS;
	}

	public JsonModel<List<DcAuctionItemDTO>> getJson() {
		return json;
	}
}
