package com.doucome.corner.web.common.toolbox;

import java.util.Date;

import com.doucome.corner.biz.core.utils.DateTool;

/**
 * 请直接修改com.doucome.corner.biz.core.utils.DateTool
 * 
 * @author ib 2012-5-12 下午06:29:25
 */
public class DateUtilsToolbox extends DateTool {
	
	public Date now(){
		return new Date() ;
	}
	
	public long currentTimeMillis(){
		return System.currentTimeMillis() ;
	}
	
	public long timeMillisOf(Date date){
		long millseconds = date == null ? 0 : date.getTime() ;
		return millseconds ;
	}
}
