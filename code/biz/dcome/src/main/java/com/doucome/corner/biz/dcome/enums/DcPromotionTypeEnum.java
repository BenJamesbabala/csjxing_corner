package com.doucome.corner.biz.dcome.enums;

/**
 * �����ö��.
 * @author ze2200
 *
 */
public enum DcPromotionTypeEnum {
	PK("PK") , 
	AUCTION("AU"),
	EXCHANGE("EX");
	
	private String type;
	
	private DcPromotionTypeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
