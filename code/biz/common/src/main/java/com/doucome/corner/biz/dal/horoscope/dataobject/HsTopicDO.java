package com.doucome.corner.biz.dal.horoscope.dataobject;

import java.util.Date;

/**
 * ÐÇ×ù»°Ìâ.
 * @author ze2200
 *
 */
public class HsTopicDO {
	private Long id;
	
	private String title;
	
	private String content;
	
	private String picture;
	
	private Date gmtDay;
	
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getGmtDay() {
		return gmtDay;
	}

	public void setGmtDay(Date gmtDay) {
		this.gmtDay = gmtDay;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
}
