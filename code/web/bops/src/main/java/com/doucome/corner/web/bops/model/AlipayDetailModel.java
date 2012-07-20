package com.doucome.corner.web.bops.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author shenjia.caosj 2012-7-2
 *
 */
public class AlipayDetailModel {

	/**
	 * ֧�����˺�
	 */
	private String alipayId ;
	
	/**
	 * ֧������������
	 */
	private Date gmtCreate ;
	
	/**
	 * �������
	 */
	private int settleCount ;
	
	/**
	 * �û�Ӷ��
	 */
	private BigDecimal totalSettleFee ;

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public int getSettleCount() {
		return settleCount;
	}

	public void setSettleCount(int settleCount) {
		this.settleCount = settleCount;
	}

	public BigDecimal getTotalSettleFee() {
		if(totalSettleFee == null){
			return new BigDecimal("0.00") ;
		}
		return totalSettleFee;
	}

	public void setTotalSettleFee(BigDecimal totalSettleFee) {
		this.totalSettleFee = totalSettleFee;
	}

	public String getFormatGmtCreate(){
		if(gmtCreate == null){
			return "" ;
		}
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		return f.format(gmtCreate) ;
	}
} 
