package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.dcome.service.DcSceneService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

@SuppressWarnings("serial")
public class SceneOperateAjaxAction extends  BopsBasicAction{

	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
	private DcSceneService dcSceneService ;
	
	private Long sceneId ;
	
	private String active ;
	
	
	public String active() throws Exception {
		
		if(sceneId == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;;
			json.setDetail("sceneId cant null .") ;
			return SUCCESS ;
		}
		
		TrueFalseEnums activeEnums = TrueFalseEnums.toEnum(active) ;
		if(activeEnums == TrueFalseEnums.UNKNOWN){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;;
			json.setDetail("active value error .") ;
			return SUCCESS ;
		}
		
		int count = dcSceneService.updateSceneActiveById(sceneId, activeEnums) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setDetail("effect count : " + count) ;
		return SUCCESS ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	
}
