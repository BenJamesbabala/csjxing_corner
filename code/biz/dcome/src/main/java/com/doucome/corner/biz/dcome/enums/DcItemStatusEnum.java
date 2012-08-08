package com.doucome.corner.biz.dcome.enums;

/**
 * 商品状态.
 * @author ze2200
 *
 */
public enum DcItemStatusEnum {
	/**
	 * 正常状态
	 */
	NORMAL("N"),
	/**
	 * 下架状态
	 */
	DISABLE("D");
	
	private String value;
	
	private DcItemStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	/**
	 * 获取美剧实例
	 * @param status
	 * @return
	 */
	public static DcItemStatusEnum getInstance(String status) {
		try {
			return valueOf(DcItemStatusEnum.class, status);
		} catch (Exception e) {
			for (DcItemStatusEnum statusEnum: values()) {
				if (statusEnum.getValue().equalsIgnoreCase(status)) {
					return statusEnum;
				}
			}
		}
		return null;
	}
}
