package com.doucome.corner.biz.dcome.business.hs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsTopicCondition;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.enums.hs.HsFatePeriodEnum;
import com.doucome.corner.biz.dcome.model.star.AHsFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsTopicDTO;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsDailyFateService;
import com.doucome.corner.biz.dcome.service.horoscope.HsMonthFateService;
import com.doucome.corner.biz.dcome.service.horoscope.HsTopicService;
import com.doucome.corner.biz.dcome.service.horoscope.HsWeekFateService;

/**
 * 星座运势业务逻辑类.
 * @author ze2200
 * 
 */
public class HoroscopeBO {
	@Autowired
    private HsDailyFateService hsDailyFateService;
	@Autowired
	private HsWeekFateService hsWeekFateService;
	@Autowired
	private HsMonthFateService hsMonthFateService;
	@Autowired
	private HsTopicService hsTopicService;
	
	public List<HsTopicDTO> getNowHsTopic() {
		HsTopicCondition condition = new HsTopicCondition();
		condition.setGmtDay(new Date());
		List<HsTopicDTO> hsTopics = hsTopicService.getHsTopicsNoPage(condition, new Pagination(1, 10));
		if (CollectionUtils.isEmpty(hsTopics)) {
			Calendar temp = Calendar.getInstance();
			temp.add(Calendar.DAY_OF_YEAR, -1);
			condition.setGmtDay(temp.getTime());
			hsTopics = hsTopicService.getHsTopicsNoPage(condition, new Pagination(1, 10));
		}
		return hsTopics;
	}
	
	/**
	 * 查询星座运势.
	 * @param userId
	 * @param hsEnum 星座id.
	 * @param periodEnum 周期id.详见:
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
	 * 查询今日星座运势.
	 * @return
	 */
    public HsDailyFateDTO getTodayHsFate(Long userId, HoroscopeEnum hsEnum) {
    	HsDailyFateDTO hsFate = hsDailyFateService.getNowHsFate(hsEnum, new Date());
    	if (hsFate == null) {
    		
    	}
		return hsFate;
	}
	
	/**
	 * 查询明日星座运势.
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
     * 查询周星座运势.
     * @return
     */
	public HsWeekFateDTO getWeekHsFate(Long userId, HoroscopeEnum hsEnum) {
		HsWeekFateDTO hsFate = hsWeekFateService.getNowWeekHsFate(hsEnum, new Date());
        if (hsFate == null) {
    		
    	}
		return hsFate;
	}
	
	/**
	 * 查询月星座运势.
	 * @return
	 */
	public HsMonthFateDTO getMonthHsFate(Long userId, HoroscopeEnum hsEnum) {
		HsMonthFateDTO hsFate = hsMonthFateService.getNowMonthHsFate(hsEnum, new Date());
        if (hsFate == null) {
    		
    	}
		return hsFate;
	}
}