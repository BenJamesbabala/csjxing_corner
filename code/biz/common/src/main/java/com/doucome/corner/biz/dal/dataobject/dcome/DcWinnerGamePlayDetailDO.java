package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

public class DcWinnerGamePlayDetailDO {

	private Long id ;
	
	private Long userId ;
	
	private String userNick ;
	
	private Integer winScore ;
	
	/**
	 * 消耗的积分
	 */
	private Integer betIntegral ;
	
	private String betParam ;
	
	private String result ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

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

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Integer getWinScore() {
		return winScore;
	}

	public void setWinScore(Integer winScore) {
		this.winScore = winScore;
	}

	public String getBetParam() {
		return betParam;
	}

	public void setBetParam(String betParam) {
		this.betParam = betParam;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getBetIntegral() {
		return betIntegral;
	}

	public void setBetIntegral(Integer betIntegral) {
		this.betIntegral = betIntegral;
	}
		
}
