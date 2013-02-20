package com.doucome.corner.web.zhe.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.zhe.action.DdzBasicAction;

/**
 * 
 * @author langben 2012-9-26
 *
 */
@SuppressWarnings("serial")
public class AccountOperateAction extends DdzBasicAction {
	
	private static final Log log = LogFactory.getLog(AccountOperateAction.class) ;
	
	private JsonModel<Integer> json = new JsonModel<Integer>() ;
	
	@Autowired
	private DdzAccountService ddzAccountService ;
	
	public String decrNotify(){
		
		String alipayId = ddzAuthz.getAlipayId() ;
		
		try {
			int effectCount = ddzAccountService.decrNotifyCountByAlipayId(alipayId, 1) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(effectCount) ;
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		return SUCCESS ;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}
	
	

}
