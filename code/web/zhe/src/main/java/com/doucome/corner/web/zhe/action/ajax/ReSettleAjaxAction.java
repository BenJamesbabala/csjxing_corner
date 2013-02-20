package com.doucome.corner.web.zhe.action.ajax;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.zhe.action.DdzBasicAction;

/**
 * 重新打款
 * @author langben 2012-11-3
 *
 */
@SuppressWarnings("serial")
public class ReSettleAjaxAction extends DdzBasicAction {
	
	private JsonModel<Integer> json = new JsonModel<Integer>();

	private Integer settleId ;
	
	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Override
	public String execute() throws Exception {
		
		DdzUserDO user = ddzAuthz.getUser() ;
		
		if(user == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.user.required") ;
			return SUCCESS ;
		}
		
		String alipayId = user.getAlipayId() ;
		
		if(IDUtils.isNotCorrect(settleId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.id.required") ;
			return SUCCESS ;
		}
		
		DdzTaokeReportSettleUpdateCondition condition = new DdzTaokeReportSettleUpdateCondition();
		condition.setEqualSettleStatus(SettleStatusEnums.SETTLE_CANCEL.getValue()) ;
		condition.setSettleAlipay(alipayId) ;
		List<Long> idList = new ArrayList<Long>();
		idList.add(Long.valueOf(settleId)) ;
		condition.setSettleIds(idList) ;
		try {
			int effectCount = ddzTaokeReportSettleService.updateTaokeSettleStatus(condition, SettleStatusEnums.UNSETTLE , null) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(effectCount) ;
		} catch (Exception e) {
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}

	public void setSettleId(Integer settleId) {
		this.settleId = settleId;
	}
	
}
