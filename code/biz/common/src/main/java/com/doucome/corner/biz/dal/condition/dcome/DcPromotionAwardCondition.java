package com.doucome.corner.biz.dal.condition.dcome;

import java.util.HashMap;
import java.util.Map;

public class DcPromotionAwardCondition {

	private Long promotionId;
	
	private String promotionType;
	
	private Long userId;
	
	private String reviewStatus;
	
	private String sendStatus;
	
	private String checkCode;
	
	private String order;
		
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		
		condition.put("promotionId", promotionId) ;
		condition.put("promotionType", promotionType) ;
		condition.put("userId", userId) ;
		condition.put("reviewStatus", reviewStatus) ;
		condition.put("sendStatus", sendStatus) ;
		condition.put("checkCode", checkCode);
		condition.put("order", order);
		
		return condition ;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
