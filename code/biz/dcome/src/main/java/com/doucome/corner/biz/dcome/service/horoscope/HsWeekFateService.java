package com.doucome.corner.biz.dcome.service.horoscope;

import java.util.Date;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsWeekFateCondition;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;

/**
 * �����Ʒ���
 * 
 * @author ze2200
 */
public interface HsWeekFateService {
	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    long createHsWeekFate(HsWeekFateDTO hsFate);
    /**
     * ��ȡ����������.
     * @param id
     * @return
     */
    HsWeekFateDTO getHsWeekFate(Long id);
    
    /**
     * ��ȡ����������.
     * @return
     */
    HsWeekFateDTO getNowWeekHsFate(HoroscopeEnum hsEnum, Date date);
    /**
     * 
     * @param condition
     * @param page
     * @return
     */
    QueryResult<HsWeekFateDTO> getHsFatePage(HsWeekFateCondition condition, Pagination page);
    /**
     * 
     * @param hsfate
     * @return
     */
    Integer updateHsWeekFate(HsWeekFateDTO hsfate);
}
