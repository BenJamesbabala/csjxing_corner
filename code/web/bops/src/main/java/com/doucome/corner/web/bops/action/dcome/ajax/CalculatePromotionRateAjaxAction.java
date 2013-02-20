package com.doucome.corner.web.bops.action.dcome.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcPromotionAwardBO;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * »î¶¯¿ª½±
 * @author langben 2012-12-4
 *
 */
@SuppressWarnings("serial")
public class CalculatePromotionRateAjaxAction extends BopsBasicAction {
	
	private static final Log log = LogFactory.getLog(CalculatePromotionRateAjaxAction.class) ;

	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
	private DcPromotionAwardBO dcPromotionAwardBO ;
	
	@Override
	public String execute() throws Exception {
		try {
			dcPromotionAwardBO.calculateRate() ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}
	
	
}
