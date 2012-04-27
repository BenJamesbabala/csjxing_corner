package com.doucome.corner.biz.core.taobao.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.model.AbstractModel;

public class TaokeReportSearchCondition extends AbstractModel {

	private Date gmtPaidStart;

	private Date gmtPaidEnd;

	private String settleStatus;

	private String settleUid;

	private String settleTaobaoNick;

	private String settleAlipay;

	private Date gmtSettledStart;

	private Date gmtSettledEnd;
	
	private String payBatchno ;
	
	

	public String getPayBatchno() {
		return payBatchno;
	}

	public void setPayBatchno(String payBatchno) {
		this.payBatchno = StringUtils.trim(payBatchno);
	}

	public String getSettleUid() {
		return settleUid;
	}

	public void setSettleUid(String settleUid) {
		this.settleUid = StringUtils.trim(settleUid);
	}

	public String getSettleAlipay() {
		return settleAlipay;
	}

	public void setSettleAlipay(String settleAlipay) {
		this.settleAlipay = StringUtils.trim(settleAlipay);
	}

	public Date getGmtPaidStart() {
		return gmtPaidStart;
	}

	public void setGmtPaidStart(Date gmtPaidStart) {
		this.gmtPaidStart = gmtPaidStart;
	}

	public Date getGmtPaidEnd() {
		return gmtPaidEnd;
	}

	public void setGmtPaidEnd(Date gmtPaidEnd) {
		this.gmtPaidEnd = gmtPaidEnd;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = StringUtils.trim(settleStatus);
	}

	public Date getGmtSettledStart() {
		return gmtSettledStart;
	}

	public void setGmtSettledStart(Date gmtSettledStart) {
		this.gmtSettledStart = gmtSettledStart;
	}

	public Date getGmtSettledEnd() {
		return gmtSettledEnd;
	}

	public void setGmtSettledEnd(Date gmtSettledEnd) {
		this.gmtSettledEnd = gmtSettledEnd;
	}

	public String getSettleTaobaoNick() {
		return settleTaobaoNick;
	}

	public void setSettleTaobaoNick(String settleTaobaoNick) {
		this.settleTaobaoNick = StringUtils.trim(settleTaobaoNick);
	}
}
