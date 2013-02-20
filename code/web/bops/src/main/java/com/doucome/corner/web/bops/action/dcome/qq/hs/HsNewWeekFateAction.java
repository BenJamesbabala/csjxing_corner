package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsWeekFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 月运势列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsNewWeekFateAction extends BopsBasicAction implements ModelDriven<HsWeekFateDTO> {

	private HsWeekFateDTO hsFate =  new HsWeekFateDTO();
	
	@Autowired
	private HsWeekFateService hsWeekFateService;
	
	@Override
	public String execute() {
		HsWeekFateDTO tempHsFate = null;
		HoroscopeEnum hsEnum = HoroscopeEnum.toEnum(hsFate.getHsId());
		if (hsEnum != HoroscopeEnum.UNKNOW) {
			tempHsFate = hsWeekFateService.getNowWeekHsFate(hsEnum, hsFate.getGmtWeek());
		}
		if (tempHsFate != null) {
			hsFate.setId(tempHsFate.getId());
			hsWeekFateService.updateHsWeekFate(hsFate);
		} else {
			hsFate.setId(null);
			hsWeekFateService.createHsWeekFate(hsFate);
		}
		return SUCCESS ;
	}
	
	@Override
	public HsWeekFateDTO getModel() {
		return hsFate ;
	}
	
	public Integer getNextHsId() {
		int nextHsId = hsFate.getHsId() + 1;
		return nextHsId > 12? 1: nextHsId;
	}
}
