package com.doucome.corner.web.bops.action.zhe;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 
 * @author langben 2012-4-22
 *
 */
@SuppressWarnings("serial")
public class TaokeReportSettleAjaxAction extends BopsBasicAction{

	private JsonModel<Integer> json = new JsonModel<Integer>();
	
	/**
	 * 批量修改的Report id
	 */
	private String ids ;
	
	/**
	 * 结算后的值
	 */
	private String settleTo ;
	
	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Override
	public String execute() throws Exception {
		
		String[] idArray = StringUtils.split(ids , ",") ;
		if(idArray == null || idArray.length <= 0){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("input ids is null .") ;
			return SUCCESS ;
		}
		
		SettleStatusEnums status = SettleStatusEnums.fromValue(settleTo) ;
		
//		if(status != SettleStatusEnums.SETTLE_FAIL && status != SettleStatusEnums.SETTLE_SUCCESS){
//			json.setCode(JsonModel.CODE_ILL_ARGS) ;
//			json.setDetail("input settleTo is not right .") ;
//			return SUCCESS ;
//		}
		List<Long> idList = new ArrayList<Long>() ;
		for(String id : idArray){
			if(StringUtils.isNumeric(id)){
				idList.add(Long.valueOf(id)) ;
			}
		}
		int count = ddzTaokeReportSettleService.updateTaokeSettleStatus(idList, status) ;
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(count) ;
		return SUCCESS ;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setSettleTo(String settleTo) {
		this.settleTo = settleTo;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}
	
	
}
