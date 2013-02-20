package com.doucome.corner.biz.dcome.service.horoscope;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsTopicCondition;
import com.doucome.corner.biz.dcome.model.star.HsTopicDTO;

/**
 * 月运势服务
 * @author ze2200
 *
 */
public interface HsTopicService {

	/**
	 * 
	 * @param dcUserDO
	 * @return
	 */
    Long createHsTopic(HsTopicDTO hsTopic);
    /**
     * 
     * @param id
     * @return
     */
    HsTopicDTO getHsTopic(Long id);
    
    /**
     * 
     * @param condition
     * @param page
     * @return
     */
    List<HsTopicDTO> getHsTopicsNoPage(HsTopicCondition condition, Pagination page);
    /**
     * 
     * @param condition
     * @param page
     * @return
     */
    QueryResult<HsTopicDTO> getHsTopicsPage(HsTopicCondition condition, Pagination page);
    /**
     * 
     * @param hsTopic
     * @return
     */
    Integer updateHsTopic(HsTopicDTO hsTopic);
    /**
     * 
     * @param topicId
     * @return
     */
    Integer deleteHsTopic(Long topicId);
}
