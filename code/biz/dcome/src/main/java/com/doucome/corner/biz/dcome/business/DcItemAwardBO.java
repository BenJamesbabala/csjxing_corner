package com.doucome.corner.biz.dcome.business;

import java.math.BigDecimal;

import com.doucome.corner.biz.dcome.model.DcItemDTO;

/**
 * ��Ʒ����ҵ���߼���.
 * @author ze2200
 *
 */
public class DcItemAwardBO {
	/**
	 * ƽ������.
	 */
	private BigDecimal avgCommissionRate;
	/**
	 * 
	 */
	private BigDecimal avgDisCount;
	
	public boolean calculateItemAward(DcItemDTO dcItem) {
		
		return false;
	}
	
	public void setAvgCommissionRate(String avgCommissionRate) {
		this.avgCommissionRate = new BigDecimal(avgCommissionRate);
	}
	
	public void setAvgDisCount(String avgDisCount) {
		this.avgDisCount = new BigDecimal(avgDisCount);
	}
}
