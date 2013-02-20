package com.doucome.corner.biz.dcome.enums;

/**
 * 用户积分来源
 * 此枚举更应该叫做系统消息
 * @author langben 2012-8-27
 * 
 */
public enum DcItemTypeEnum {
	NORMAL("N"),
	VIRTUAL("V"),
	
	/**
	 * 话费
	 */
	HUAFEI("HF") ,
	
	/**
	 * 集分宝
	 */
	JFB("JFB") ,
	
	/**
	 * Q币
	 */
	QQBI("QB") ,
	
	OTHER("O");
	
	private String itemType;
	
	private DcItemTypeEnum(String itemType) {
		this.itemType = itemType;
	}
	
	public String getItemType() {
		return this.itemType;
	}
}