package com.doucome.corner.biz.core.taobao.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.InitializingBean;


/**
 * ���������ѡ��
 * @author langben 2013-1-6
 *
 */
public class SettleConfig implements InitializingBean {
	
	/**
	 * �Ա����������
	 */
	public static final BigDecimal DEFAULT_TAOBAO_TAX_RATE = new BigDecimal(10) ;
	
	/**
	 * ���ֱ����������
	 */
	public static final BigDecimal DEFAULT_JFB_TAX_RATE = new BigDecimal(10) ;

	/**
	 * �ӳٽ�����
	 */
	private BigDecimal delaySettleAmount = new BigDecimal(50);
	
	/**
	 * �ӳٽ�������
	 */
	private int delaySettleDays = 15 ;
	
	/**
	 * �Ե����ۿ۱���
	 */
	private BigDecimal eatCommissionRate = new BigDecimal(12);
	
	private BigDecimal taobaoTaxRate = DEFAULT_TAOBAO_TAX_RATE ;
	
	private BigDecimal jfbTaxRate = DEFAULT_JFB_TAX_RATE ;
	
	/**
	 * 
	 * @return
	 */

	public BigDecimal getDelaySettleAmount() {
		return delaySettleAmount;
	}

	public void setDelaySettleAmount(BigDecimal delaySettleAmount) {
		this.delaySettleAmount = delaySettleAmount;
	}

	public int getDelaySettleDays() {
		return delaySettleDays;
	}

	public void setDelaySettleDays(int delaySettleDays) {
		this.delaySettleDays = delaySettleDays;
	}

	public BigDecimal getEatCommissionRate() {
		return eatCommissionRate;
	}

	public void setEatCommissionRate(BigDecimal eatCommissionRate) {
		this.eatCommissionRate = eatCommissionRate;
	}

	public BigDecimal getTaobaoTaxRate() {
		return taobaoTaxRate;
	}

	public void setTaobaoTaxRate(BigDecimal taobaoTaxRate) {
		this.taobaoTaxRate = taobaoTaxRate;
	}

	public BigDecimal getJfbTaxRate() {
		return jfbTaxRate;
	}

	public void setJfbTaxRate(BigDecimal jfbTaxRate) {
		this.jfbTaxRate = jfbTaxRate;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	
	}
	
}
