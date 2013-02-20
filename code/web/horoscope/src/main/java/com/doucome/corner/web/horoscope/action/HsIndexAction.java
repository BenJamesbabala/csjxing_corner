package com.doucome.corner.web.horoscope.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.hs.HoroscopeBO;
import com.doucome.corner.biz.dcome.model.star.HsTopicDTO;
import com.doucome.corner.biz.dcome.model.star.HsUserDTO;

/**
 * ÐÇ×ù±´±´Ê×Ò³action.
 * @author ze2200
 *
 */
public class HsIndexAction extends HsBasicAction {
	
	private static final long serialVersionUID = 1L;

	private List<HsTopicDTO> hsTopics;
	
	@Autowired
	private HoroscopeBO horoscopeBO;
	
	@Override
	public String execute() {
		HsUserDTO user = getUser();
		if (user != null) {
			hsTopics = horoscopeBO.getNowHsTopic();
		}
		return SUCCESS;
	}

	public List<HsTopicDTO> getHsTopics() {
		return hsTopics;
	}
}
