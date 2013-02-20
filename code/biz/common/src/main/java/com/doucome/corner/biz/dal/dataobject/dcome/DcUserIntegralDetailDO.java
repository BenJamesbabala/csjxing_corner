package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 用户积分详细记录
 * 
 * @author langben 2012-8-27
 * 
 */
public class DcUserIntegralDetailDO extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965788650794520390L;

	private Long id;

	/**
	 * userId
	 */
	private Long userId;

	/**
	 * 积分数
	 */
	private Integer integralCount;

	/**
	 * 积分来源
	 */
	private String source;

	/**
	 * 描述信息
	 */
	private String integralDesc;
	/**
	 * 
	 */
	private String readStatus;
	
	private Date gmtCreate;

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

	public Integer getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(Integer integralCount) {
		this.integralCount = integralCount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIntegralDesc() {
		return integralDesc;
	}

	public void setIntegralDesc(String integralDesc) {
		this.integralDesc = integralDesc;
	}

	public String getReadStatus() {
		return readStatus;
	}
	
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}
