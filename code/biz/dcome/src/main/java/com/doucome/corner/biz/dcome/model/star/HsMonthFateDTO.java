package com.doucome.corner.biz.dcome.model.star;

import java.util.Date;

import com.doucome.corner.biz.dal.horoscope.dataobject.HsMonthFateDO;
import com.doucome.corner.biz.dcome.enums.hs.HsFatePeriodEnum;

/**
 * HsMonthFateDTO
 * @author 
 *
 */
public class HsMonthFateDTO extends AHsFateDTO {
	
	private static final long serialVersionUID = 1L;
	
    private HsMonthFateDO hsFate ;
	
	public HsMonthFateDTO() {
		this(null);
	}
	
	public HsMonthFateDTO(HsMonthFateDO hsFate){
		if(hsFate == null){
			hsFate = new HsMonthFateDO();
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
	
	public Date getGmtMonth() {
		return hsFate.getGmtMonth();
	}
	
	public void setGmtMonth(Date gmtMonth) {
		hsFate.setGmtMonth(gmtMonth);
	}
	
	public Date getMonthStart() {
		return HsFatePeriodEnum.MONTH.getValidPeriod(getGmtMonth())[0];
	}
	
	public Date getMonthEnd() {
		return HsFatePeriodEnum.MONTH.getValidPeriod(getGmtMonth())[1];
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
	
	public HsMonthFateDO toDO() {
		return hsFate;
	}
}
