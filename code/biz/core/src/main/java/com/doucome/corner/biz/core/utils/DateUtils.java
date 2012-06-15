package com.doucome.corner.biz.core.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author shenjia.caosj 2012-4-12
 *
 */
public class DateUtils {
	
	private static final int[] CALENDAR_FIELD_LIST =
	  {Calendar.MILLISECOND, Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR_OF_DAY, Calendar.DAY_OF_MONTH, Calendar.MONTH};

	public static Date setTime(Date date , int h , int m , int s ){
		if(date == null){
			return null ;
		}
		Calendar c = Calendar.getInstance() ;
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, h) ;
		c.set(Calendar.MINUTE, m);
		c.set(Calendar.SECOND, s);
		return c.getTime() ;
	}
	
	public static Date trimDate(Date date, int calendarField) {
		if (date == null) {
			return date;
		}
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		for (int field : CALENDAR_FIELD_LIST) {
			if (field == Calendar.DAY_OF_MONTH || field == Calendar.MONDAY) {
				temp.set(field, 1);
			} else {
				temp.set(field, 0);
			}
			if (field == calendarField) {
				return temp.getTime();
			}
		}
		
		return date;
	}
	
	public static Calendar getCalendar(Date date){
		Calendar c = Calendar.getInstance() ;
		c.setTime(date);
		return c ;
	}
}
