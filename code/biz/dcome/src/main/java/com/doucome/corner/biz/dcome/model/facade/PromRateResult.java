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
	 * 消耗积分
	 */
	private int consumeIntegralCount ;
	
	/**
	 * 增加票数（小于0为附属）
	 */
	private int growRateCount ;
	
	private int rateCount ;
	
	/**
	 * 下一个可以领取愿望值剩余时间（单位秒）
	 * -1 下一个时间点未知
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
