package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * banner����
 * @author langben 2012-9-27
 *
 */
public class BannerConfigDO extends AbstractModel {

	private Long id ;
	
	/**
	 * bannerId
	 */
	private String bannerId ;
	
	/**
	 * banner ��ַ
	 */
	private String bannerPicUrl ;
	
	/**
	 * ���
	 */
	private Integer width ;
	
	/**
	 * �߶�
	 */
	private Integer height ;
	
	private String targetUrl ;
	
	/**
	 * �Ƿ����´����д�banner
	 */
	private String targetBlank ;
	
	private String status ;
	
	private String memo ;
	
	/**
	 * ���¼���
	 */
	private String bindEvent ;
	
	/**
	 * ���¼�JS
	 */
	private String bindEventFunction ;
	
	private Date gmtModified ;
	
	private Date gmtCreate ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBannerPicUrl() {
		return bannerPicUrl;
	}

	public void setBannerPicUrl(String bannerPicUrl) {
		this.bannerPicUrl = bannerPicUrl;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	
	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTargetBlank() {
		return targetBlank;
	}

	public void setTargetBlank(String targetBlank) {
		this.targetBlank = targetBlank;
	}

	public String getBindEvent() {
		return bindEvent;
	}

	public void setBindEvent(String bindEvent) {
		this.bindEvent = bindEvent;
	}

	public String getBindEventFunction() {
		return bindEventFunction;
	}

	public void setBindEventFunction(String bindEventFunction) {
		this.bindEventFunction = bindEventFunction;
	}
	
}
