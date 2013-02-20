package com.doucome.corner.biz.dal.condition;

import java.util.HashMap;
import java.util.Map;

public class BannerConfigSearchCondition {

	private String bannerId ;
	
	public Map<String,Object> toMap(){
    	Map<String,Object> condition = new HashMap<String,Object>() ;
		
		condition.put("bannerId", bannerId) ;
		
		return condition ;
    }

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	
}
