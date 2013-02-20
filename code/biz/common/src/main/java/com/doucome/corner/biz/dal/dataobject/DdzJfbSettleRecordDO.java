package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ���ֱ����ż�¼
 * @author langben 2012-12-19
 *
 */
public class DdzJfbSettleRecordDO extends AbstractModel {

	private Long id ;
	
	/**
	 * �ƻ����ŵ�֧������
	 */
	private Integer planAlipayCount ;
	
	/**
	 * �ɹ����ŵ�֧������
	 */
	private Integer successAlipayCount ;
	
	/**
	 * �ƻ����ŵļ��ֱ���
	 */
	private Integer planJfbCount ;
	
	/**
	 * �ɹ����ŵļ��ֱ���
	 */
	private Integer successJfbCount ;
	
	/**
	 * ���׺�
	 */
	private String tradeNo ;
	
	/**
	 * �������κ�
	 */
	private String settleBatchno ;
	
	/**
	 * �Ƿ��Ѿ�����
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
