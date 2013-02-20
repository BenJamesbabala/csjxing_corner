package com.doucome.corner.web.namefate.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.namefate.model.NamefateTrickDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateTrickService;
import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.namefate.condition.NamefateTrickSearchCondition;


@SuppressWarnings("serial")
public class NamefateTrickListAction extends NamefateBasicAction {

	@Autowired
	private NamefateTrickService namefateTrickService ;
	
	private QueryResult<NamefateTrickDTO> queryResult ;
	
	private int page ;
	
	@Override
	public String execute() throws Exception {
		Long userId = getUserId() ;
		if(IDUtils.isNotCorrect(userId)) {
			return SUCCESS ;
		}
		NamefateTrickSearchCondition condition = new NamefateTrickSearchCondition() ;
		condition.setUserId(userId) ;
		queryResult = namefateTrickService.getTricksWithPagination(condition, new Pagination(page)) ;
		return SUCCESS ;
	}

	public QueryResult<NamefateTrickDTO> getQueryResult() {
		return queryResult;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
