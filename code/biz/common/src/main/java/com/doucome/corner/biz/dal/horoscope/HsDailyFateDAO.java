package com.doucome.corner.biz.dal.horoscope;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsDailyFateCondition;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsDailyFateDO;

/**
 * 星座贝贝
 * 
 * 
 */
public interface HsDailyFateDAO {
	/**
	 * 新建星座日运势
	 * @param hsFate
	 * @return
	 */
    Long insertHsDailyFate(HsDailyFateDO hsFate);
    /**
     * 获取星座日运势
     * @param id
     * @return
     */
    HsDailyFateDO queryHsDailyFate(Long id);
    /**
     * 获取星座日运势
     * @param gmtStart
     * @param gmtEnd
     * @return
     */
    HsDailyFateDO queryHsDailyFate(Integer hsId, Date dayStart, Date dayEnd);
    /**
     * 
     * @param condition
     * @param start
     * @param end
     * @return
     */
    List<HsDailyFateDO> queryHsDailyFates(HsDailyFateCondition condition, int start, int size);
    /**
     * 
     * @param condition
     * @return
     */
    Integer countHsDailyFates(HsDailyFateCondition condition);
    /**
     * 
     * @param hsFate
     * @return
     */
    Integer updateHsDailyFate(HsDailyFateDO hsFate);
}
