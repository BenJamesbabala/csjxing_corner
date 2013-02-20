package com.doucome.corner.biz.dcome.model.facade;

/**
 * 
 * @author langben 2012-9-2
 *
 */
public class PromRateResult {
	
	public PromRateResult(){
		
	}
	
	public PromRateResult(int consumeIntegralCount, int growRateCount) {
		this();
		this.consumeIntegralCount = consumeIntegralCount;
		this.growRateCount = growRateCount;
	}

	/**
	 * ���Ļ���
	 */
	private int consumeIntegralCount ;
	
	/**
	 * ����Ʊ����С��0Ϊ������
	 */
	private int growRateCount ;
	
	private int rateCount ;
	
	/**
	 * ��һ��������ȡԸ��ֵʣ��ʱ�䣨��λ�룩
	 * -1 ��һ��ʱ���δ֪
	 */
	private int nextDrawRemainSeconds = -1;
	
	private long sysTimeMillis ;

	public int getConsumeIntegralCount() {
		return consumeIntegralCount;
	}

	public void setConsumeIntegralCount(int consumeIntegralCount) {
		this.consumeIntegralCount = consumeIntegralCount;
	}

	public int getGrowRateCount() {
		return growRateCount;
	}

	public void setGrowRateCount(int growRateCount) {
		this.growRateCount = growRateCount;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public int getNextDrawRemainSeconds() {
		return nextDrawRemainSeconds;
	}

	public void setNextDrawRemainSeconds(int nextDrawRemainSeconds) {
		this.nextDrawRemainSeconds = nextDrawRemainSeconds;
	}

	public long getSysTimeMillis() {
		return sysTimeMillis;
	}

	public void setSysTimeMillis(long sysTimeMillis) {
		this.sysTimeMillis = sysTimeMillis;
	}
	
}
