package com.doucome.corner.web.zhe.action.ajax;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;
import com.doucome.corner.web.zhe.model.JsonModel;

/**
 * …Ë÷√÷ß∏∂±¶
 * @author shenjia.caosj 2012-6-1
 *
 */
@SuppressWarnings("serial")
public class SetAlipayAction extends BasicAction {

	private String alipayId ;
	
	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
    private DdzSessionOperator        ddzSessionOperator;
	
	@Autowired
	private DdzAccountService         ddzAccountService;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isBlank(alipayId)) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("input alipay is blank.") ;
			return SUCCESS ;
		}
		
		String accountId = generateAccountId() ;
		
		if(StringUtils.isBlank(accountId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("accountId is blank.") ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		return SUCCESS ;
	}
	
	

	public JsonModel<Boolean> getJson() {
		return json;
	}



	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	
	private String generateAccountId() {
		String accountId = null ;
        
        // validate alipayId
        if (!ValidateUtil.checkIsEmail(alipayId) && !ValidateUtil.checkIsMobile(alipayId)) {
            alipayId = null;
        }

        ddzSessionOperator.setAlipayId(alipayId);

        if (StringUtils.isBlank(accountId) && StringUtils.isNotBlank(alipayId)) {
            accountId = ddzAccountService.getAccountIdByAlipayId(alipayId);
        }
        return accountId;
    }
	
}
