package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcSceneDetailService;
import com.doucome.corner.biz.dcome.service.DcSceneService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class AddSceneItemAction extends BopsBasicAction implements ModelDriven<DcItemSearchCondition> {

	/**
	 * input
	 */
	private Long sceneId ;
	
	private Long catId ;
	
	/**
	 * output
	 */
	
	private QueryResult<DcItemDTO> itemQuery ;
	
	private DcSceneDTO sceneDTO ;
	
	private List<Long> sceneDetailList ;
	
	private DcItemSearchCondition condition = new DcItemSearchCondition() ;
	
	private List<DcCategoryDTO> categoryList ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcSceneService dcSceneService ;
	
	@Autowired
	private DcSceneDetailService dcSceneDetailService ;
	
	@Autowired
	private DcCategoryService dcCategoryService ;
	
	private int page ;
	
	@Override
	public String execute() throws Exception {
		
		sceneDTO = dcSceneService.getSceneById(sceneId);
		List<Long> catList = sceneDTO.getCatIdList() ;
		if(!CollectionUtils.isEmpty(catList)){
			categoryList = dcCategoryService.getCategoriesByIds(catList) ;
		}
		
		if(sceneDTO == null){
			return SUCCESS ;
		}
		
		if(catId != null){
			List<Long> idList = sceneDTO.getCatIdList() ;
			if(CollectionUtils.contains(idList.iterator(), catId)){
				condition.setCategoryId(catId) ;
			}
		}
		
		sceneDetailList = dcSceneDetailService.getItemsByScene(sceneId, 100) ;
		
		if(condition.getCategoryId() == null){
//			condition.setCategoryIds(sceneDTO.getCatIdList()) ;
		}
		
		this.itemQuery = dcItemService.getItemsWithPagination(condition, new Pagination(page , 30));
		
		return SUCCESS ;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	@Override
	public DcItemSearchCondition getModel() {
		return condition ;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public QueryResult<DcItemDTO> getItemQuery() {
		return itemQuery;
	}

	
	public Long getCatId() {
		return catId;
	}

	public DcSceneDTO getSceneDTO() {
		return sceneDTO;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public List<Long> getSceneDetailList() {
		return sceneDetailList;
	}

	public String getSceneDetailString() {
		return ArrayStringUtils.toString(sceneDetailList);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<DcCategoryDTO> getCategoryList() {
		return categoryList;
	}
	
	
}
