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
	 * 支付宝账号
	 */
	private String alipayId ;
	
	/**
	 * 支付宝创建日期
	 */
	private Date gmtCreate ;
	
	/**
	 * 结算笔数
	 */
	private int settleCount ;
	
	/**
	 * 用户佣金
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
