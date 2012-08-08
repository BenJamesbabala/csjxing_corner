package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcSceneSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.service.DcQIndexConfigService;
import com.doucome.corner.biz.dcome.service.DcSceneService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ³¡¾°ÁÐ±í
 * @author shenjia.caosj 2012-7-24
 *
 */
@SuppressWarnings("serial")
public class SceneListAction extends BopsBasicAction implements ModelDriven<DcSceneSearchCondition>{

	private QueryResult<DcSceneDTO> sceneQuery ;
	
	@Autowired
	private DcSceneService dcSceneService ;
	
	@Autowired
	private DcQIndexConfigService dcQIndexConfigService ;
	
	private int page = 1 ;
	
	private DcSceneSearchCondition condition = new DcSceneSearchCondition() ;
	
	@Override
	public String execute() throws Exception {
		
		dcQIndexConfigService.getConfigsByStatus(DcQIndexPubStatusEnums.RELEASE) ;
		
		sceneQuery = dcSceneService.getScenesWithPagination(condition , new Pagination(page)) ;
		
		return SUCCESS ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcSceneDTO> getSceneQuery() {
		return sceneQuery;
	}

	@Override
	public DcSceneSearchCondition getModel() {
		return condition ;
	}
	
	
}
