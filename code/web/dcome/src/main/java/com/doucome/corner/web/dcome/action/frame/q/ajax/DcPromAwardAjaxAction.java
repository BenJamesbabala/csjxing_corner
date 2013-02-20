package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.DateTool;
import com.doucome.corner.biz.dcome.business.DcPromotionAwardBO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 中奖列表.
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcPromAwardAjaxAction extends DComeBasicAction {
	
	private Integer dayBefore;
	
	private JsonModel<List<DcPromotionAwardDTO>> json = new JsonModel<List<DcPromotionAwardDTO>>();
	
	private String awardDate;
	
	@Autowired
	private DcPromotionAwardBO dcPromotionAwardBO;
	
	public String execute() {
		Long userId = getUserId();
		if (IDUtils.isNotCorrect(userId) || dayBefore == null || dayBefore < 0) {
			json.setFail(JsonModel.CODE_FAIL, "invalid.params");
			return SUCCESS;
		}
		Date promDate = getPromDate();
		Date firstAwardDay = getFirstAwardDate();
		if (promDate.before(firstAwardDay)) {
			json.setFail(JsonModel.CODE_FAIL, "no.prom");
			return SUCCESS;
		}
		List<DcPromotionAwardDTO> promAwards = dcPromotionAwardBO.getPromAwardsByDate(promDate);
		json.setSuccess(JsonModel.CODE_SUCCESS, promAwards);
		this.awardDate = DateTool.formatToDay(promDate);
		return SUCCESS;
	}
	
	private Date getPromDate() {
		Calendar promCal = Calendar.getInstance();
		int hour = promCal.get(Calendar.HOUR_OF_DAY);
		if (hour < 22) {
			promCal.add(Calendar.DAY_OF_YEAR, -1);
		}
		promCal.set(Calendar.HOUR_OF_DAY, 1);
		promCal.add(Calendar.DAY_OF_YEAR, -dayBefore);
		return promCal.getTime();
		
	}
	
	private Date getFirstAwardDate() {
		Calendar temp = Calendar.getInstance();
		temp.set(Calendar.YEAR, 2012);
		//month从0开始
		temp.set(Calendar.MONTH, 8);
		//DAY_OF_MONTH从1开始
		temp.set(Calendar.DAY_OF_MONTH, 11);
		temp.set(Calendar.HOUR_OF_DAY, 0);
		temp.set(Calendar.MINUTE, 0);
		return temp.getTime();
	}
	
	public void setDayBefore(Integer dayBefore) {
		this.dayBefore = dayBefore;
	}
	
	public JsonModel<List<DcPromotionAwardDTO>> getJson() {
		return json;
	}
	
	public String getAwardDate() {
		return awardDate;
	}
}
