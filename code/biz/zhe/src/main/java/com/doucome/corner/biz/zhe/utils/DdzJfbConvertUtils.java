package com.doucome.corner.biz.zhe.utils;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.constant.DecimalConstant;

/**
 * ���ֱ�
 * @author langben 2012-12-24
 *
 */
public class DdzJfbConvertUtils {
	
	public static final BigDecimal JFB_RATE = new BigDecimal(0.9) ;

	/**
	 * ת�������ֱ�
	 * @param amount
	 * @return
	 */
	public static int convertMoney2Jfb(BigDecimal amount){
		
		if(amount == null){
			return 0 ;
		}
		
		int jfbAmount = amount.multiply(DecimalConstant.HUNDRED).intValue() ;
		
		return (int)(jfbAmount * 0.9) ;
	}
	
	/**
	 * 
	 * @return  ��ʽ 0.05 ��ʾ 5%
	 */
	public static BigDecimal convertJfbCommissionRate(int userJfbAmount , BigDecimal realPayFee) {
		
		BigDecimal userJfb = new BigDecimal(userJfbAmount) ;
		BigDecimal price = realPayFee.multiply(DecimalConstant.HUNDRED) ;
		BigDecimal rate = userJfb.divide(price , 4, BigDecimal.ROUND_HALF_EVEN) ;
		
		return rate ;
		
	}
	
	/**
	 * 
	 * @param price
	 * @param jfbRate ��ʽ 0.05 ��ʾ5%
	 * @return
	 */
	public static int calcJfbAmount(BigDecimal price , BigDecimal jfbRate){
		BigDecimal commission = price.multiply(jfbRate) ;
		return commission.multiply(DecimalConstant.HUNDRED).intValue() ;
	}
	
	public static void main(String[] args) {
		int jbf = 100 ;
		BigDecimal price = new BigDecimal(100) ;
		System.out.println(convertJfbCommissionRate(jbf, price));
	}
}
