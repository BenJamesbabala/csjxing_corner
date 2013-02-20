package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

/**
 * ¶¹Þ¢ÏµÍ³APP
 * @author langben 2012-7-26
 *
 */
public class DcSystemDO {

	private Long id ;
	
	private String name ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
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
