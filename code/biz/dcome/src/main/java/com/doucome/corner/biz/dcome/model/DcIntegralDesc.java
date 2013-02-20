package com.doucome.corner.biz.dcome.model;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ��������.վ�ڻ��ֵĽǶȣ����ִ�fromObjId�����ӵ�toObjId��.
 * @author ze2200
 *
 */
public class DcIntegralDesc extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��Դ��������.
	 */
	private String fromObjName;
	/**
	 * ��Դ����id.
	 */
	private Long fromObjId;
	/**
	 * ��ȡ��������.
	 */
	private String toObjName;
	/**
	 * ��ȡ����id.
	 */
	private Long toObjId;
	/**
	 * ������Ϣ
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
