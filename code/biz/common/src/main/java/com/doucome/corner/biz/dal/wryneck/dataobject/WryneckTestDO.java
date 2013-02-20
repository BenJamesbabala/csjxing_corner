package com.doucome.corner.biz.dal.wryneck.dataobject;

import java.util.Date;

/**
 * 歪脖子UserDO
 * @author langben 2013-1-1
 *
 */
public class WryneckTestDO {
	
	private Long id;
	
	private String name;
	/**
     * 外部平台id
     */
    private String showText;
    
    private Date gmtCreate;

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

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}
