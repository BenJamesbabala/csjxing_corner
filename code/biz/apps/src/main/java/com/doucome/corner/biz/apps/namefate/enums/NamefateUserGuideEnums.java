package com.doucome.corner.biz.apps.namefate.enums;


public enum NamefateUserGuideEnums {

	/**
	 * 是否完成缘份测试
	 */
	NAMEFATE_TEST("namefate_test", 1L) ,
	
	UNKNOWN("", 0L) ,
	;
	
	private NamefateUserGuideEnums(String guideStr, long value) {
		this.guideStr = guideStr;
		this.value = value;
	}
	
	private String guideStr;
	
	private long value;
	
	public String getGuideStr() {
		return guideStr;
	}

	public long getValue() {
		return value;
	}

	public static NamefateUserGuideEnums toEnum(String guideStr) {
		for (NamefateUserGuideEnums guide: NamefateUserGuideEnums.values()) {
			if (guide.getGuideStr().equals(guideStr)) {
				return guide;
			}
		}
		return UNKNOWN;
	}
	
	public long getDoneValue(long oldValue) {
		return oldValue | getValue();
	}
	
	/**
	 * 
	 * @param guideEnum
	 * @param guideValue
	 * @return
	 */
	public boolean isGuideDone(long guideValue) {
		return (getValue() & guideValue) > 0;
	}
}
