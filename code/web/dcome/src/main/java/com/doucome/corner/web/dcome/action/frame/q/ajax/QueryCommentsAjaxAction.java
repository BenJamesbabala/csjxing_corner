package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.business.DcCommentBO;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;


@SuppressWarnings("serial")
public class QueryCommentsAjaxAction extends DComeBasicAction{
	
	private static final int DEFAULT_SIZE = 5 ;

	private JsonModel<List<DcCommentDTO>> json = new JsonModel<List<DcCommentDTO>>();
	
	private int page ;
	
	private int size = DEFAULT_SIZE ;
	
	private Long itemId ;
	
	@Autowired
	private DcCommentBO dcCommentBO ; 
	
	@Override
	public String execute() throws Exception {
		
		if(page < 1){
			page = 1;
		}
		if(size > 20 || size < 1){
			size = DEFAULT_SIZE ;
		}
		
		if(itemId == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.queryComment.itemId.required") ;
			return SUCCESS;
		}
		
		List<DcCommentDTO> commentList = dcCommentBO.getCommentsNoPagination(itemId,page,size);;
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(commentList) ;
		
		return SUCCESS ;
	}

	
	public void setPage(int page) {
		this.page = page;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public JsonModel<List<DcCommentDTO>> getJson() {
		return json;
	}
	
	
}
