package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.model.AbstractModel;

public class DdzTaokeReportSettleDO extends AbstractModel {

	private String tradeId;

	private int id;

	private BigDecimal settleFee;

	private String settleStatus;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getSettleFee() {
		return settleFee;
	}

	public void setSettleFee(BigDecimal settleFee) {
		this.settleFee = settleFee;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}


}
