package com.doucome.corner.biz.dal.dataobject;

import com.doucome.corner.biz.dal.model.AbstractModel;

public class DdzTaokeReportSettleStatisticsDO extends AbstractModel {

	/**
	 * 支付宝账号数
	 */
	private Integer alipayCount ;
	
	/**
	 * 集分宝个数
	 */
	private Integer jfbCount ;

	public Integer getAlipayCount() {
		return alipayCount;
	}

	public void setAlipayCount(Integer alipayCount) {
		this.alipayCount = alipayCount;
	}

	public Integer getJfbCount() {
		return jfbCount;
	}

	public void setJfbCount(Integer jfbCount) {
		this.jfbCount = jfbCount;
	}
	
	
}
