package com.doucome.corner.biz.dcome.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dcome.enums.DcUserExtendDescEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;


public class DcUserUtils {
	
	private static final Log log = LogFactory.getLog(DcUserUtils.class) ;

	/**
	 * 更新本月的兑换次数
	 * @param oriExtendDesc
	 * @return
	 */
	public static Map<String,String> incrExchangeCount(Map<String,String> exMap){
		String exMonth = exMap.get(DcUserExtendDescEnums.EXCHANGE_MONTH.getValue()) ;
		int exCount = NumberUtils.parseInt(exMap.get(DcUserExtendDescEnums.EXCHANGE_COUNT.getValue())) ;
		
		DateFormat df = new SimpleDateFormat(DcUserDTO.EXCHANGE_MON_FORMAT) ;
		Date curDate = new Date() ;
		if(StringUtils.isNotBlank(exMonth)){
			try {
				Date exDate = df.parse(exMonth) ;
				if(!DateUtils.isSameMonth(curDate, exDate)){
					exMonth = null ;
					exCount = 0 ;
				}
			} catch (ParseException e) {
				log.error(e.getMessage() , e) ;
				exMonth = null ;
				exCount = 0 ;
			}
		} 
		
		if(exMonth == null){
			exMonth = df.format(curDate) ;
		}
		
		exCount = exCount + 1 ;
		
		exMap.put(DcUserExtendDescEnums.EXCHANGE_MONTH.getValue(), exMonth) ;
		exMap.put(DcUserExtendDescEnums.EXCHANGE_COUNT.getValue(), String.valueOf(exCount)) ;
		return exMap ;
	}
}
