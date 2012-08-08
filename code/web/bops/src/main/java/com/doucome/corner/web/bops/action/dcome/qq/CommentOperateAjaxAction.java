package com.doucome.corner.web.bops.action.dcome.qq;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 评论操作
 * @author shenjia.caosj 2012-7-25
 *
 */
@SuppressWarnings("serial")
public class CommentOperateAjaxAction  extends  BopsBasicAction{

	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
	private DcCommentService dcCommentService ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	private Long commentId ;
	
	private String status ;
	
	/**
	 * 更新评论状态
	 * @return
	 */
	public String status() throws Exception {
		
		if(StringUtils.isBlank(status) || commentId == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("commentId and status cant be blank.") ;
			return SUCCESS ;
		}
		
		DcCommentStatusEnums toStatusEnum = DcCommentStatusEnums.toEnum(this.status) ;
		if(DcCommentStatusEnums.UNKNOWN == toStatusEnum){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("unknown status : " + status) ;
			return SUCCESS ;
		}
		
		DcCommentDTO comment = dcCommentService.getCommentById(commentId) ;
		
		if(comment == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("comment not exists") ;
			return SUCCESS ;
		}
		
		String oldStatus = comment.getStatus() ;
		
		DcCommentStatusEnums oldStatusEnum = DcCommentStatusEnums.toEnum(oldStatus) ;
		if(oldStatusEnum != toStatusEnum){
			int count = dcCommentService.updateStatus(commentId , toStatusEnum) ;
			if(count > 0){
				if(oldStatusEnum == DcCommentStatusEnums.NORMAL){ //从正常的评论变成其他状态
					dcItemService.descCommentCount(comment.getItemId()) ;
				}else if(toStatusEnum == DcCommentStatusEnums.NORMAL){ //把其他状态的评论变成正常
					dcItemService.incrCommentCount(comment.getItemId()) ;
				}
			}
			json.setDetail("effect count : " + count) ;
		}
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		return SUCCESS ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
