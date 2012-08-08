package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

public class DdzSyncReportTaskDO  extends AbstractModel {

	/**
	 * PK
	 */
	private int id ;
	
	/**
	 * yyyyMMdd 格式
	 */
	private String taskId ;
	
	/**
	 * 任务跑完后的输出结果（JSON）
	 */
	private String runoutData ;
	
	/**
	 * T OR  F
	 */
	private String isSuccess ;
	
	/**
	 * 本次任务报表会员数
	 */
	private Integer reportMembCount ; 
	
	private Integer successCount ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;
	
	private Date gmtReport ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getRunoutData() {
		return runoutData;
	}

	public void setRunoutData(String runoutData) {
		this.runoutData = runoutData;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getReportMembCount() {
		return reportMembCount;
	}

	public void setReportMembCount(Integer reportMembCount) {
		this.reportMembCount = reportMembCount;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	
}
