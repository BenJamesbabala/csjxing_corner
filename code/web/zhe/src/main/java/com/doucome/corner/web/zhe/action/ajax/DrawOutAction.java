package com.doucome.corner.web.zhe.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.dal.dataobject.DdzDrawApproveDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.bo.DdzTaokeReportSettleBO;
import com.doucome.corner.biz.zhe.exception.ManualSettleNotSufficientException;
import com.doucome.corner.biz.zhe.model.ManualSettleResult;
import com.doucome.corner.biz.zhe.service.DdzDrawApproveService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.zhe.action.DdzBasicAction;

/**
 * Ã·œ÷
 * @author langben 2012-9-24
 *
 */
@SuppressWarnings("serial")
public class DrawOutAction extends DdzBasicAction {

	private static final Log log = LogFactory.getLog(ConfirmLoginAlipayAction.class) ;
	
	private JsonModel<ManualSettleResult> json = new JsonModel<ManualSettleResult>();

	@Autowired
	private DdzTaokeReportSettleBO ddzTaokeReportSettleBO ;
	
	@Autowired
	private DdzDrawApproveService ddzDrawApproveService ;
	
	@Override
	public String execute() throws Exception {
		
		DdzUserDO user = ddzAuthz.getUser() ;
		
		if(user == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.manual.user.required") ;
			return SUCCESS ;
		}
		
		String alipayId = user.getAlipayId() ;
		
		if(StringUtils.isBlank(alipayId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.manual.user.required") ;
			return SUCCESS ;
		}
		try {
			ManualSettleResult result = ddzTaokeReportSettleBO.manualCombineSettlesByUser(alipayId) ;
			if(result.isSuccess()){
				DdzDrawApproveDO approve = new DdzDrawApproveDO() ;
				approve.setAlipayAccount(alipayId) ;
				approve.setLoginId(user.getLoginId()) ;
				approve.setSettleIds(ArrayStringUtils.toString(result.getSettleIdList())) ;
				ddzDrawApproveService.createApprove(approve) ;
			}
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(result) ;
		} catch (ManualSettleNotSufficientException e){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("ddz.settle.manual.noSufficient") ;
			return SUCCESS ;
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		return SUCCESS ;
	}

	public JsonModel<ManualSettleResult> getJson() {
		return json;
	}
	
}
