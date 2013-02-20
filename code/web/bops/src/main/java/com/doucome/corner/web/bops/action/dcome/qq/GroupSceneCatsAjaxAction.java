package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcCatCountDO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcSceneDetailService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 统计一个场景
 * @author langben 2012-7-28
 *
 */
@SuppressWarnings("serial")
public class GroupSceneCatsAjaxAction extends  BopsBasicAction{

	private JsonModel<List<DcCatCountDO>> json = new JsonModel<List<DcCatCountDO>>() ;
	
	private Long sceneId  ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcSceneDetailService dcSceneDetailService ;
	
	@Override
	public String execute() throws Exception {
		
		if(sceneId == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("sceneId cant be null.") ;
			return SUCCESS ;
		}
		
		List<Long> itemIdList = dcSceneDetailService.getItemsByScene(sceneId, 10000) ;
		
		if(CollectionUtils.isEmpty(itemIdList)){
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(new ArrayList<DcCatCountDO>()) ;
			return SUCCESS ;
		}
		
		List<DcCatCountDO> catGroupList = dcItemService.groupCategoryCountByIds(itemIdList) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(catGroupList) ;
		
		return SUCCESS ;
	}

	public JsonModel<List<DcCatCountDO>> getJson() {
		return json;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	
	
	
}
