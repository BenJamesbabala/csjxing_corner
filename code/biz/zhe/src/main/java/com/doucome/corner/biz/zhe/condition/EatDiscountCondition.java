package com.doucome.corner.biz.zhe.condition;

/**
 * 折扣规则
 * @author shenjia.caosj 2012-3-29
 *
 */
public class EatDiscountCondition {

	/**
	 * 是否从活动价格计算
	 */
	private boolean needPromotionPrice ;

	public boolean isNeedPromotionPrice() {
		return needPromotionPrice;
	}

	public void setNeedPromotionPrice(boolean needPromotionPrice) {
		this.needPromotionPrice = needPromotionPrice;
	}
	
	
}
