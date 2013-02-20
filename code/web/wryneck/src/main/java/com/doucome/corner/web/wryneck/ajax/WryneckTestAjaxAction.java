package com.doucome.corner.web.wryneck.ajax;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckTestDO;
import com.doucome.corner.biz.dcome.business.wryneck.WryneckTestBO;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckTestDTO;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckUserDTO;
import com.doucome.corner.biz.dcome.service.wryneck.WryneckUserService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.wryneck.action.WryneckBasicAction;

/**
 * ≤‚≤‚ƒ„ «ƒƒ÷÷Õ·≤±◊”
 * @author langben 2013-1-7
 *
 */
public class WryneckTestAjaxAction extends WryneckBasicAction {

	private JsonModel<WryneckTestDTO> json = new JsonModel<WryneckTestDTO>() ;
	
	@Autowired
	private WryneckTestBO wryneckTestBO ;
	
	@Autowired
	private WryneckUserService wryneckUserService ;
	
	@Override
	public String execute() throws Exception {
		
		Long userId = getUserId() ;
		
		if(IDUtils.isNotCorrect(userId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("wryneck.user.notLogin") ;
			return SUCCESS ;
		}
		
		WryneckUserDTO user = getUser() ;
		WryneckTestDTO dto = user.getWryneckTestModel() ;
		if(dto == null){
			dto  = wryneckTestBO.test() ;		
		}	
		
		if(dto == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("wryneck.config.error") ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(dto) ;
		WryneckTestDO testDO = new WryneckTestDO() ;
		testDO.setName(dto.getName()) ;
		testDO.setId(dto.getId()) ;
		testDO.setShowText(dto.getShowText()) ;
		wryneckUserService.updateTestResult(userId, JacksonHelper.toJSON(testDO)) ;
		
		return SUCCESS ;
	}

	public JsonModel<WryneckTestDTO> getJson() {
		return json;
	}
	
	
}
