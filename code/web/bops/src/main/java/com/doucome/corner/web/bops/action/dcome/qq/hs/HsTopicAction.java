package com.doucome.corner.web.bops.action.dcome.qq.hs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.model.star.HsTopicDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsTopicService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * –«ª∞Ã‚
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsTopicAction extends BopsBasicAction {

	private HsTopicDTO hsTopic = new HsTopicDTO();
	@Autowired
	private HsTopicService hsTopicService;
	
	@Override
	public String execute() {
		if (IDUtils.isCorrect(hsTopic.getId())) {
			hsTopic = hsTopicService.getHsTopic(hsTopic.getId());
		} else {
			hsTopic.setGmtDay(new Date());
		}
		return SUCCESS ;
	}
	
	public void setId(Long id) {
		hsTopic.setId(id);
	}
	
	public HsTopicDTO getHsTopic() {
		return this.hsTopic;
	}
}
