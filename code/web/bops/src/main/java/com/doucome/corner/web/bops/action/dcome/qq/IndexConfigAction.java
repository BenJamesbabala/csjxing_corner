package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcSceneService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * …Ë÷√ ◊“≥
 * @author langben 2012-7-26
 *
 */
@SuppressWarnings("serial")
public class IndexConfigAction  extends BopsBasicAction {

	private Long sceneId ;
	
	private DcSceneDTO sceneDTO ;
	
	private List<DcItemDTO> itemList ;
	
	
	@Autowired
	private DcSceneService dcSceneService ; 
	
	@Autowired
	private DcItemService dcItemService ;
	
	
	@Override
	public String execute() throws Exception {
		
		if(sceneId == null){
			return INPUT ;
		}
		
		this.sceneDTO = dcSceneService.getSceneWithDetailsById(sceneId) ;
		
		if(sceneDTO != null){
			itemList = dcItemService.getItemsByIds(this.sceneDTO.getItemIdList()) ;
		}
		
		return SUCCESS ;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public DcSceneDTO getSceneDTO() {
		return sceneDTO;
	}

	public List<DcItemDTO> getItemList() {
		return itemList;
	}
	
	
}
