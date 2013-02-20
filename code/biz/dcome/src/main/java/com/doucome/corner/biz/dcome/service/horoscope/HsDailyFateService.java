package com.doucome.corner.biz.dcome.service.horoscope;

import java.util.Date;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsDailyFateCondition;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;

/**
 * �����Ʒ���
 * @author ze2200
 *
 */
public interface HsDailyFateService {

	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    Long createHsDailyFate(HsDailyFateDTO hsFate);
    /**
     * 
     * @param userId
     * @return
     */
    HsDailyFateDTO getHsDailyFate(Long id);
    
    /**
     * ��ȡ�û���Ϣ
     * @param userIds
     * @return
     */
    HsDailyFateDTO getNowHsFate(HoroscopeEnum hsEnum, Date date);
    /**
     * 
     * @param condition
     * @param page
     * @return
     */
    QueryResult<HsDailyFateDTO> getHsFatePage(HsDailyFateCondition condition, Pagination page);
    /**
     * 
     * @param hsFate
     * @return
     */
    Integer updateHsDailyFate(HsDailyFateDTO hsFate);
}
