package com.doucome.corner.biz.dcome.model.param;

import java.util.List;

import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dal.model.KeyValuePair;

/**
 * �ϻ�����Ϸ���
 * @author langben 2012-12-14
 *
 */
public class DcWinnerGameResultModel extends AbstractModel {

	/**
	 * 
	 */
	private String winCardName ;
	
	/**
	 * ��Ƭ��ֵ
	 */
	private Integer winCardScore ;
	
	/**
	 * �ܵ÷�
	 */
	private int winScore ;
	
	/**
	 * ��ע�Ĳ���
	 */
	List<KeyValuePair> betParamList ;
	
	/**
	 * �û�����
	 */
	private int userIntegralCount ;
	
	/**
	 * �Ƿ��н�
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
