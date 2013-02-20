package com.doucome.corner.web.horoscope.action.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.hs.HoroscopeBO;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.horoscope.action.HsBasicAction;

/**
 * 星座运势异步接口类.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsFateAjaxAction extends HsBasicAction {
	
	private JsonModel<Object> json = new JsonModel<Object>();
	@Autowired
	private HoroscopeBO hsHoroscopeFateBO;
	
	private Integer hsId;
	
	/**
	 * 查询明日运势 
	 * @return
	 */
	public String queryTomorrowHsFate() {
		Long userId = getUserId();
		HoroscopeEnum hsEnum = HoroscopeEnum.toEnum(hsId);
		if (hsEnum == HoroscopeEnum.UNKNOW) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.hsId");
			return SUCCESS;
		}
		HsDailyFateDTO hsFate = hsHoroscopeFateBO.getTomorrowHsFate(userId, hsEnum);
		json.setSuccess(JsonModel.CODE_SUCCESS, hsFate);
		return SUCCESS;
	}
	
	/**
	 * 查询周运势 
	 * @return
	 */
	public String queryWeekHsFate() {
		Long userId = getUserId();
		HoroscopeEnum hsEnum = HoroscopeEnum.toEnum(hsId);
		if (hsEnum == HoroscopeEnum.UNKNOW) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.hsId");
			return SUCCESS;
		}
		HsWeekFateDTO hsFate = hsHoroscopeFateBO.getWeekHsFate(userId, hsEnum);
		json.setSuccess(JsonModel.CODE_SUCCESS, hsFate);
		return SUCCESS;
	}
	
	/**
	 * 查询月运势 
	 * @return
	 */
	public String queryMonthHsFate() {
		Long userId = getUserId();
		HoroscopeEnum hsEnum = HoroscopeEnum.toEnum(hsId);
		if (hsEnum == HoroscopeEnum.UNKNOW) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.hsId");
			return SUCCESS;
		}
		HsMonthFateDTO hsFate = hsHoroscopeFateBO.getMonthHsFate(userId, hsEnum);
		json.setSuccess(JsonModel.CODE_SUCCESS, hsFate);
		return SUCCESS;
	}
	
	public JsonModel<Object> getJson() {
		return this.json;
	}
}
