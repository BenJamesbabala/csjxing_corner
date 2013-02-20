package com.doucome.corner.biz.dal.horoscope;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsTopicCondition;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsTopicDO;


/**
 * ÐÇ×ù»°Ìâ
 * 
 * 
 */
public interface HsTopicDAO {
	/**
	 * 
	 * @param topicDO
	 * @return
	 */
	Long insertHsTopic(HsTopicDO topicDO);
	/**
	 * 
	 * @param topicId
	 * @return
	 */
	HsTopicDO queryHsTopic(Long topicId);
	/**
	 * 
	 * @return
	 */
	List<HsTopicDO> queryHsTopics(HsTopicCondition condition, int start, int size);
	/**
	 * 
	 * @return
	 */
	Integer countHsTopics(HsTopicCondition condition);
	/**
	 * 
	 * @param topicDO
	 * @return
	 */
	Integer updateHsTopic(HsTopicDO topicDO);
	/**
	 * 
	 * @param topicId
	 * @return
	 */
	Integer deleteHsTopic(Long topicId);
}
