package com.doucome.corner.web.zhe.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.utils.DecimalUtils;

/**
 * 
 * @author langben 2012-12-24
 *
 */
public class PromotionPriceModel {

	private BigDecimal userCommission ;
	
	private BigDecimal userCommissionRate ;
	
	private BigDecimal promotionPrice ;
	
	private BigDecimal userJfbRate ;
	
	private Integer userJfb ;
	
	private boolean hasPromotion ;
	
	public String getPromotionPriceFormat(){
		return DecimalUtils.format(this.promotionPrice, "0.00") ;
	}
	
	
	public String getUserCommissionFormat(){
		return DecimalUtils.format(this.userCommission, "0.00") ;
	}
	
	public String getUserJfbRateFormat(){
		return DecimalUtils.format(this.userJfbRate, "0.00") ;
	}
	
	public String getUserJfbByMoneyFormat(){
		int jfb = getUserJfb() ;
    	BigDecimal money = new BigDecimal(jfb).divide(DecimalConstant.HUNDRED , 4, BigDecimal.ROUND_HALF_EVEN) ;
		return DecimalUtils.format(money, "0.00") ;
	}
	
	/**
	 * ----------------------------------------------------------
	 */

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
	
	public BigDecimal getUserJfbRate() {
		return userJfbRate;
	}

	public void setUserJfbRate(BigDecimal userJfbRate) {
		this.userJfbRate = userJfbRate;
	}

	public Integer getUserJfb() {
		return userJfb;
	}

	public void setUserJfb(Integer userJfb) {
		this.userJfb = userJfb;
	}
	
}
