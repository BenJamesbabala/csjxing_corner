package com.doucome.corner.web.bops.action.dcome.qq;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.ParameterUtils;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 评论操作
 * @author langben 2012-7-25
 *
 */
@SuppressWarnings("serial")
public class BopsCommentAjaxAction extends BopsBasicAction{
	
	private Long commentId ;
	
	private String status ;
	
	private Long itemId ;
	/**
	 * 评论内容
	 */
	private String content;
	
	private JsonModel<List<DcCommentDTO>> comments = new JsonModel<List<DcCommentDTO>>();

	private JsonModel<Boolean> json = new JsonModel<Boolean>() ;
	
	@Autowired
	private DcCommentService dcCommentService ;
	
	@Autowired
	private DcItemBO dcItemBO ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	private static final Log log = LogFactory.getLog(BopsItemAjaxAction.class) ;
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
	
	/**
	 * 灌水。
	 * @return
	 */
	public String addBopsComment() {
		if(StringUtils.isEmpty(content)) {
			comments.setCode(JsonModel.CODE_FAIL) ;
			comments.setDetail("content.required") ;
			return SUCCESS ;
		}
		if (!ParameterUtils.isLongIdValid(itemId)) {
			comments.setCode(JsonModel.CODE_ILL_ARGS) ;
			comments.setDetail("itemId.required") ;
			return SUCCESS ;
		}
		try {
			String temp = URLDecoder.decode(content,"UTF-8");
			List<String> tempComments = new ArrayList<String> (Arrays.asList(temp.split(";")));
			for (int i = tempComments.size() - 1; i >= 0; i--) {
				if (StringUtils.isEmpty(tempComments.get(i)) || StringUtils.length(tempComments.get(i)) > 160) {
					tempComments.remove(i);
				}
			}
			List<DcCommentDTO> result = null;
			if (tempComments.size() == 1) {
				DcCommentDTO comment = dcItemBO.addBopsComment(null, itemId, tempComments.get(0));
				result = new ArrayList<DcCommentDTO>();
				result.add(comment);
			} else {
				result = dcItemBO.addBopsComments(itemId, tempComments);
			}
			comments.setCode(JsonModel.CODE_SUCCESS);
			comments.setData(result) ;
		} catch (Exception e) {
			log.error(e.getMessage() , e) ;
			comments.setCode(JsonModel.CODE_FAIL) ;
			comments.setDetail("server.error") ;
			return SUCCESS ;
		}
		return SUCCESS;
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

	public JsonModel<List<DcCommentDTO>> getComments() {
		return comments;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
