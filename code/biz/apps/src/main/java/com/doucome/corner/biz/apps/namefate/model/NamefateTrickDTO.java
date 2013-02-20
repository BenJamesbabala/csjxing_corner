package com.doucome.corner.biz.apps.namefate.model;

import java.util.Date;

import com.doucome.corner.biz.dal.namefate.dataobject.NamefateTrickDO;

public class NamefateTrickDTO {

	private NamefateTrickDO trick ;
	
	public NamefateTrickDTO(NamefateTrickDO trick) {
		if(trick == null){
			trick = new NamefateTrickDO() ;
		}
		this.trick = trick ;
	}
	
	public Date getGmtCreate() {
		return trick.getGmtCreate() ;
	}
	
	public Long getId() {
		return trick.getId();
	}
	
	public Long getUserId() {
		return trick.getUserId();
	}
	
	public String getUserNick() {
		return trick.getUserNick();
	}
	
	public Long getTrickUserId() {
		return trick.getTrickUserId();
	}
	
	public String getTrickUserNick() {
		return trick.getTrickUserNick();
	}
	
	public String getTrickInputName() {
		return trick.getTrickInputName();
	}
	
	public String getTrickInputTaName() {
		return trick.getTrickInputTaName();
	}
	
}
