package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;
import com.doucome.corner.biz.dcome.enums.DcCategoryLevelEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 创建子类目
 * @author shenjia.caosj 2012-7-25
 *
 */
@SuppressWarnings("serial")
public class CreateCategoryAction extends BopsBasicAction implements ModelDriven<DcCategoryDTO>{

	private DcCategoryDTO categoryDTO = new DcCategoryDTO(null) ;
	
	private DcCategoryDTO topCategoryDTO  ;
	
	@Autowired
	private DcCategoryService dcCategoryService ;
	
	private Long catId ;
	
	private Long parentCatId ;
	
	@Override
	public String execute() throws Exception {
		if(catId != null){
			categoryDTO = dcCategoryService.getCategoryById(catId);
		}
		return SUCCESS ;
	}
	
	public String doCreate() throws Exception {
		
		Long id = categoryDTO.getId() ;
		
		categoryDTO.setCategoryLevel(DcCategoryLevelEnums.CATGORY.getValue()) ; //只能创建|修改子类目
		
		if(!validateCreate(categoryDTO)){
			return INPUT ;
		}
		
		//名字是否已经存在
		DcCategorySearchCondition condition = new DcCategorySearchCondition() ;
		condition.setName(categoryDTO.getName()) ;
		List<DcCategoryDTO> catList = dcCategoryService.getCategories(condition) ;
		if(CollectionUtils.isNotEmpty(catList)){
			for(DcCategoryDTO catDto : catList){
				if(org.apache.commons.lang.StringUtils.equalsIgnoreCase(catDto.getName(), categoryDTO.getName())){
					addFieldError("name", "dcome.createCategory.name.exists") ;
					return INPUT ;
				}
			}
		}
		
		if(id == null){

			//创建
			DcCategoryDO cat = new DcCategoryDO() ;
			cat.setName(categoryDTO.getName()) ;
			cat.setCategoryLevel(categoryDTO.getCategoryLevel());
			cat.setParentCategoryId(categoryDTO.getParentCategoryId());
			dcCategoryService.createCategory(cat) ;
			
		} else {
			//只能更新名字
			DcCategoryDO cat = new DcCategoryDO() ;
			cat.setId(categoryDTO.getId()) ;
			cat.setName(categoryDTO.getName()) ;
			//cat.setCategoryLevel(categoryDTO.getCategoryLevel());
			//cat.setParentCategoryId(categoryDTO.getParentCategoryId());
			dcCategoryService.updateCategoryById(cat) ;	
		}
		
		return SUCCESS ;
	}
	
	
	private boolean validateCreate(DcCategoryDTO dto){
		boolean isValid = true ;
		if(StringUtils.isBlank(dto.getName())){
			addFieldError("name", "dcome.createCategory.name.required") ;
			isValid = false ;
		}else if(org.apache.commons.lang.StringUtils.length(dto.getName()) > 32) {
			addFieldError("name", "dcome.createCategory.name.maxLength") ;
			isValid = false ;
		}
		
		if(StringUtils.isBlank(dto.getCategoryLevel())){
			addFieldError("categoryLevel", "dcome.createCategory.categoryLevel.required") ;
			isValid = false ;
		}

		return isValid ;
	}
	@Override
	public DcCategoryDTO getModel() {
		return categoryDTO;
	}

	public DcCategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public Long getParentCatId() {
		return parentCatId;
	}

	public void setParentCatId(Long parentCatId) {
		this.parentCatId = parentCatId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public DcCategoryDTO getTopCategoryDTO() {
		return topCategoryDTO;
	}

	 
}
