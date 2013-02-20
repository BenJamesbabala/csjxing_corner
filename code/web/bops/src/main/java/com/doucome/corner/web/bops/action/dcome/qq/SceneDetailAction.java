package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dcome.business.DcSceneBO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.service.DcSceneService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * ≤È—Ø≥°æ∞
 * @author langben 2012-7-24
 *
 */
@SuppressWarnings("serial")
public class SceneDetailAction extends BopsBasicAction {

	private Long sceneId ;
	
	private int page ;
	
	private DcSceneDTO sceneDTO ;
	
	private QueryResult<DcItemDTO> itemQuery ;
	
	@Autowired
	private DcSceneService dcSceneService ;
	
	@Autowired
	private DcSceneBO dcSceneBO ;
	
	@Override
	public String execute() throws Exception {
		
		if(sceneId == null){
			return SUCCESS ;
		}
				
		sceneDTO = dcSceneService.getSceneById(sceneId) ;
		
		itemQuery = dcSceneBO.getItemsWithPagination(sceneId, new Pagination(page)) ;
		
		return SUCCESS ;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public DcSceneDTO getSceneDTO() {
		return sceneDTO;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcItemDTO> getItemQuery() {
		return itemQuery;
	}

	
}
