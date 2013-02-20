package com.doucome.corner.biz.zhe.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author langben 2012-5-22
 *
 */
public class RecommendBatchDate {
	
	private static final String format = "yyyyMMdd" ;

	private Date date ;

	private String dateStr ;
	
	public RecommendBatchDate(Date date) {
		this.date = date;
		DateFormat f = new SimpleDateFormat(format) ;
		dateStr = f.format(date) ;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	
}
