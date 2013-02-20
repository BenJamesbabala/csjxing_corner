package com.doucome.corner.biz.core.taobao.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.InitializingBean;


/**
 * 结算的配置选项
 * @author langben 2013-1-6
 *
 */
public class SettleConfig implements InitializingBean {
	
	/**
	 * 淘宝技术服务费
	 */
	public static final BigDecimal DEFAULT_TAOBAO_TAX_RATE = new BigDecimal(10) ;
	
	/**
	 * 集分宝技术服务费
	 */
	public static final BigDecimal DEFAULT_JFB_TAX_RATE = new BigDecimal(10) ;

	/**
	 * 延迟结算金额
	 */
	private BigDecimal delaySettleAmount = new BigDecimal(50);
	
	/**
	 * 延迟结算天数
	 */
	private int delaySettleDays = 15 ;
	
	/**
	 * 吃掉的折扣比率
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
