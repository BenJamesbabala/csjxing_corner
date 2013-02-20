package com.doucome.corner.biz.dcome.enums;

/**
 * 商品状态.
 * @author ze2200
 *
 */
public enum DcPromotionRateStatusEnums {
	
	/**
	 * 正常状态
	 */
	NORMAL("N"),
	
	/**
	 * 投票无效
	 */
	DISABLE("D");
	
	private String value;
	
	private DcPromotionRateStatusEnums(String value) {
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
	public static DcPromotionRateStatusEnums getInstance(String status) {
		try {
			return valueOf(DcPromotionRateStatusEnums.class, status);
		} catch (Exception e) {
			for (DcPromotionRateStatusEnums statusEnum: values()) {
				if (statusEnum.getValue().equalsIgnoreCase(status)) {
					return statusEnum;
				}
			}
		}
		return null;
	}
	
}
