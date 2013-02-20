package com.doucome.corner.biz.dal.condition.dcome;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DcAuctionItemSearchCondition {

	private Long id;
	private Long userId;
	private Date gmtStart;
	private String auctionStatus;
	

	public Map<String, Object> toMap() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		condition.put("userId", userId);
		condition.put("gmtStart", gmtStart);
		condition.put("auctionStatus", auctionStatus);
		return condition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getGmtStart() {
		return gmtStart;
	}

	public void setGmtStart(Date gmtStart) {
		this.gmtStart = gmtStart;
	}

	public String getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(String auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

}
