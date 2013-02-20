package com.doucome.corner.biz.dal.horoscope;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsMonthFateCondition;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsMonthFateDO;

/**
 * 星座贝贝
 * 
 * @author ze2200
 */
public interface HsMonthFateDAO {
	/**
	 * 新建星座周运势
	 * @param hsFate
	 * @return
	 */
    Long insertHsMonthFate(HsMonthFateDO hsFate);
    /**
     * 获取星座日运势
     * @param id
     * @return
     */
    HsMonthFateDO queryHsMonthFate(Long id);
    /**
     * 获取星座日运势
     * @param gmtStart
     * @param gmtEnd
     * @return
     */
    HsMonthFateDO queryHsMonthFate(Integer hsId, Date monthStart, Date monthEnd);
    /**
     * 
     * @param condition
     * @param start
     * @param end
     * @return
     */
    List<HsMonthFateDO> queryHsMonthFates(HsMonthFateCondition condition, int start, int size);
    /**
     * 
     * @param condition
     * @return
     */
    Integer countHsMonthFates(HsMonthFateCondition condition);
    /**
     * 
     * @param hsFate
     * @return
     */
    Integer updateHsMonthFate(HsMonthFateDO hsFate);
}
