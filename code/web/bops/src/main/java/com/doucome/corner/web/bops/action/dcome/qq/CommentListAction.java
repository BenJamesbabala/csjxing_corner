package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class CommentListAction extends BopsBasicAction implements ModelDriven<DcCommentSearchCondition>{

	private DcCommentSearchCondition condition = new DcCommentSearchCondition() ;

	@Autowired
	private DcCommentService dcCommentService ;
	
	private int page ;
	
	private QueryResult<DcCommentDTO> commentQuery ;
	
	@Override
	public String execute() throws Exception {
		
		commentQuery = dcCommentService.getCommentsWithPagination(condition, new Pagination(page)) ;
		
		return SUCCESS ;
	}
	
	@Override
	public DcCommentSearchCondition getModel() {
		return condition ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcCommentDTO> getCommentQuery() {
		return commentQuery;
	} 
	
	
	
}
