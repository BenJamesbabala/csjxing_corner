package com.doucome.corner.biz.dcome.enums;

/**
 * 拍卖活动状态，用于取数.
 * @author ze2200
 *
 */
public enum DcShareStatusEnum {
	YES("Y"),
	NO("N");
	
	private String value;
	
	private DcShareStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
