package com.doucome.corner.biz.dcome.model.star;

import java.util.Date;

import com.doucome.corner.biz.dal.horoscope.dataobject.HsWeekFateDO;
import com.doucome.corner.biz.dcome.enums.hs.HsFatePeriodEnum;

/**
 * HsFateWeekDTO
 * @author 
 *
 */
public class HsWeekFateDTO extends AHsFateDTO {
	
	private static final long serialVersionUID = 1L;
	
	private HsWeekFateDO hsFate ;
	
	public HsWeekFateDTO() {
		this(null);
	}
	
	public HsWeekFateDTO(HsWeekFateDO hsFate){
		if(hsFate == null){
			hsFate = new HsWeekFateDO();
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
	
	public Integer getLoveIndex() {
		return hsFate.getLoveIndex();
	}
	
	public void setLoveIndex(Integer loveIndex) {
		hsFate.setLoveIndex(getValidIndexValue(loveIndex));
	}
	
	public String getLoveDesc() {
		return hsFate.getLoveDesc();
	}
	
	public void setLoveDesc(String loveDesc) {
		hsFate.setLoveDesc(loveDesc);
	}
	
	public Integer getWorkIndex() {
		return hsFate.getWorkIndex();
	}
	
	public void setWorkIndex(Integer workIndex) {
		hsFate.setWorkIndex(getValidIndexValue(workIndex));
	}

	public String getWorkDesc() {
		return hsFate.getWorkDesc();
	}
	
	public void setWorkDesc(String workDesc) {
		hsFate.setWorkDesc(workDesc);
	}
	
	public Integer getWealthIndex() {
		return hsFate.getWealthIndex();
	}
	
	public void setWealthIndex(Integer wealthIndex) {
		hsFate.setWealthIndex(getValidIndexValue(wealthIndex));
	}
	
	public String getWealthDesc() {
		return hsFate.getWealthDesc();
	}
	
	public void setWealthDesc(String wealthDesc) {
		hsFate.setWealthDesc(wealthDesc);
	}
	
	public Integer getHealthIndex() {
		return hsFate.getHealthIndex();
	}
	
	public void setHealthIndex(Integer healthIndex) {
		hsFate.setHealthIndex(getValidIndexValue(healthIndex));
	}
	
	public String getHealthDesc() {
		return hsFate.getHealthDesc();
	}
	
	public void setHealthDesc(String healthDesc) {
		hsFate.setHealthDesc(healthDesc);
	}
	
	public Date getGmtWeek() {
		return hsFate.getGmtWeek();
	}
	
	public void setGmtWeek(Date gmtWeek) {
		hsFate.setGmtWeek(gmtWeek);
	}
	
	public Date getWeekStart() {
		return HsFatePeriodEnum.WEEK.getValidPeriod(getGmtWeek())[0];
	}
	
	public Date getWeekEnd() {
		return HsFatePeriodEnum.WEEK.getValidPeriod(getGmtWeek())[1];
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
	
	public HsWeekFateDO toDO() {
		return hsFate;
	}
}
