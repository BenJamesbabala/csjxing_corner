package com.doucome.corner.biz.dcome.model.param;

import com.doucome.corner.biz.dcome.enums.DcGameAwardEnum;


public class DcGameAwardDTO {
	/**
	 * ��Ϸ����.��ӦDcGameAwardEnum
	 */
	private DcGameAwardEnum gameAward;
	
	public void setAward(DcGameAwardEnum gameAward) {
		this.gameAward = gameAward;
	}
	
	public String getAward() {
		return this.gameAward.getAward();
	}
	
	public int getAmount() {
		return gameAward.getAmount();
	}
}
