package com.doucome.corner.biz.zhe.model;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

public class JfbExportModel {

	private List<DdzTaokeReportSettleDO> settleList ;
	
	private String settleBatchno ;

	public List<DdzTaokeReportSettleDO> getSettleList() {
		return settleList;
	}

	public void setSettleList(List<DdzTaokeReportSettleDO> settleList) {
		this.settleList = settleList;
	}

	public String getSettleBatchno() {
		return settleBatchno;
	}

	public void setSettleBatchno(String settleBatchno) {
		this.settleBatchno = settleBatchno;
	}
	
}
