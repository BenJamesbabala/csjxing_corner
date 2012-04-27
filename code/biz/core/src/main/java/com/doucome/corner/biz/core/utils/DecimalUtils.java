package com.doucome.corner.biz.core.utils;

import java.math.BigDecimal;

public class DecimalUtils {

	/**
	 * �ж� d1 ���� d2
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean greatThan(BigDecimal d1 , BigDecimal d2){
		int i = d1.compareTo(d2) ;
		return i == 1 ;
	}
	
	/**
	 * �ж� d1 С�� d2
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean lessThan(BigDecimal d1 , BigDecimal d2){
		int i = d1.compareTo(d2) ;
		return i == -1 ;
	}
	
	/**
	 * �ж� d1 ���ڵ��� d2
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean greatEqualThan(BigDecimal d1 , BigDecimal d2){
		int i = d1.compareTo(d2) ;
		return i == -1 ? false : true ;
	}
	
	/**
	 * �ж�d1 С�ڵ��� d2
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean lessEqualThan(BigDecimal d1 , BigDecimal d2){
		int i = d1.compareTo(d2) ;
		return i == 1 ? false : true ;
	}
	
	/**
	 * multiply
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal d1 , BigDecimal d2){
		if(d1 == null || d2 == null){
			return null ;
		}
		return d1.multiply(d2) ;
	}
	
	public static BigDecimal divide(BigDecimal d1 , BigDecimal d2){
		if(d1 == null || d2 == null){
			return null ;
		}
		return d1.divide(d2) ;
	}
	
	public static BigDecimal substract(BigDecimal d1 , BigDecimal d2){
		if(d1 == null || d2 == null){
			return null ;
		}
		return d1.subtract(d2) ;
	}

	public static BigDecimal add(BigDecimal d1 , BigDecimal d2){
		if(d1 == null || d2 == null){
			return null ;
		}
		return d1.add(d2) ;
	}
}
