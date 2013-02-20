package com.doucome.corner.biz.dal.condition.dcome.hs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ze2200
 *
 */
public class HsTopicCondition {
	
	private Long id;
	
	private Date gmtDay;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("gmtDay", gmtDay);
		condition.put("id", id);
		return condition ;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGmtDay() {
		return gmtDay;
	}

	public void setGmtDay(Date gmtDay) {
		this.gmtDay = gmtDay;
	}
}
