package com.doucome.corner.biz.dcome.model.star;

import java.util.Date;

import com.doucome.corner.biz.dal.horoscope.dataobject.HsDailyFateDO;

/**
 * HsDailyFateDTO
 * @author 
 *
 */
public class HsDailyFateDTO extends AHsFateDTO {
	
	private static final long serialVersionUID = 1L;
	
	private HsDailyFateDO hsFate ;
	
	public HsDailyFateDTO() {
		this(null);
	}
	
	public HsDailyFateDTO(HsDailyFateDO hsFate){
		if(hsFate == null){
			hsFate = new HsDailyFateDO();
		}
		this.hsFate = hsFate ;
	}
	
	public Long getId() {
		return hsFate.getId();
	}
	
	public void setId(Long id) {
		hsFate.setId(id);
	}
	
	public Integer getHsId() {
		return hsFate.getHsId();
	}
	
	public void setHsId(Integer hsId) {
		hsFate.setHsId(hsId);
	}
	
	public String getSummary() {
		return hsFate.getSummary();
	}
	
	public void setSummary(String summary) {
		hsFate.setSummary(summary);
	}
	
	public Integer getCompositeIndex() {
		return hsFate.getCompositeIndex();
	}
	
	public void setCompositeIndex(Integer compositeIndex) {
		hsFate.setCompositeIndex(getValidIndexValue(compositeIndex));
	}
	
	public Integer getLoveIndex() {
		return hsFate.getLoveIndex();
	}
	
	public void setLoveIndex(Integer loveIndex) {
		hsFate.setLoveIndex(getValidIndexValue(loveIndex));
	}
	
	public Integer getWorkIndex() {
		return hsFate.getWorkIndex();
	}
	
	public void setWorkIndex(Integer workIndex) {
		hsFate.setWorkIndex(getValidIndexValue(workIndex));
	}
	
	public Integer getWealthIndex() {
		return hsFate.getWealthIndex();
	}
	
	public void setWealthIndex(Integer wealthIndex) {
		hsFate.setWealthIndex(getValidIndexValue(wealthIndex));
	}
	
	public Integer getHealthIndex() {
		return hsFate.getHealthIndex();
	}
	
	public void setHealthIndex(Integer healthIndex) {
		hsFate.setHealthIndex(getValidIndexValue(healthIndex));
	}
	
	public String getLuckyColor() {
		return hsFate.getLuckyColor();
	}
	
	public void setLuckyColor(String luckyColor) {
		hsFate.setLuckyColor(luckyColor);
	}
	
	public Integer getLuckyNumber() {
		return hsFate.getLuckyNumber();
	}
	
	public void setLuckyNumber(Integer luckyNumber) {
		hsFate.setLuckyNumber(luckyNumber);
	}
	
	public String getMatchHs() {
		return hsFate.getMatchHs();
	}
	
	public void setMatchHs(String matchHs) {
		hsFate.setMatchHs(matchHs);
	}
	
	public Date getGmtDay() {
		return hsFate.getGmtDay();
	}
	
	public void setGmtDay(Date gmtDay) {
		hsFate.setGmtDay(gmtDay);
	}
	
	public Date getGmtModified() {
		return hsFate.getGmtModified();
	}
	
	public Date getGmtCreate() {
		return hsFate.getGmtCreate();
	}
	
	public String getTurnToLucky() {
		return hsFate.getTurnToLucky();
	}
	
	public void setTurnToLucky(String turnToLucky) {
		hsFate.setTurnToLucky(turnToLucky);
	}
	
	public HsDailyFateDO toDO() {
		return hsFate;
	}
}
