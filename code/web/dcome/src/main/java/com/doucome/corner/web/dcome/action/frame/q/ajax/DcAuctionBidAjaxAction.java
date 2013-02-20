package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.business.DcAuctionBO;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 积分竞价异步接口类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcAuctionBidAjaxAction extends DComeBasicAction {
	
	private JsonModel<List<DcMessageDTO>> json = new JsonModel<List<DcMessageDTO>>();
	
	@Autowired
	private DcAuctionBO dcAuctionBO;
	
	private Long auctionId;
	
	private Integer bidIntegral;
	
	private static final Log logger = LogFactory.getLog(DcAuctionBidAjaxAction.class);
	
	/**
	 * 竞拍商品.
	 * @return
	 */
	public String bidItem() {
		DcUserDTO user = getUser();
		if (user == null) {
			json.setFail(JsonModel.CODE_SUCCESS, "not.login");
			return SUCCESS;
		}
		List<DcMessageDTO> bidDetails = new ArrayList<DcMessageDTO>();
		DcMessageDTO bidDetail = dcAuctionBO.bidItem(auctionId, user, bidIntegral);
		bidDetails.add(bidDetail);
		//如果出价不成功,获取最新的出价信息
		if (bidDetail == null || !"success".equals(bidDetail.getOtherInfo())) {
			DcMessageDTO latestBidDetail = dcAuctionBO.getLatestBidDetail(auctionId);
			if (latestBidDetail.getIntegral() > bidIntegral) {
				bidDetails.add(latestBidDetail);
			}
		}
		json.setSuccess(JsonModel.CODE_SUCCESS, bidDetails);
		json.setDetail(bidDetail == null? null: bidDetail.getOtherInfo());
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String queryBidHistory() {
		if (IDUtils.isNotCorrect(auctionId) || getUser() == null) {
			json.setFail(JsonModel.CODE_FAIL, "param.error");
			return SUCCESS;
		}
		try {
			List<DcMessageDTO> auctionHis = dcAuctionBO.getAuctionHistories(auctionId, bidIntegral);
			json.setSuccess(JsonModel.CODE_SUCCESS, auctionHis);
		} catch (Exception e) {
			logger.error(e);
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public void setBidIntegral(Integer bidIntegral) {
		this.bidIntegral = bidIntegral;
	}

	public JsonModel<List<DcMessageDTO>> getJson() {
		return json;
	}
}
