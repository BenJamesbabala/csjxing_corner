package com.doucome.corner.web.bops.action.zhe;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.zhe.cache.BuyingRecommendItemCache;
import com.doucome.corner.biz.zhe.service.DdzRecommendService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

@SuppressWarnings("serial")
public class BuyingRecommDisplayAjaxAction extends  BopsBasicAction {

	private String display = TrueFalseEnums.TRUE.getValue() ;
	
	private String id ;
	
	private JsonModel<Integer> json = new JsonModel<Integer>();
	
	@Autowired
	private DdzRecommendService ddzRecommendService ;
	
	@Autowired
	private BuyingRecommendItemCache buyingRecommendItemCache ;
	
	
	public String execute() throws Exception {
		if(StringUtils.isBlank(id)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("input id is blank.") ;
			return SUCCESS ;
		}
		try {
			ddzRecommendService.updateRecommendDisplayById(Integer.valueOf(id), display) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
		}catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("exception : " + e.getMessage()) ;
		}
		return SUCCESS ;
	}
	
	public String refreshCache(){
		buyingRecommendItemCache.clear() ;
		json.setCode(JsonModel.CODE_SUCCESS) ;
		return SUCCESS ;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setDisplay(String display) {
		this.display = display;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}
	
	
}
