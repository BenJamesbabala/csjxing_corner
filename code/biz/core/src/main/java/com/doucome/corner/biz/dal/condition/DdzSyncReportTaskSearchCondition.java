package com.doucome.corner.biz.dal.condition;

import java.util.Date;

/**
 * 
 * @author shenjia.caosj 2012-4-24
 *
 */
public class DdzSyncReportTaskSearchCondition {

	/**
	 * 查询开始时间（包含）
	 */
	private Date gmtReportStart ;
	
	/**
	 * 查询结束时间（包含）
	 */
	private Date gmtReportEnd ;

	public Date getGmtReportStart() {
		return gmtReportStart;
	}

	public void setGmtReportStart(Date gmtReportStart) {
		this.gmtReportStart = gmtReportStart;
	}

	public Date getGmtReportEnd() {
		return gmtReportEnd;
	}

	public void setGmtReportEnd(Date gmtReportEnd) {
		this.gmtReportEnd = gmtReportEnd;
	}

	
	
	
	
}
