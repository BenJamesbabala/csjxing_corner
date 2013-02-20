package com.doucome.corner.biz.dcome.model.util;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.dcome.model.DcItemDTO;

public class PromotionIntegralUtils {

	/**
	 * 积分对RMB汇率（100:1）
	 */
	private static final int INTEGRAL_EXCHANGE_RATE = 100 ;
	
	private static final int MAX_INTEGRAL = 800 ;
	
	private static final int MIN_INTEGRAL = 10 ;
	
	private static final BigDecimal MIN_COMMISSION = new BigDecimal("0.15");

	/**
	 * 根据Item查询积分
	 * @param item
	 * @return
	 */
	public static int getIntegral(DcItemDTO item){
		
		if(item == null || item.getCommissionRate() == null || item.getItemPrice() == null) {
			return 0 ;
		}
		
		BigDecimal commissionRate = item.getCommissionRate() ;
		BigDecimal itemPrice = item.getItemPromPrice() == null ? item.getItemPrice(): item.getItemPromPrice();
		
		if(commissionRate.compareTo(DecimalConstant.ZERO) <= 0 || itemPrice.compareTo(DecimalConstant.ZERO) <= 0){
			return 0 ;
		}
		
		//佣金
		BigDecimal commission = itemPrice.multiply(commissionRate);
		if(MIN_COMMISSION.compareTo(commission) > 0) {
			return 0;
		}
		//除去技术服务费10%，截取一半
		commission = commission.multiply(new BigDecimal("0.45"));
		int integral = commission.multiply(new BigDecimal(INTEGRAL_EXCHANGE_RATE)).intValue();
		if(integral > MAX_INTEGRAL){
			integral = MAX_INTEGRAL ;
		} else if (integral < MIN_INTEGRAL) {
			integral = MIN_INTEGRAL;
		}
		return integral ;
	}
}
