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
public class HsWeekFateCondition {
	
	private Integer hsId;
	
	private Date gmtWeek;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		Date[] dates = getWeekStartEnd(gmtWeek);
		if (dates != null) {
			condition.put("weekStart", dates[0]);
			condition.put("weekEnd", dates[1]);
		}
		condition.put("hsId", getHsId());
		return condition ;
	}
	
	private static Date[] getWeekStartEnd(Date time) {
		if (time == null) {
			return null;
		}
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar = org.apache.commons.lang.time.DateUtils.ceiling(calendar, Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		dates[0] = calendar.getTime();
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
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

	public Date getGmtWeek() {
		return gmtWeek;
	}

	public void setGmtWeek(Date gmtWeek) {
		this.gmtWeek = gmtWeek;
	}
}
