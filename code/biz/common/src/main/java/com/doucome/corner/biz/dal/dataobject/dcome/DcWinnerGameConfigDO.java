package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 老虎机
 * @author langben 2012-12-14
 *
 */
public class DcWinnerGameConfigDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 
	 */
	private String cardName ;
	
	/**
	 * 概率，基数10000
	 */
	private Integer probability ;
	
	/**
	 * 对应分值
	 */
	private Integer score ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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
	
	
}
