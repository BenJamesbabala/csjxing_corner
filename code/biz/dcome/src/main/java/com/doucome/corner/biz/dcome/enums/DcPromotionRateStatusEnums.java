package com.doucome.corner.biz.dcome.enums;

/**
 * ��Ʒ״̬.
 * @author ze2200
 *
 */
public enum DcPromotionRateStatusEnums {
	
	/**
	 * ����״̬
	 */
	NORMAL("N"),
	
	/**
	 * ͶƱ��Ч
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
	 * ��ȡ����ʵ��
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
