package com.doucome.corner.biz.dal.condition.dcome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DcUserExchangeApproveSearchCondition {

	private Long userId ;
	
	private List<String> exItemTypeList ; 
	
	private String status ;
	
	private String checkCode ;
	
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("userId", userId) ;
		map.put("exItemTypeList", exItemTypeList) ;
		map.put("status", status) ;
		map.put("checkCode", checkCode) ;
		return map ;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getExItemTypeList() {
		return exItemTypeList;
	}

	public void setExItemTypeList(List<String> exItemTypeList) {
		this.exItemTypeList = exItemTypeList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
