package com.doucome.corner.biz.dcome.business.hs;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.enums.hs.HsFatePeriodEnum;
import com.doucome.corner.biz.dcome.model.star.AHsFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsDailyFateService;
import com.doucome.corner.biz.dcome.service.horoscope.HsMonthFateService;
import com.doucome.corner.biz.dcome.service.horoscope.HsWeekFateService;

/**
 * ��������ҵ���߼���.
 * @author ze2200
 * 
 */
public class HsFateBO {
	@Autowired
    private HsDailyFateService hsDailyFateService;
	@Autowired
	private HsWeekFateService hsWeekFateService;
	@Autowired
	private HsMonthFateService hsMonthFateService;
	
	/**
	 * ��ѯ��������.
	 * @param userId
	 * @param hsEnum ����id.
	 * @param periodEnum ����id.���:
	 * @return
	 */
	public AHsFateDTO getHsFate(Long userId, HoroscopeEnum hsEnum, HsFatePeriodEnum periodEnum) {
		if (periodEnum == HsFatePeriodEnum.TODAY) {
			return getTodayHsFate(userId, hsEnum);
		} else if (periodEnum == HsFatePeriodEnum.TOMORROW) {
			return getTomorrowHsFate(userId, hsEnum);
		} else if (periodEnum == HsFatePeriodEnum.WEEK) {
			return getWeekHsFate(userId, hsEnum);
		} else if (periodEnum == HsFatePeriodEnum.MONTH) {
			return getMonthHsFate(userId, hsEnum);
		} else {
			throw new IllegalArgumentException("unknow periodEnum");
		}
	}
	
	/**
	 * ��ѯ������������.
	 * @return
	 */
    public HsDailyFateDTO getTodayHsFate(Long userId, HoroscopeEnum hsEnum) {
    	HsDailyFateDTO hsFate = hsDailyFateService.getNowHsFate(hsEnum, new Date());
    	if (hsFate == null) {
    		
    	}
		return hsFate;
	}
	
	/**
	 * ��ѯ������������.
	 * @return
	 */
    public HsDailyFateDTO getTomorrowHsFate(Long userId, HoroscopeEnum hsEnum) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		HsDailyFateDTO hsFate = hsDailyFateService.getNowHsFate(hsEnum, calendar.getTime());
    	if (hsFate == null) {
    		
    	}
		return hsFate;
	}
	
    /**
     * ��ѯ����������.
     * @return
     */
	public HsWeekFateDTO getWeekHsFate(Long userId, HoroscopeEnum hsEnum) {
		HsWeekFateDTO hsFate = hsWeekFateService.getNowWeekHsFate(hsEnum, new Date());
        if (hsFate == null) {
    		
    	}
		return hsFate;
	}
	
	/**
	 * ��ѯ����������.
	 * @return
	 */
	public HsMonthFateDTO getMonthHsFate(Long userId, HoroscopeEnum hsEnum) {
		HsMonthFateDTO hsFate = hsMonthFateService.getNowMonthHsFate(hsEnum, new Date());
        if (hsFate == null) {
    		
    	}
		return hsFate;
	}
}