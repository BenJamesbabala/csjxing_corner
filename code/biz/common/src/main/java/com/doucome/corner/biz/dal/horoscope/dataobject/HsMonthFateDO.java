package com.doucome.corner.biz.dal.horoscope.dataobject;

import java.util.Date;

/**
 * ����������.
 * @author ze2200
 *
 */
public class HsMonthFateDO {

	private Long id;
	/**
	 * ����id.
	 */
	private Integer hsId;
	/**
	 * ����.
	 */
	private String summary;
	/**
	 * ����ָ��
	 */
	private Integer loveIndex;
	/**
	 * �������
	 */
	private String loveDesc;
	/**
	 * ����ָ��
	 */
	private Integer workIndex;
	/**
	 * 
	 */
	private String workDesc;
	/**
	 * ����ָ��.
	 */
	private Integer wealthIndex;
	/**
	 * 
	 */
	private String wealthDesc;
	/**
	 * ����ָ��.
	 */
	private Integer healthIndex;
	/**
	 * 
	 */
	private String healthDesc;
	/**
	 * ת�˷�ʽ.
	 */
	private String turnToLucky;
	/**
	 * 
	 */
	private Date gmtMonth;
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
	public Integer getLoveIndex() {
		return loveIndex;
	}
	public void setLoveIndex(Integer loveIndex) {
		this.loveIndex = loveIndex;
	}
	public String getLoveDesc() {
		return loveDesc;
	}
	public void setLoveDesc(String loveDesc) {
		this.loveDesc = loveDesc;
	}
	public Integer getWorkIndex() {
		return workIndex;
	}
	public void setWorkIndex(Integer workIndex) {
		this.workIndex = workIndex;
	}
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	public Integer getWealthIndex() {
		return wealthIndex;
	}
	public void setWealthIndex(Integer wealthIndex) {
		this.wealthIndex = wealthIndex;
	}
	public String getWealthDesc() {
		return wealthDesc;
	}
	public void setWealthDesc(String wealthDesc) {
		this.wealthDesc = wealthDesc;
	}
	public Integer getHealthIndex() {
		return healthIndex;
	}
	public void setHealthIndex(Integer healthIndex) {
		this.healthIndex = healthIndex;
	}
	public String getHealthDesc() {
		return healthDesc;
	}
	public void setHealthDesc(String healthDesc) {
		this.healthDesc = healthDesc;
	}
	
	public String getTurnToLucky() {
		return turnToLucky;
	}
	public void setTurnToLucky(String turnToLucky) {
		this.turnToLucky = turnToLucky;
	}
	public Date getGmtMonth() {
		return gmtMonth;
	}
	public void setGmtMonth(Date gmtMonth) {
		this.gmtMonth = gmtMonth;
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
}
