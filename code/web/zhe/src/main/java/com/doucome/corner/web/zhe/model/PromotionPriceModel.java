package com.doucome.corner.web.zhe.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.utils.DecimalUtils;

public class PromotionPriceModel {

	private BigDecimal userCommission ;
	
	private BigDecimal userCommissionRate ;
	
	private BigDecimal promotionPrice ;
	
	private boolean hasPromotion ;

	public BigDecimal getUserCommission() {
		return userCommission;
	}

	public void setUserCommission(BigDecimal userCommission) {
		this.userCommission = userCommission;
	}

	public BigDecimal getUserCommissionRate() {
		return userCommissionRate;
	}

	public void setUserCommissionRate(BigDecimal userCommissionRate) {
		this.userCommissionRate = userCommissionRate;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public boolean isHasPromotion() {
		return hasPromotion;
	}

	public void setHasPromotion(boolean hasPromotion) {
		this.hasPromotion = hasPromotion;
	}
	
	public String getPromotionPriceFormat(){
		return DecimalUtils.format(this.promotionPrice, "0.00") ;
	}
	
	
	public String getUserCommissionFormat(){
		return DecimalUtils.format(this.userCommission, "0.00") ;
	}
	
}
