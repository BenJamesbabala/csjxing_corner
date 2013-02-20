package com.doucome.corner.biz.dal.horoscope;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsDailyFateCondition;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsDailyFateDO;

/**
 * ��������
 * 
 * 
 */
public interface HsDailyFateDAO {
	/**
	 * �½�����������
	 * @param hsFate
	 * @return
	 */
    Long insertHsDailyFate(HsDailyFateDO hsFate);
    /**
     * ��ȡ����������
     * @param id
     * @return
     */
    HsDailyFateDO queryHsDailyFate(Long id);
    /**
     * ��ȡ����������
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
