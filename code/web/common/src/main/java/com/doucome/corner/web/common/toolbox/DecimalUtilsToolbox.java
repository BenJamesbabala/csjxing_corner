package com.doucome.corner.web.common.toolbox;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.doucome.corner.biz.core.utils.DecimalUtils;

public class DecimalUtilsToolbox extends DecimalUtils {

	
	public String format (BigDecimal decimal , String pattern){
		if(decimal == null){
			return "" ;
		}
		DecimalFormat f = new DecimalFormat(pattern) ;
		return f.format(decimal) ;
	}
	
	/**
	 * 只输出小数
	 * @param decimal
	 * @param pattern
	 * @return
	 */
	public String fractionFormat (BigDecimal decimal , String pattern){
		if(decimal == null){
			return "" ;
		}
		DecimalFormat f = new DecimalFormat(pattern) ;
		f.setMaximumIntegerDigits(0) ;
		return f.format(decimal) ;
	}
	
	public BigDecimal divide(BigDecimal d1 , String decimalStr){
		return d1.divide(new BigDecimal(decimalStr)) ;
	}
	
	/**
	 * createInt
	 * @param str
	 * @return
	 */
	public int createInt(String str){
		if(StringUtils.isBlank(str)){
			return 0 ;
		}
		return NumberUtils.createInteger(str) ;
	}
	
	public int createInt(Integer i){
		if(i == null){
			return 0 ;
		}
		return i ;
	}
	
	public int createInt(Integer i , int defaultVal){
		if(i == null){
			return defaultVal ;
		}
		return createInt(i) ;
	}
	
	/**
	 * createInt
	 * @param str
	 * @return
	 */
	public int createInt(String str ,int defaultVal ){
		if(StringUtils.isBlank(str)){
			return defaultVal ;
		}
		return createInt(str) ;
	} 
	
	/**
	 * multiply
	 * @param d1
	 * @param d2
	 * @return
	 */
	public BigDecimal multiply(BigDecimal d1 , int d2){
		if(d1 == null){
			return null ;
		}
		return d1.multiply(new BigDecimal(d2)) ;
	}
}
