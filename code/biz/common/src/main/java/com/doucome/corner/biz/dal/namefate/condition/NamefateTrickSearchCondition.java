package com.doucome.corner.biz.dal.namefate.condition;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.dal.model.AbstractModel;

@SuppressWarnings("serial")
public class NamefateTrickSearchCondition extends AbstractModel{

	private Long userId ;
	
	private Long trickUserId ;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("userId", userId) ;
		condition.put("trickUserId", trickUserId) ;
		return condition ;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTrickUserId() {
		return trickUserId;
	}

	public void setTrickUserId(Long trickUserId) {
		this.trickUserId = trickUserId;
	}
	
	
}
