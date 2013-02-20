package com.doucome.corner.biz.dal.dataobject.dcome;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * pk 活动
 * @author langben 2012-8-10
 *
 */
public class DcPromotionDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 活动状态
	 */
	private String status ;
	
	private BigDecimal limitTopPrice ;
	
	/**
	 * 活动类型
	 */
	private String promType ;
	
	private Date startTime ;
	
	private Date endTime ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPromType() {
		return promType;
	}

	public void setPromType(String promType) {
		this.promType = promType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getLimitTopPrice() {
		return limitTopPrice;
	}

	public void setLimitTopPrice(BigDecimal limitTopPrice) {
		this.limitTopPrice = limitTopPrice;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	
	
}
