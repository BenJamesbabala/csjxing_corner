package com.doucome.corner.biz.dcome.service.horoscope.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsTopicCondition;
import com.doucome.corner.biz.dal.horoscope.HsTopicDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsTopicDO;
import com.doucome.corner.biz.dcome.model.star.HsTopicDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsTopicService;

/**
 * 
 * @author ze2200
 *
 */
public class HsTopicServiceImpl implements HsTopicService {
	@Autowired
	private HsTopicDAO hsTopicDAO;
	
	@Override
	public Long createHsTopic(HsTopicDTO hsTopic) {
		return hsTopicDAO.insertHsTopic(hsTopic.toDO());
	}

	@Override
	public HsTopicDTO getHsTopic(Long id) {
		HsTopicDO hsTopic = hsTopicDAO.queryHsTopic(id);
		if (hsTopic == null) {
			return null;
		}
		return new HsTopicDTO(hsTopic);
	}
	
	@Override
	public List<HsTopicDTO> getHsTopicsNoPage(HsTopicCondition condition, Pagination page) {
		List<HsTopicDO> hsTopics = hsTopicDAO.queryHsTopics(condition, page.getStart() - 1, page.getSize());
		List<HsTopicDTO> temps = conver(hsTopics);
		return temps;
	}

	@Override
	public QueryResult<HsTopicDTO> getHsTopicsPage(HsTopicCondition condition, Pagination page) {
		Integer count = hsTopicDAO.countHsTopics(condition);
		if (count == 0) {
			return new QueryResult<HsTopicDTO>(new ArrayList<HsTopicDTO>(), page, 0);
		}
		List<HsTopicDO> hsTopics = hsTopicDAO.queryHsTopics(condition, page.getStart() - 1, page.getSize());
		List<HsTopicDTO> temps = conver(hsTopics);
		return new QueryResult<HsTopicDTO>(temps, page, count);
	}

	@Override
	public Integer updateHsTopic(HsTopicDTO hsTopic) {
		return hsTopicDAO.updateHsTopic(hsTopic.toDO());
	}
	
	@Override
	public Integer deleteHsTopic(Long topicId) {
		return hsTopicDAO.deleteHsTopic(topicId);
	}
	
	private List<HsTopicDTO> conver(List<HsTopicDO> hsTopics) {
		List<HsTopicDTO> temps = new ArrayList<HsTopicDTO>();
		if (CollectionUtils.isEmpty(hsTopics)) {
			return temps;
		}
		for (HsTopicDO hsTopic: hsTopics) {
			temps.add(new HsTopicDTO(hsTopic));
		}
		return temps;
	}

}
