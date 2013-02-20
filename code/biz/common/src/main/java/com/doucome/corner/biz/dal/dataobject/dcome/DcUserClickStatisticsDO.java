package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcUserClickStatisticsDO extends AbstractModel {

	private Long id ;
	
	/**
	 * userId
	 */
	private Long userId ;
	
	/**
	 * clickCount
	 */
	private Integer clickCount ;
	
	/**
	 * clickType
	 */
	private String clickType ;
	
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

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
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

	public String getClickType() {
		return clickType;
	}

	public void setClickType(String clickType) {
		this.clickType = clickType;
	}
	
	
}
