package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcAppVoteDO extends AbstractModel {

	private Long id ;
	
	private Long userId ;
	
	private String hasVote ;
	
	private String awardStatus;
	
	private Date gmtCreate ;

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

	public String getHasVote() {
		return hasVote;
	}

	public void setHasVote(String hasVote) {
		this.hasVote = hasVote;
	}
	
	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
