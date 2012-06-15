package com.doucome.corner.biz.zhe.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author ze2200
 *
 */
public class SettleResult {
	private boolean isSucc = true;
	private String batchNO;
	private int updateSettleCount = 0;
	private int updateReportCount = 0;
	private String message;

	public boolean isSucc() {
		return isSucc;
	}

	public void setSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}

	public String getBatchNO() {
		return batchNO;
	}
	
	public void setBatchNO(String batchNO) {
		this.batchNO = batchNO;
	}

	public int getUpdateSettleCount() {
		return updateSettleCount;
	}

	public void setUpdateSettleCount(int updateSettleCount) {
		this.updateSettleCount = updateSettleCount;
	}

	public int getUpdateReportCount() {
		return updateReportCount;
	}

	public void setUpdateReportCount(int updateReportCount) {
		this.updateReportCount = updateReportCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
