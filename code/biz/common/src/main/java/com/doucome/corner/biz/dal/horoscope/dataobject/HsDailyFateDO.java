package com.doucome.corner.biz.dal.horoscope.dataobject;

import java.util.Date;

/**
 * 
 * @author ze2200
 *
 */
public class HsDailyFateDO {
	private Long id;
	/**
	 * 星座id.
	 */
	private Integer hsId;
	/**
	 * 今日概述.
	 */
	private String summary;
	/**
	 * 综合指数.
	 */
	private Integer compositeIndex;
	/**
	 * 爱情指数
	 */
	private Integer loveIndex;
	/**
	 * 工作指数
	 */
	private Integer workIndex;
	/**
	 * 财运指数.
	 */
	private Integer wealthIndex;
	/**
	 * 健康指数.
	 */
	private Integer healthIndex;
	/**
	 * 幸运色.
	 */
	private String luckyColor;
	/**
	 * 幸运数字
	 */
	private Integer luckyNumber;
	/**
	 * 速配星座.
	 */
	private String matchHs;
	/**
	 * 转运方式.
	 */
	private String turnToLucky;
	/**
	 * 
	 */
	private Date gmtDay;
	/**
	 * 
	 */
	private Date gmtModified;
	/**
	 * 
	 */
	private Date gmtCreate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getHsId() {
		return hsId;
	}
	public void setHsId(Integer hsId) {
		this.hsId = hsId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getCompositeIndex() {
		return compositeIndex;
	}
	public void setCompositeIndex(Integer compositeIndex) {
		this.compositeIndex = compositeIndex;
	}
	public Integer getLoveIndex() {
		return loveIndex;
	}
	public void setLoveIndex(Integer loveIndex) {
		this.loveIndex = loveIndex;
	}
	public Integer getWorkIndex() {
		return workIndex;
	}
	public void setWorkIndex(Integer workIndex) {
		this.workIndex = workIndex;
	}
	public Integer getWealthIndex() {
		return wealthIndex;
	}
	public void setWealthIndex(Integer wealthIndex) {
		this.wealthIndex = wealthIndex;
	}
	public Integer getHealthIndex() {
		return healthIndex;
	}
	public void setHealthIndex(Integer healthIndex) {
		this.healthIndex = healthIndex;
	}
	public String getLuckyColor() {
		return luckyColor;
	}
	public void setLuckyColor(String luckyColor) {
		this.luckyColor = luckyColor;
	}
	public Integer getLuckyNumber() {
		return luckyNumber;
	}
	public void setLuckyNumber(Integer luckyNumber) {
		this.luckyNumber = luckyNumber;
	}
	public String getMatchHs() {
		return matchHs;
	}
	public void setMatchHs(String matchHs) {
		this.matchHs = matchHs;
	}
	public Date getGmtDay() {
		return gmtDay;
	}
	public void setGmtDay(Date gmtDay) {
		this.gmtDay = gmtDay;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getTurnToLucky() {
		return turnToLucky;
	}
	public void setTurnToLucky(String turnToLucky) {
		this.turnToLucky = turnToLucky;
	}
}
