package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * ��ShortUrl.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-3-13 ����01:36:00
 */
public class ShortUrlDO extends AbstractModel {

	private Integer id;
	/**
	 * ���̺��ֵ
	 */
	private String shorten;
	/**
	 * ԭʼURL
	 */
	private String url;

	/**
	 * ԭʼURL��md5ֵ
	 */
	private String md5Url;
	
	/**
	 * 
	 */
	private Date gmtCreate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShorten() {
		return shorten;
	}

	public void setShorten(String shorten) {
		this.shorten = shorten;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getMd5Url() {
		return md5Url;
	}

	public void setMd5Url(String md5Url) {
		this.md5Url = md5Url;
	}

}
