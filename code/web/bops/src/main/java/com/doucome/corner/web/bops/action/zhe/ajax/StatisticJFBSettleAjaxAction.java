package com.doucome.corner.web.bops.action.zhe.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.SettleConstant;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleStatisticsDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * 统计有多少积分宝记录需要结算
 * @author langben 2012-12-22
 *
 */
public class StatisticJFBSettleAjaxAction extends BopsBasicAction {

	private JsonModel<DdzTaokeReportSettleStatisticsDO> json = new JsonModel<DdzTaokeReportSettleStatisticsDO>() ;
	
	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Override
	public String execute() throws Exception {
		
		DdzTaokeReportSettleSearchCondition condition = new DdzTaokeReportSettleSearchCondition() ;
		condition.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
		condition.setOutcodeTypeList(SettleConstant.JFB_SETTLE_OUTCODE_TYPES) ;

		DdzTaokeReportSettleStatisticsDO statistic = ddzTaokeReportSettleService.statisticsWithPagination(condition) ;
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(statistic) ;
		
		return SUCCESS ;
	}

	public JsonModel<DdzTaokeReportSettleStatisticsDO> getJson() {
		return json;
	}
	
}
