package com.doucome.corner.biz.core.utils;

import java.math.BigDecimal;

public class NumberUtils {

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static int integerToInt (Integer i){
		if(i == null){
			return 0 ;
		}
		return i ;
	}
	
	public static boolean isGreaterEqual(BigDecimal decimal1 , BigDecimal decimal2){
		if(decimal1 == null){
			return false ;
		}
		if(decimal2 == null){
			return true ;
		}
		int compareVal = decimal1.compareTo(decimal2) ;
		return compareVal == -1 ? false : true ;
	}
	
	public static boolean isLessEqual(BigDecimal decimal1 , BigDecimal decimal2){
		if(decimal1 == null){
			return true ;
		}
		if(decimal2 == null){
			return false ;
		}
		int compareVal = decimal1.compareTo(decimal2) ;
		return compareVal == 1 ? false : true ;
	}
}
