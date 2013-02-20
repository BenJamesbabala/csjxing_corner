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
public class HsDailyFateCondition {
	
	private Integer hsId;
	
	private Date gmtDay;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		Date[] dates = getDayStartEnd(gmtDay);
		if (dates != null) {
			condition.put("dayStart", dates[0]);
			condition.put("dayEnd", dates[1]);
		}
		condition.put("hsId", getHsId());
		return condition ;
	}
	
	private static Date[] getDayStartEnd(Date time) {
		if (time == null) {
			return null;
		}
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar = org.apache.commons.lang.time.DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH);
		dates[0] = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
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

	public Date getGmtDay() {
		return gmtDay;
	}

	public void setGmtDay(Date gmtDay) {
		this.gmtDay = gmtDay;
	}
}
