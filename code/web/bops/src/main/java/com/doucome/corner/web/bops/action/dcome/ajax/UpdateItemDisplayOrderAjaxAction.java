package com.doucome.corner.web.bops.action.dcome.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 更新 Item的显示顺序
 * @author langben 2012-11-4
 *
 */
@SuppressWarnings("serial")
public class UpdateItemDisplayOrderAjaxAction extends BopsBasicAction  {
	
	private static final Log log = LogFactory.getLog(UpdateItemDisplayOrderAjaxAction.class) ;

	private JsonModel<Integer> json = new JsonModel<Integer>() ;
	
	private Long itemId ;
	
	@Autowired
	private DcItemService dcItemService ;
	 
	@Override
	public String execute() throws Exception {
		if(IDUtils.isNotCorrect(itemId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.itemId.required") ;
			return SUCCESS ;
		}
		try {
			int effectCount = dcItemService.updateDisplayOrderById(itemId) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(effectCount) ;	
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
