package com.doucome.corner.web.bops.action.dcome.qq.hs;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsMonthFateService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 月运势列表
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsNewMonthFateAction extends BopsBasicAction implements ModelDriven<HsMonthFateDTO> {

	private HsMonthFateDTO hsFate =  new HsMonthFateDTO() ;
	
	@Autowired
	private HsMonthFateService hsMonthFateService;
	
	@Override
	public String execute() {
		HsMonthFateDTO tempHsFate = null;
		
		HoroscopeEnum hsEnum = HoroscopeEnum.toEnum(hsFate.getHsId());
		if (hsEnum != HoroscopeEnum.UNKNOW) {
			tempHsFate = hsMonthFateService.getNowMonthHsFate(hsEnum, hsFate.getGmtMonth());
		}
		if (tempHsFate != null) {
			hsFate.setId(tempHsFate.getId());
			hsMonthFateService.updateHsMonthFate(hsFate);
		} else {
			hsFate.setId(null);
			hsMonthFateService.createHsMonthFate(hsFate);
		}
		return SUCCESS ;
	}
	
	@Override
	public HsMonthFateDTO getModel() {
		return hsFate ;
	}
	
	public Integer getNextHsId() {
		int nextHsId = hsFate.getHsId() + 1;
		return nextHsId > 12? 1: nextHsId;
	}
}
