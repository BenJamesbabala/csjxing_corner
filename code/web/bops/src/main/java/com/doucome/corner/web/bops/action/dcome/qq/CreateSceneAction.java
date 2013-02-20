package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;
import com.doucome.corner.biz.dcome.enums.DcCategoryLevelEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.biz.dcome.service.DcSceneService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 创建或者更新
 * @author langben 2012-7-24
 *
 */
@SuppressWarnings("serial")
public class CreateSceneAction  extends BopsBasicAction implements ModelDriven<DcSceneDTO>{
	
	private DcSceneDTO sceneDTO = new DcSceneDTO(null);
	
	/**
	 * input sceneId
	 */
	private Long sceneId ;
	
	private List<DcCategoryDTO> categoryList ;
		
	@Autowired
	private DcSceneService dcSceneService;
	
	@Autowired
	private DcCategoryService dcCategoryService ;
	
	@Override
	public String execute() throws Exception {
		
		if(sceneId != null){
			sceneDTO = dcSceneService.getSceneById(sceneId) ;
			
		}
		
		categoryList = dcCategoryService.getCategoriesByLevel(DcCategoryLevelEnums.CATGORY) ;
	
		return SUCCESS ;
	}
	
	/**
	 * 创建/更新 场景
	 * @return
	 * @throws Exception
	 */
	public String doCreate() throws Exception {
		
		Long id = sceneDTO.getId() ;
		
		categoryList = dcCategoryService.getCategoriesByLevel(DcCategoryLevelEnums.CATGORY) ;
		
		if(!validateCreate(sceneDTO)){
			return INPUT ;
		}
		
		if(id == null){
			//创建
			DcSceneDO scene = sceneDTO.getScene() ;
			dcSceneService.createScene(scene) ;
			
		} else {
			DcSceneDO scene = sceneDTO.getScene();
			dcSceneService.updateSceneById(scene) ;
			
		}
		
		
		
		return SUCCESS ;
	}
	
	private boolean validateCreate(DcSceneDTO dto){
		boolean isValid = true ;
		
		if(StringUtils.isBlank(dto.getName())){
			addFieldError("name", "dcome.createScene.name.required") ;
			isValid = false ;
		}else if(org.apache.commons.lang.StringUtils.length(dto.getName()) > 32) {
			addFieldError("name", "dcome.createScene.name.maxLength") ;
			isValid = false ;
		}
		
		if(StringUtils.isBlank(dto.getSceneDesc())){
			addFieldError("sceneDesc","dcome.createScene.sceneDesc.required") ;
			isValid = false ;
		}else if(org.apache.commons.lang.StringUtils.length(dto.getSceneDesc()) > 160) {
			addFieldError("sceneDesc", "dcome.createScene.sceneDesc.maxLength") ;
			isValid = false ;
		}
		
		if(dto.getCatIdList() == null){
			addFieldError("categoryIds", "dcome.createScene.categoryIds.size") ;
			isValid = false ;
		}else if(CollectionUtils.size(dto.getCatIdList()) != 4){
			addFieldError("categoryIds", "dcome.createScene.categoryIds.size") ;
			isValid = false ;
		}
		
		if(dto.getSystemId() == null){
			addFieldError("systemId", "dcome.createScene.systemId.required") ;
			isValid = false ;
		}
		return isValid ;
	}
	
	@Override
	public DcSceneDTO getModel() {
		return sceneDTO ;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public DcSceneDTO getSceneDTO() {
		return sceneDTO;
	}

	public List<DcCategoryDTO> getCategoryList() {
		return categoryList;
	}
		
}
