package com.doucome.corner.web.bops.action.dcome.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

public class UpdatePromotionRateAjaxAction extends BopsBasicAction {

	private static final Log log = LogFactory.getLog(UpdatePromotionRateAjaxAction.class) ;

	private JsonModel<Integer> json = new JsonModel<Integer>() ;
	
	private Long promItemId ;
	
	private int rateCount ;
	
	@Autowired
	private DcPromotionItemService dcPromotionItemService ;
	
	@Override
	public String execute() throws Exception {
		
		if(IDUtils.isNotCorrect(promItemId)) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promItemId.required") ;
			return SUCCESS ;
		}
		
		try {
			int effectCount = dcPromotionItemService.updateRateCountById(promItemId, rateCount) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(effectCount) ;
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}

	public void setPromItemId(Long promItemId) {
		this.promItemId = promItemId;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}
	
	
}
