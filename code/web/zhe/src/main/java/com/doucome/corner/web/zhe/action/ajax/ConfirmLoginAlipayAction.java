package com.doucome.corner.web.zhe.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzUserService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.zhe.action.DdzBasicAction;

/**
 * 
 * @author langben 2012-9-23
 * 
 */
@SuppressWarnings("serial")
public class ConfirmLoginAlipayAction extends DdzBasicAction {

	private static final Log log = LogFactory.getLog(ConfirmLoginAlipayAction.class) ;
	
	private JsonModel<Boolean> json = new JsonModel<Boolean>();

	private String bindAlipayAccount;

	@Autowired
	private DdzAccountService ddzAccountService ;
	
	@Autowired
	private DdzUserService ddzUserService;

	@Override
	public String execute() throws Exception {

		if(StringUtils.isBlank(bindAlipayAccount)) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.confirm.alipay.required") ;
			return SUCCESS ;
		}
		
		DdzAccountDO account = ddzAccountService.queryAccountDOByAlipayId(bindAlipayAccount) ;
		
		if(account == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.confirm.alipay.notExists") ;
			return SUCCESS ;
		}
		
		DdzUserDO user = ddzAuthz.getUser() ;
		
		if(user == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.confirm.alipay.user.notExists") ;
			return SUCCESS ;
		}
		
		String loginId = user.getLoginId() ;
		
		if(StringUtils.isBlank(loginId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.confirm.alipay.user.notExists") ;
			return SUCCESS ;
		}
		try {
			
			//修改支付宝次数
			DdzUserDO u = ddzUserService.getByLoginId(loginId) ;
			int mc = u.getModificationCount() ;
			if(mc > 5){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("ddz.confirm.alipay.modification.maxCount") ;
				return SUCCESS ;
			}
			
			int effectCount = ddzUserService.updateAlipayIdByLoginId(loginId, bindAlipayAccount) ;
			ddzUserService.incrModificationCount(loginId) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;			
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		return SUCCESS;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setBindAlipayAccount(String bindAlipayAccount) {
		this.bindAlipayAccount = bindAlipayAccount;
	}

}
