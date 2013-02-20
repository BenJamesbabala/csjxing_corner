package com.doucome.corner.biz.dal.condition.dcome;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动投票查询
 * @author langben 2012-8-14
 *
 */
public class DcPromotionRateDetailCondition {

	
	private Long rateUserId ;
	
	private Long promotionId ;
	
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("rateUserId", rateUserId) ;
		map.put("promotionId", promotionId) ;
		return map ;
	}


	public Long getRateUserId() {
		return rateUserId;
	}

	public void setRateUserId(Long rateUserId) {
		this.rateUserId = rateUserId;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	
}
