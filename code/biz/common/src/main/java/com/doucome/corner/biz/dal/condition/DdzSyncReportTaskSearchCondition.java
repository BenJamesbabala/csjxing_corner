package com.doucome.corner.biz.dal.condition;

import java.util.Date;

/**
 * 
 * @author shenjia.caosj 2012-4-24
 *
 */
public class DdzSyncReportTaskSearchCondition {

	/**
	 * ��ѯ��ʼʱ�䣨������
	 */
	private Date gmtReportStart ;
	
	/**
	 * ��ѯ����ʱ�䣨������
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
