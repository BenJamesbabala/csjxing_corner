package com.doucome.corner.biz.core.taobao.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 淘宝客接口用的时间格式
 * @author shenjia.caosj 2012-2-26
 *
 */
public class TaobaokeDate {

	private static final String pattern = "yyyyMMdd" ;
	
	private Date date ;
	
	private String dateFormat ;
	
	@SuppressWarnings("unused")
	private TaobaokeDate() {}
	
	public TaobaokeDate(Date date){
		this.date = date ;
		this.dateFormat = new SimpleDateFormat(pattern).format(date) ;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
