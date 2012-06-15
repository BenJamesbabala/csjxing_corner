package com.doucome.corner.biz.dal.condition;

import java.util.Date;

public class DdzSearchLogCondition {

	private String alipayId ;
	
	private String searchBrief ;
	
	private String searchWay ;
	
	private Date gmtCreateStart ;
	
	private Date gmtCreateEnd ;
	
	private boolean isUniqueBrief ;

	public boolean isUniqueBrief() {
		return isUniqueBrief;
	}

	public void setUniqueBrief(boolean isUniqueBrief) {
		this.isUniqueBrief = isUniqueBrief;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	public String getSearchBrief() {
		return searchBrief;
	}

	public void setSearchBrief(String searchBrief) {
		this.searchBrief = searchBrief;
	}

	public String getSearchWay() {
		return searchWay;
	}

	public void setSearchWay(String searchWay) {
		this.searchWay = searchWay;
	}

	public Date getGmtCreateStart() {
		return gmtCreateStart;
	}

	public void setGmtCreateStart(Date gmtCreateStart) {
		this.gmtCreateStart = gmtCreateStart;
	}

	public Date getGmtCreateEnd() {
		return gmtCreateEnd;
	}

	public void setGmtCreateEnd(Date gmtCreateEnd) {
		this.gmtCreateEnd = gmtCreateEnd;
	}
	
	
}
