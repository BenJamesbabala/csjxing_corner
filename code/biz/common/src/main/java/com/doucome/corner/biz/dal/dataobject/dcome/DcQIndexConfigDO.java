package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

/**
 * QQ�ռ���ҳ ����
 * @author shenjia.caosj 2012-7-26
 *
 */
public class DcQIndexConfigDO {

	/**
	 * ID
	 */
	private Long id ;
	
	/**
	 * ϵͳAPPid
	 */
	private Long systemId ;
	
	/**
	 * ����ID
	 */
	private Long sceneId ;
	
	/**
	 * ״̬ Online Prepub
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
