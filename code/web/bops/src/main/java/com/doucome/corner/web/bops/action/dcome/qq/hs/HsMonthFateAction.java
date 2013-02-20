package com.doucome.corner.web.bops.action.dcome.qq.hs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsMonthFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * ÷‹‘À ∆
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsMonthFateAction extends BopsBasicAction {

    private HsMonthFateDTO hsFate = new HsMonthFateDTO();
	
	@Autowired
	private HsMonthFateService hsMonthFateService;
	
	@Override
	public String execute() {
		if (IDUtils.isCorrect(hsFate.getId())) {
			hsFate = hsMonthFateService.getHsMonthFate(hsFate.getId());
		} else {
			hsFate.setGmtMonth(new Date());
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
	
	public HsMonthFateDTO getHsFate() {
		return this.hsFate;
	}
}
