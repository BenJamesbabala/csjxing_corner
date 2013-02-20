package com.doucome.corner.biz.dal.condition.dcome;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author ze2200
 *
 */
public class DcPromotionCondition {
	private String name;
	
	private String status;
	
	private Date startDate;
	
	private Date endDate;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this ,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
