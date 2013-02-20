package com.doucome.corner.biz.dcome.enums;

import java.math.BigDecimal;
/**
 * 
 * 
 * @author ze2200
 */
public enum DcUserLevelEnum {
	LEVEL0(0, new BigDecimal("1"),  new int[] {0, 30}),
	LEVEL1(1, new BigDecimal("0.95"), new int[] {30, 50}),
	LEVEL2(2, new BigDecimal("0.90"), new int[] {50, 100}),
	LEVEL3(3, new BigDecimal("0.85"), new int[] {100, 200}),
	LEVEL4(4, new BigDecimal("0.80"), new int[] {200, 400}),
	LEVEL5(5, new BigDecimal("0.75"), new int[] {400, 100000});
	
	private Integer value;
	
	private BigDecimal discount;
	
	private int[] commissionRanges;
	
	private DcUserLevelEnum(int value, BigDecimal discount, int[] commissionRanges) {
		this.value = value;
		this.discount = discount;
		this.commissionRanges = commissionRanges;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public BigDecimal getDiscount() {
		return this.discount;
	}
	
	public int[] getCommissionRanges() {
		return this.commissionRanges;
	}
	
	public static DcUserLevelEnum toEnum(Integer level) {
		for (DcUserLevelEnum levelEnum: DcUserLevelEnum.values()) {
			if (levelEnum.getValue().equals(level)) {
				return levelEnum;
			}
		}
		return LEVEL0;
	}
	
	public static DcUserLevelEnum toEnum(BigDecimal userCommission) {
		if (userCommission == null) {
			return LEVEL0;
		}
		int intCommission = userCommission.intValue();
		for (DcUserLevelEnum levelEnum: DcUserLevelEnum.values()) {
			int[] commissionRange = levelEnum.getCommissionRanges();
			if (commissionRange[0] <= intCommission && commissionRange[1] > intCommission) {
				return levelEnum;
			}
		}
		return LEVEL0;
	}
}
