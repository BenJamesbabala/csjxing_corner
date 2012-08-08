package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.condition.dcome.DcCategorySearchCondition;
import com.doucome.corner.biz.dcome.enums.DcCategoryLevelEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * ≤È—Ø
 * @author shenjia.caosj 2012-7-25
 *
 */
@SuppressWarnings("serial")
public class QueryCategoriesAjaxAction extends  BopsBasicAction {

	private JsonModel<List<DcCategoryDTO>> json = new JsonModel<List<DcCategoryDTO>>() ;

	
	private Long catId ;
	
	@Autowired
	private DcCategoryService dcCategoryService ;
	
	@Override
	public String execute() throws Exception {
				
		DcCategorySearchCondition condition = new DcCategorySearchCondition() ;
		condition.setCategoryLevel(DcCategoryLevelEnums.CATGORY.getValue()) ;
		condition.setId(catId);
		List<DcCategoryDTO> catList = dcCategoryService.getCategories(condition) ;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(catList) ;
		
		return SUCCESS ;
	}

	public JsonModel<List<DcCategoryDTO>> getJson() {
		return json;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	
	
	
}
