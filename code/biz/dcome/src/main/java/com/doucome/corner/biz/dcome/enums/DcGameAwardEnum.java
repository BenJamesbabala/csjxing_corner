package com.doucome.corner.biz.dcome.enums;

/**
 * ÓÎÏ·½±Àø
 * @author ze2200
 *
 */
public enum DcGameAwardEnum {
	NOTHING("NO", 0),
	INTEGRAL("INTE", 5),
	WISH_STAR("STAR", 1);
	
	private String award;
	
	private int amount;
	
	private DcGameAwardEnum(String award, int amount) {
		this.award = award;
		this.amount = amount;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public String getAward() {
		return this.award;
	}
}
