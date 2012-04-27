package com.doucome.corner.web.common.toolbox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilsToolbox {

	public String format(Date date , String format){
		if(date == null){
			return null ;
		}
		DateFormat f = new SimpleDateFormat(format) ;
		return f.format(date) ;
	}
}
