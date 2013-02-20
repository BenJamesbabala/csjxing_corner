package com.doucome.corner.web.bops.action.dcome.qq.hs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsDailyFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * »’‘À ∆
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsDailyFateAction extends BopsBasicAction {

	private HsDailyFateDTO hsFate = new HsDailyFateDTO();
	@Autowired
	private HsDailyFateService hsDailyFateService;
	
	@Override
	public String execute() {
		if (IDUtils.isCorrect(hsFate.getId())) {
			hsFate = hsDailyFateService.getHsDailyFate(hsFate.getId());
		} else {
			hsFate.setGmtDay(new Date());
		}
		return SUCCESS ;
	}
	
	public void setId(Long id) {
		hsFate.setId(id);
	}
	
	public void setHsId(Integer hsId) {
		hsFate.setHsId(hsId);
	}
	
	public HsDailyFateDTO getHsFate() {
		return this.hsFate;
	}
}
