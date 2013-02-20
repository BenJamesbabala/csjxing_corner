package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsDailyFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 月运势列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsNewDailyFateAction extends BopsBasicAction implements ModelDriven<HsDailyFateDTO> {

	private HsDailyFateDTO hsFate =  new HsDailyFateDTO();
	
	@Autowired
	private HsDailyFateService hsDailyFateService;
	
	@Override
	public String execute() {
		HsDailyFateDTO tempHsFate = null;
		HoroscopeEnum hsEnum = HoroscopeEnum.toEnum(hsFate.getHsId());
		if (hsEnum != HoroscopeEnum.UNKNOW) {
			tempHsFate = hsDailyFateService.getNowHsFate(hsEnum, hsFate.getGmtDay());
		}
		if (tempHsFate != null) {
			tempHsFate.getId();
			hsFate.setId(tempHsFate.getId());
			hsDailyFateService.updateHsDailyFate(hsFate);
		} else {
			hsFate.setId(null);
			hsDailyFateService.createHsDailyFate(hsFate);
		}
		return SUCCESS ;
	}
	
	@Override
	public HsDailyFateDTO getModel() {
		return hsFate ;
	}
	
	public Integer getNextHsId() {
		int nextHsId = hsFate.getHsId() + 1;
		return nextHsId > 12? 1: nextHsId;
	}
}
