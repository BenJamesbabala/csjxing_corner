package com.doucome.corner.biz.dal.horoscope;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsWeekFateCondition;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsWeekFateDO;

/**
 * 星座贝贝
 * 
 * @author ib 2012-7-28 下午09:55:16
 */
public interface HsWeekFateDAO {
	/**
	 * 新建星座周运势
	 * @param hsFate
	 * @return
	 */
    Long insertHsWeekFate(HsWeekFateDO hsFate);
    /**
     * 获取星座日运势
     * @param id
     * @return
     */
    HsWeekFateDO queryHsWeekFate(Long id);
    /**
     * 获取星座日运势
     * @param gmtStart
     * @param gmtEnd
     * @return
     */
    HsWeekFateDO queryHsWeekFate(Integer hsId, Date weekStart, Date weekEnd);
    /**
     * 
     * @param condition
     * @param start
     * @param end
     * @return
     */
    List<HsWeekFateDO> queryHsWeekFates(HsWeekFateCondition condition, int start, int size);
    /**
     * 
     * @param condition
     * @return
     */
    Integer countHsWeekFates(HsWeekFateCondition condition);
    /**
     * 
     * @param hsFate
     * @return
     */
    Integer updateHsWeekFate(HsWeekFateDO hsFate);
}
