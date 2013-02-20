package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * userId, type, timeId唯一标示
 * @author ze2200
 *
 */
public class DcGameRuleDO extends AbstractModel {
	
	private static final long serialVersionUID = -184986354427471242L;

	private Long id;
	
	private Long userId;
	
	private String type;
	
	private String timeId;
	
	private String status;
	/**
	 * 
	 */
	private Integer playLimit;
	/**
	 * 点击兑换比率
	 */
	private Integer exchangeAmount;
	/**
	 * 今天已经玩了多少次.
	 */
	private Integer todayPlayCount;
	/**
	 * 
	 */
	private Integer todayClickCount;
	
	private Date gmtModified;
	
	private Date gmtCreate;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getTimeId() {
		return timeId;
	}

	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPlayLimit() {
		return playLimit;
	}

	public void setPlayLimit(Integer playLimit) {
		this.playLimit = playLimit;
	}
	
	public Integer getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(Integer exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	public Integer getTodayClickCount() {
		return todayClickCount == null ? 0: todayClickCount;
	}

	public void setTodayClickCount(Integer todayClickCount) {
		this.todayClickCount = todayClickCount;
	}

	public Integer getTodayPlayCount() {
		return todayPlayCount == null ? 0: todayPlayCount;
	}

	public void setTodayPlayCount(Integer todayPlayCount) {
		this.todayPlayCount = todayPlayCount;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
}
