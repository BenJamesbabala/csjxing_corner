package com.doucome.corner.biz.dcome.enums;

/**
 * ��Ϣ�Ķ�״̬.
 * @author ze2200
 *
 */
public enum DcMessageReadStatus {
	YES("Y"),
	NO("N");
	
	private String value;
	
	private DcMessageReadStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
