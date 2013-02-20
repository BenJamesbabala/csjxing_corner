package com.doucome.corner.web.zhe.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils.SettleModel;

/**
 * 
 * @author langben 2012-11-15
 *
 */
public class ManualSettleResultModel {

	/**
	 * �˿�άȨ�ֽ���
	 */
	private BigDecimal refundCashAmount ;
	
	/**
	 * �����ֽ���
	 */
	private BigDecimal settleCashAmount ;
	
	/**
	 * άȨ���ֱ�
	 */
	private int refundJfbAmount ;
	
	/**
	 * ���㼯�ֱ�
	 */
	private int settleJfbAmount ;
	
	/**
	 * �������
	 */
	private int settleSize ;
	
	private SettleModel settleModel ;
	
	public boolean getCanSettle(){
		if(settleModel == null){
			return false ;
		}
		return settleModel.isCanSettle() ;
	}
	
	public boolean getHasRefund(){
		return refundJfbAmount > 0 || 
			DecimalUtils.isGreaterThan0(refundCashAmount) ;
	}

	/**
	 * --------------------------------------------------------------
	 */


	public BigDecimal getRefundCashAmount() {
		return refundCashAmount;
	}

	public void setRefundCashAmount(BigDecimal refundCashAmount) {
		this.refundCashAmount = refundCashAmount;
	}

	public BigDecimal getSettleCashAmount() {
		return settleCashAmount;
	}

	public void setSettleCashAmount(BigDecimal settleCashAmount) {
		this.settleCashAmount = settleCashAmount;
	}

	public int getRefundJfbAmount() {
		return refundJfbAmount;
	}

	public void setRefundJfbAmount(int refundJfbAmount) {
		this.refundJfbAmount = refundJfbAmount;
	}

	public int getSettleJfbAmount() {
		return settleJfbAmount;
	}

	public void setSettleJfbAmount(int settleJfbAmount) {
		this.settleJfbAmount = settleJfbAmount;
	}

	public int getSettleSize() {
		return settleSize;
	}

	public void setSettleSize(int settleSize) {
		this.settleSize = settleSize;
	}

	public SettleModel getSettleModel() {
		return settleModel;
	}

	public void setSettleModel(SettleModel settleModel) {
		this.settleModel = settleModel;
	}
	
	
}
