package com.doucome.corner.web.horoscope.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.hs.HoroscopeBO;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.enums.hs.HsFatePeriodEnum;
import com.doucome.corner.biz.dcome.model.star.AHsFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsUserDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsUserService;

/**
 * ÐÇ×ùÄÚÈÝaction.
 * @author ze2200
 *
 */
public class HsHoroscopeAction extends HsBasicAction {
	
	private static final long serialVersionUID = 1L;

	private Integer hsId;
	
	private Integer periodId;
	
	private HoroscopeEnum horoscope;
	
	private HsFatePeriodEnum hsFatePeriod;
	
	private AHsFateDTO hsFate;
	@Autowired
	private HoroscopeBO horoscopeBO;
	@Autowired
	private HsUserService hsUserService;
	
	@Override
	public String execute() {
		HsUserDTO user = getUser();
		Long userId = null;
		if (user != null) {
		    hsId = hsId == null ? user.getHsId(): hsId;
		    userId = user.getUserId();
		}
		horoscope = HoroscopeEnum.toEnum(hsId);
		horoscope = horoscope == HoroscopeEnum.UNKNOW ? HoroscopeEnum.ARIES: horoscope;
		hsId = horoscope.getId();
		periodId = periodId == null? HsFatePeriodEnum.TODAY.getId(): periodId;
		hsFatePeriod = HsFatePeriodEnum.toEnum(periodId);
		hsFatePeriod = hsFatePeriod == HsFatePeriodEnum.UNKNOW ? HsFatePeriodEnum.TODAY : hsFatePeriod;
		periodId = hsFatePeriod.getId();
		
		hsFate = horoscopeBO.getHsFate(userId, horoscope, hsFatePeriod);
		if (user != null && !hsId.equals(user.getHsId())) {
		    hsUserService.updateHsId(user.getUserId(), hsId);
		}
		return SUCCESS;
	}

	public Integer getHsId() {
		return hsId;
	}
	
	public void setHsId(Integer hsId) {
		this.hsId = hsId;
	}
	
	public AHsFateDTO getHsFate() {
		return hsFate;
	}
	
	public HoroscopeEnum getHoroscope() {
		return horoscope;
	}

	public Integer getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}
	
	public HsFatePeriodEnum getHsFatePeriod() {
		return hsFatePeriod;
	}

	public Date getEffectDateStart() {
		return hsFatePeriod.getValidPeriod(new Date())[0];
	}
	
	public Date getEffectDateEnd() {
		return hsFatePeriod.getValidPeriod(new Date())[1];
	}
}
