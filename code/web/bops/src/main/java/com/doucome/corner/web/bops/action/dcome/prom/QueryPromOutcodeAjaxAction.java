package com.doucome.corner.web.bops.action.dcome.prom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils.OutCode;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils.OutCodeData;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;
import com.doucome.corner.web.bops.model.PromOutcodeModel;

/**
 * ¸ù¾Ýoutcode²éÑ¯
 * @author langben 2012-9-18
 *
 */
@SuppressWarnings("serial")
public class QueryPromOutcodeAjaxAction extends BopsBasicAction {
	
	private static final Log log = LogFactory.getLog(QueryPromOutcodeAjaxAction.class) ;

	private JsonModel<PromOutcodeModel> json = new JsonModel<PromOutcodeModel>() ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	private String outcode ;
	
	@Override
	public String execute() throws Exception {
		
		OutCode oc = OutCodeUtils.decodeOutCode(outcode) ;
		
		if(oc.getType() != OutCodeEnums.DOUCOME_PROMOTION){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.outcode.unknown") ;
			return SUCCESS ;
		}
		
		OutCodeData itemAndUser = DcPromotionOutCodeUtils.decodeOutCode(oc.getOutCode()) ;
		
		Long itemId = itemAndUser.getItemId() ;
		Long userId = itemAndUser.getUserId() ;
		
		if(IDUtils.isNotCorrect(itemId) || IDUtils.isNotCorrect(userId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.outcode.unknown") ;
			return SUCCESS ;
		}
		
		try {
		
			DcItemDTO item = dcItemService.getItemById(itemId);
			DcUserDTO user = dcUserService.getUser(userId) ;
			
			if(item == null || user == null){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.outcode.unknown") ;
				return SUCCESS ;
			}
			
			PromOutcodeModel model = new PromOutcodeModel() ;
			model.setItem(item) ;
			model.setUser(user) ;
			
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(model) ;
			
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
		}
		
		return SUCCESS ;
	}
	
	

	public JsonModel<PromOutcodeModel> getJson() {
		return json;
	}

	public void setOutcode(String outcode) {
		this.outcode = outcode;
	}
	
}
