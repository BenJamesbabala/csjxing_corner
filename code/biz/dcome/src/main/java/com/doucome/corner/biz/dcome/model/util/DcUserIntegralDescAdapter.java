package com.doucome.corner.biz.dcome.model.util;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcUserIntegralDescKeyEnums;

/**
 * DC_USER_INTEGRAL_RECORD.INETRGAL_DESC 适配解析类
 * @author langben 2013-1-21
 *
 */
public class DcUserIntegralDescAdapter {

	private Map<String,String> descMap ;
	
	public DcUserIntegralDescAdapter(Map<String,String> descMap)  {
		if(descMap == null) {
			descMap = new HashMap<String,String>() ;
		}
		this.descMap = descMap ;
	}
	
	public String getTitle(){
		DcIntegralSourceEnums source = getSourceEnums() ;
		if(source == DcIntegralSourceEnums.BUY_REBATE || source == DcIntegralSourceEnums.EXCHANGE_ITEM ||
				source == DcIntegralSourceEnums.REFUND) {
			String itemTitle = descMap.get(DcUserIntegralDescKeyEnums.ITEM_TITLE.getValue()) ;
			int exItemNum = NumberUtils.parseInt(descMap.get(DcUserIntegralDescKeyEnums.EX_ITEM_NUM.getValue())) ;
			if(exItemNum <= 1){
				return itemTitle ;
			} else {
				return itemTitle + " x " + exItemNum ; 
			}
 		} else if(source == DcIntegralSourceEnums.INVITED_USER) { //邀请新用户
 			return descMap.get(DcUserIntegralDescKeyEnums.INVITED_USER_NICK) ;
 		} else {
 			return "" ;
 		}
	}
	
	public String getSource() {
		String source = descMap.get(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue()) ;
		return source ;
	}
	
	public DcIntegralSourceEnums getSourceEnums() {
		String source = descMap.get(DcUserIntegralDescKeyEnums.DESC_SOURCE.getValue()) ;
		return DcIntegralSourceEnums.get(source) ;
	}
}
