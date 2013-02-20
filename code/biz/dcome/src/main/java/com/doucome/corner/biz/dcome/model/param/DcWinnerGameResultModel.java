package com.doucome.corner.biz.dcome.model.param;

import java.util.List;

import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dal.model.KeyValuePair;

/**
 * 老虎机游戏结果
 * @author langben 2012-12-14
 *
 */
public class DcWinnerGameResultModel extends AbstractModel {

	/**
	 * 
	 */
	private String winCardName ;
	
	/**
	 * 卡片分值
	 */
	private Integer winCardScore ;
	
	/**
	 * 总得分
	 */
	private int winScore ;
	
	/**
	 * 下注的参数
	 */
	List<KeyValuePair> betParamList ;
	
	/**
	 * 用户积分
	 */
	private int userIntegralCount ;
	
	/**
	 * 是否中奖
	 */
	public boolean isWin() {
		return winScore > 0 ;
	}

	public String getWinCardName() {
		return winCardName;
	}

	public void setWinCardName(String winCardName) {
		this.winCardName = winCardName;
	}

	public Integer getWinCardScore() {
		return winCardScore;
	}

	public void setWinCardScore(Integer winCardScore) {
		this.winCardScore = winCardScore;
	}

	public int getWinScore() {
		return winScore;
	}

	public void setWinScore(int winScore) {
		this.winScore = winScore;
	}

	public List<KeyValuePair> getBetParamList() {
		return betParamList;
	}

	public void setBetParamList(List<KeyValuePair> betParamList) {
		this.betParamList = betParamList;
	}

	public int getUserIntegralCount() {
		return userIntegralCount;
	}

	public void setUserIntegralCount(int userIntegralCount) {
		this.userIntegralCount = userIntegralCount;
	}
	
}
