package com.doucome.corner.biz.dal.condition.dcome;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ze2200
 *
 */
public class DcSearchLogCondition {
	private Long id;
	private String status;
	private Long userId;
	private BigDecimal priceStart;
	private BigDecimal priceEnd;
	private Date gmtStart;
	private Date gmtEnd;
	private String order;

	public Map<String, Object> toMap() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		condition.put("status", status);
		condition.put("userId", userId);
		condition.put("priceStart", priceStart);
		condition.put("priceEnd", priceEnd);
		condition.put("gmtStart", gmtStart);
		condition.put("gmtEnd", gmtEnd);
		condition.put("order", order);
		return condition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public BigDecimal getPriceStart() {
		return priceStart;
	}

	public void setPriceStart(BigDecimal priceStart) {
		this.priceStart = priceStart;
	}

	public BigDecimal getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(BigDecimal priceEnd) {
		this.priceEnd = priceEnd;
	}

	public Date getGmtStart() {
		return gmtStart;
	}

	public void setGmtStart(Date gmtStart) {
		this.gmtStart = gmtStart;
	}

	public Date getGmtEnd() {
		return gmtEnd;
	}

	public void setGmtEnd(Date gmtEnd) {
		this.gmtEnd = gmtEnd;
	}
}
