package com.doucome.corner.biz.dal.condition.dcome;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ze2200
 *
 */
public class DcExchangeItemCondition {
	private Long id;
	private String status;
	/**
	 * 与exUserType互斥，优先级高于exUserType
	 */
	private Long userId;
	private String exUserType;
	private String order;
	private String exType ;

	public Map<String, Object> toMap() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		condition.put("status", status);
		condition.put("userId", userId);
		if (userId == null) {
			condition.put("exUserType", exUserType);
		}
		condition.put("order", order);
		condition.put("exType", exType) ;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getExUserType() {
		if (userId != null) {
			return "";
		}
		return exUserType;
	}

	public void setExUserType(String exUserType) {
		this.exUserType = exUserType;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getExType() {
		return exType;
	}

	public void setExType(String exType) {
		this.exType = exType;
	}
	
}
