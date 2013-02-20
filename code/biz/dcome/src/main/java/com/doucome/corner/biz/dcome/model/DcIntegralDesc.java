package com.doucome.corner.biz.dcome.model;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 积分描述.站在积分的角度，积分从fromObjId来，加到toObjId上.
 * @author ze2200
 *
 */
public class DcIntegralDesc extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 来源对象名称.
	 */
	private String fromObjName;
	/**
	 * 来源对象id.
	 */
	private Long fromObjId;
	/**
	 * 获取对象名称.
	 */
	private String toObjName;
	/**
	 * 获取对象id.
	 */
	private Long toObjId;
	/**
	 * 其他信息
	 */
	private String otherInfo;
	
	public String getFromObjName() {
		return fromObjName;
	}
	
	public void setFromObjName(String fromObjName) {
		this.fromObjName = fromObjName;
	}
	
	public Long getFromObjId() {
		return fromObjId;
	}
	
	public void setFromObjId(Long fromObjId) {
		this.fromObjId = fromObjId;
	}
	
	public String getToObjName() {
		return toObjName;
	}
	
	public void setToObjName(String toObjName) {
		this.toObjName = toObjName;
	}
	
	public Long getToObjId() {
		return toObjId;
	}
	
	public void setToObjId(Long toObjId) {
		this.toObjId = toObjId;
	}
	
	public String getOtherInfo() {
		return otherInfo;
	}
	
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
}
