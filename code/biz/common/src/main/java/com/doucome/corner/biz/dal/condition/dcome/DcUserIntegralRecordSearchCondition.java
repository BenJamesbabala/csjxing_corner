package com.doucome.corner.biz.dal.condition.dcome;

import java.util.HashMap;
import java.util.Map;

/**
 * 积分记录查询
 * @author langben 2013-1-8
 *
 */
public class DcUserIntegralRecordSearchCondition {

	private Long userId ;
	
	private String inOutType ;
	
	private String status ;
	
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("userId", userId) ;
		map.put("inOutType", inOutType) ;
		map.put("status", status) ;
		return map ;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
