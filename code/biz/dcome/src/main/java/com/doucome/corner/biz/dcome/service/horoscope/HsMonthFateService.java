package com.doucome.corner.biz.dcome.service.horoscope;

import java.util.Date;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsMonthFateCondition;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;

/**
 * 月运势服务
 * @author ze2200
 *
 */
public interface HsMonthFateService {

	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    Long createHsMonthFate(HsMonthFateDTO hsFate);
    /**
     * 
     * @param userId
     * @return
     */
    HsMonthFateDTO getHsMonthFate(Long id);
    
    /**
     * 获取用户信息
     * @param userIds
     * @return
     */
    HsMonthFateDTO getNowMonthHsFate(HoroscopeEnum hsEnum, Date date);
    /**
     * 
     * @param condition
     * @param page
     * @return
     */
    QueryResult<HsMonthFateDTO> getHsFatePage(HsMonthFateCondition condition, Pagination page);
    /**
     * 
     * @param hsFate
     * @return
     */
    Integer updateHsMonthFate(HsMonthFateDTO hsFate);
}
