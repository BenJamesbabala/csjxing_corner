package com.doucome.corner.web.bops.action.dcome.qq.hs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsWeekFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * ‘¬‘À ∆
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsWeekFateAction extends BopsBasicAction {

    private HsWeekFateDTO hsFate = new HsWeekFateDTO();
	
	@Autowired
	private HsWeekFateService hsWeekFateService;
	
	@Override
	public String execute() {
		if (IDUtils.isCorrect(hsFate.getId())) {
			hsFate = hsWeekFateService.getHsWeekFate(hsFate.getId());
		} else {
			hsFate.setGmtWeek(new Date());
		}
		return SUCCESS ;
	}
	
	public Long getId() {
		return hsFate.getId();
	}
	
	public void setId(Long id) {
		hsFate.setId(id);
	}
	public void setHsId(Integer hsId) {
		hsFate.setHsId(hsId);
	}
	
	public HsWeekFateDTO getHsFate() {
		return this.hsFate;
	}
}
