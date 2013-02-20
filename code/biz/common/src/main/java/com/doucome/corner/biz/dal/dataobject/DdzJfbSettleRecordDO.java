package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 集分宝发放记录
 * @author langben 2012-12-19
 *
 */
public class DdzJfbSettleRecordDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 计划发放的支付宝数
	 */
	private Integer planAlipayCount ;
	
	/**
	 * 成功发放的支付宝数
	 */
	private Integer successAlipayCount ;
	
	/**
	 * 计划发放的集分宝数
	 */
	private Integer planJfbCount ;
	
	/**
	 * 成功发放的集分宝数
	 */
	private Integer successJfbCount ;
	
	/**
	 * 交易号
	 */
	private String tradeNo ;
	
	/**
	 * 结算批次号
	 */
	private String settleBatchno ;
	
	/**
	 * 是否已经结算
	 */
	private String isSettled ;
	
	private Date gmtSettled ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;
	

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPlanAlipayCount() {
		return planAlipayCount;
	}

	public void setPlanAlipayCount(Integer planAlipayCount) {
		this.planAlipayCount = planAlipayCount;
	}

	public Integer getSuccessAlipayCount() {
		return successAlipayCount;
	}

	public void setSuccessAlipayCount(Integer successAlipayCount) {
		this.successAlipayCount = successAlipayCount;
	}

	public Integer getPlanJfbCount() {
		return planJfbCount;
	}

	public void setPlanJfbCount(Integer planJfbCount) {
		this.planJfbCount = planJfbCount;
	}

	public Integer getSuccessJfbCount() {
		return successJfbCount;
	}

	public void setSuccessJfbCount(Integer successJfbCount) {
		this.successJfbCount = successJfbCount;
	}

	public String getSettleBatchno() {
		return settleBatchno;
	}

	public void setSettleBatchno(String settleBatchno) {
		this.settleBatchno = settleBatchno;
	}

	public String getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}

	public Date getGmtSettled() {
		return gmtSettled;
	}

	public void setGmtSettled(Date gmtSettled) {
		this.gmtSettled = gmtSettled;
	}

}
