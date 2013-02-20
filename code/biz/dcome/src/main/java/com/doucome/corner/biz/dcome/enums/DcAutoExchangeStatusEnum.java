package com.doucome.corner.biz.dcome.enums;

/**
 * 
 * @author ze2200
 *
 */
public enum DcAutoExchangeStatusEnum {
	AUDITED("A"),
	UNAUDITED("U"),
	UNKNOWN("");
	
	private String value;
	
	private DcAutoExchangeStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static DcAutoExchangeStatusEnum toEnum(String value) {
		for (DcAutoExchangeStatusEnum temp: DcAutoExchangeStatusEnum.values()) {
			if (temp.getValue().equals(value)) {
				return temp;
			}
		}
		return UNKNOWN;
	}
}
