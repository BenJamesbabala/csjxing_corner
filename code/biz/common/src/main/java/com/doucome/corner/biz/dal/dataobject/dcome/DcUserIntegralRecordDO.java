package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ���ּ�¼
 * @author langben 2013-1-8
 *
 */
public class DcUserIntegralRecordDO extends AbstractModel {

	private Long id ;
	
	/**
	 * �û�ID
	 */
	private Long userId ;
	
	/**
	 * ������
	 */
	private Integer integralCount ;
	
	/**
	 * ����/֧��
	 */
	private String inOutType ;
	
	/**
	 * ��Դ
	 */
	private String source ;
	
	private String integralDesc ;
	
	/**
	 * �������
	 */
	private Integer integralBalance ;
	
	/**
	 * ��Ӧ��DDZ_TAOKE_REPORT.ID
	 */
	private Long taokeReportId ;
	
	/**
	 * �һ�ID
	 */
	private Long exchangeApproveId ;
	
	/**
	 * ����ID
	 */
	private Long settleId ;
	
	/**
	 * ״̬
	 */
	private String status ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(Integer integralCount) {
		this.integralCount = integralCount;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIntegralDesc() {
		return integralDesc;
	}

	public void setIntegralDesc(String integralDesc) {
		this.integralDesc = integralDesc;
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

	public Integer getIntegralBalance() {
		return integralBalance;
	}

	public void setIntegralBalance(Integer integralBalance) {
		this.integralBalance = integralBalance;
	}

	public Long getTaokeReportId() {
		return taokeReportId;
	}

	public void setTaokeReportId(Long taokeReportId) {
		this.taokeReportId = taokeReportId;
	}

	public Long getExchangeApproveId() {
		return exchangeApproveId;
	}

	public void setExchangeApproveId(Long exchangeApproveId) {
		this.exchangeApproveId = exchangeApproveId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSettleId() {
		return settleId;
	}

	public void setSettleId(Long settleId) {
		this.settleId = settleId;
	}
	
}
