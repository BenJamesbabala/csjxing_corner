package com.doucome.corner.biz.dal.condition;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * ≤È—Øaccount
 * @author shenjia.caosj 2012-5-20
 *
 */
public class DdzAccountSearchCondition {

	private Date gmtCreateStart ;
	
	private Date gmtCreateEnd ;
	
	private String alipayId ;

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

	public String getAlipayId() {
		return StringUtils.trim(alipayId);
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	
	
}
