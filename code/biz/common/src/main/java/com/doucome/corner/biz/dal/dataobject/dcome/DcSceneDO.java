package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 场景
 * @author langben 2012-7-22
 *
 */
public class DcSceneDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 场景名称
	 */
	private String name ;
	
	/**
	 * 场景描述
	 */
	private String sceneDesc ;
	
	/**
	 * 系统ID
	 */
	private Long systemId ;
	
	/**
	 * 
	 */
	private String categoryIds ;
	
	/**
	 * banner url
	 */
	private String bannerPicUrl ;
	
	/**
	 * 是否激活 T | F
	 */
	private String active ;
	
	private Date gmtModified  ;
	
	private Date gmtCreate ;
	
	public String getBannerPicUrl() {
		return bannerPicUrl;
	}

	public void setBannerPicUrl(String bannerPicUrl) {
		this.bannerPicUrl = bannerPicUrl;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public String getSceneDesc() {
		return sceneDesc;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
