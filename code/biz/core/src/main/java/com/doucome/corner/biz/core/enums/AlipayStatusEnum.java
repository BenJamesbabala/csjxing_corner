package com.doucome.corner.biz.core.enums;

/**
 * 
 * @author ze2200
 *
 */
public enum AlipayStatusEnum {
	SUCCESS("S"),
	FAIL("F");
	
	private String value;
	
	private AlipayStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
