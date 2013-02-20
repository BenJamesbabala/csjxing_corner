package com.doucome.corner.biz.dal.horoscope;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsMonthFateCondition;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsMonthFateDO;

/**
 * ��������
 * 
 * @author ze2200
 */
public interface HsMonthFateDAO {
	/**
	 * �½�����������
	 * @param hsFate
	 * @return
	 */
    Long insertHsMonthFate(HsMonthFateDO hsFate);
    /**
     * ��ȡ����������
     * @param id
     * @return
     */
    HsMonthFateDO queryHsMonthFate(Long id);
    /**
     * ��ȡ����������
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
