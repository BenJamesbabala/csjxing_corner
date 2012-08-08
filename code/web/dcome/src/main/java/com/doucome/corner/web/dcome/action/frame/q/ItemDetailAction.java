package com.doucome.corner.web.dcome.action.frame.q;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.business.DcSceneBO;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.model.facade.DcSceneQIndexFacade;
import com.doucome.corner.biz.dcome.service.DcCategoryService;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 商品detail
 * @author shenjia.caosj 2012-7-29
 *
 */
@SuppressWarnings("serial")
public class ItemDetailAction extends DComeBasicAction {

	private Long id ;
	
	private Long sceneId ;
	
	////////////////////////////////////
	
	private DcItemDTO item ;
	
	private DcCategoryDTO category ;
	
	private List<DcCommentDTO> commentList ;
	
	private List<DcItemDTO>  relativeItemList ;
	
	private DcSceneDTO sceneDTO ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcCategoryService dcCategoryService ;
	
	@Autowired
	private DcCommentService dcCommentService ;
	
	@Autowired
	private DcSceneBO dcSceneBO ;
	
	@Override
	public String execute() throws Exception {
		item = dcItemService.getItemById(id) ;
		if(item != null && item.getCategoryId() != null){
			category = dcCategoryService.getCategoryById(item.getCategoryId()) ;
			
			DcCommentSearchCondition condition = new DcCommentSearchCondition() ;
			condition.setStatus(DcCommentStatusEnums.NORMAL.getValue()) ;
			condition.setItemId(item.getId()) ;
			commentList = dcCommentService.getCommentsNoPagination(condition, new Pagination(1,5)) ;
			
		}
		
		//根据场景获取关联的item
		if(sceneId != null){ 
			DcSceneQIndexFacade idxFacade = dcSceneBO.getQIndexFacade(sceneId) ;
			if(idxFacade != null) {
				Long catId = item.getCategoryId() ;
				relativeItemList = idxFacade.getCatItemMappings().get(catId) ;
				sceneDTO = idxFacade.getDcSceneDTO() ;
			}
		}
		
		//关联的推荐item为空
		if(CollectionUtils.isEmpty(relativeItemList)){
			DcItemSearchCondition condition = new DcItemSearchCondition() ;
			condition.setCategoryId(item.getCategoryId()) ;
			relativeItemList = dcItemService.getItemsNoPagination(condition, new Pagination(1,9)) ;
		}
				
		return SUCCESS ;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DcItemDTO getItem() {
		return item;
	}

	public DcCategoryDTO getCategory() {
		return category;
	}

	public List<DcCommentDTO> getCommentList() {
		return commentList;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public DcSceneDTO getSceneDTO() {
		return sceneDTO;
	}

	public List<DcItemDTO> getRelativeItemList() {
		return relativeItemList;
	}
	
	

}
