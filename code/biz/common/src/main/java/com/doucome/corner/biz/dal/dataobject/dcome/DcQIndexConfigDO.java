package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * QQ空间首页 配置
 * @author langben 2012-7-26
 *
 */
public class DcQIndexConfigDO extends AbstractModel {

	/**
	 * ID
	 */
	private Long id ;
	
	/**
	 * 系统APPid
	 */
	private Long systemId ;
	
	/**
	 * 场景ID
	 */
	private Long sceneId ;
	
	/**
	 * 状态 Online Prepub
	 */
	private String pubStatus ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public String getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}

		
}
