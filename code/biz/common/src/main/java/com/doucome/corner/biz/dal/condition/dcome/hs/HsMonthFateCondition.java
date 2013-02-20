package com.doucome.corner.biz.dal.condition.dcome.hs;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 豆蔻商品查询条件
 * @author langben 2012-7-21
 *
 */
public class HsMonthFateCondition {
	
	private Integer hsId;
	
	private Date gmtMonth;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		Date[] dates = getMonthStartEnd(gmtMonth);
		if (dates != null) {
			condition.put("monthStart", dates[0]);
			condition.put("monthEnd", dates[1]);
		}
		condition.put("hsId", getHsId());
		return condition ;
	}
	
	private static Date[] getMonthStartEnd(Date time) {
		if (time == null) {
			return null;
		}
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar = org.apache.commons.lang.time.DateUtils.truncate(calendar, Calendar.MONDAY);
		dates[0] = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		dates[1] = calendar.getTime();
		return dates;
	}

	public Integer getHsId() {
		return hsId;
	}

	public void setHsId(Integer hsId) {
		this.hsId = hsId;
	}

	public Date getGmtMonth() {
		return gmtMonth;
	}

	public void setGmtMonth(Date gmtMonth) {
		this.gmtMonth = gmtMonth;
	}
}
