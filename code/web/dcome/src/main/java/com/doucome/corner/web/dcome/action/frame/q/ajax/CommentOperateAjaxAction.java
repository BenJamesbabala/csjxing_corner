package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.core.qq.model.QqCsecWordModel;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.model.facade.PfModel;
import com.doucome.corner.biz.dcome.model.facade.QQPfModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 添加评论
 * @author langben 2012-7-28
 *
 */
@SuppressWarnings("serial")
public class CommentOperateAjaxAction extends DComeBasicAction {
	
	private static final Log log = LogFactory.getLog(CommentOperateAjaxAction.class) ;

	/**
	 * 商品ID
	 */
	private Long itemId ;
		
	/**
	 * 评论内容
	 */
	private String content ;
	
	@Autowired
	private QqQueryService qqQueryService ;
	
	
	private JsonModel<DcCommentDTO> json = new JsonModel<DcCommentDTO>() ;

	@Autowired
	private DcItemBO dcItemBO ;
	
	public String add(){
		
		Long userId = dcAuthz.getUserId() ;
		String userNick = dcAuthz.getPfNick() ;
		DcLoginSourceEnums source = dcAuthz.getLoginSource() ;
		
		DcCommentDO comment = new DcCommentDO() ;
		try {
			comment.setContent(URLDecoder.decode(content,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage() , e);
		}
		comment.setItemId(itemId);
		
		comment.setUserNick(userNick) ;
		comment.setUserId(userId) ;
		comment.setSource(source.getValue());
		String errMsg = this.validate(comment);
		if(StringUtils.isNotBlank(errMsg)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail(errMsg) ;
			return SUCCESS ;
		}
		try {
			
			PfModel pfm = dcAuthz.getPfModel() ;
			if(pfm != null && (pfm.getPf() == DcLoginSourceEnums.Pengyou || pfm.getPf() == DcLoginSourceEnums.Qzone)){
				QQPfModel qpfm = (QQPfModel) pfm ;
				//过滤敏感词
				QqCsecWordModel qm = qqQueryService.csecWordFilter(comment.getContent(),qpfm.getPf().getValue() , qpfm.getOpenId(), qpfm.getOpenKey()) ;
				if(qm != null && qm.isDirty()){
					comment.setContent(qm.getMsg()) ;
				}
			}
			
			
			Long commentId = dcItemBO.addComment(comment);
			json.setDetail("id:" + commentId) ;
			json.setData(new DcCommentDTO(comment)) ;
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail("internal error .") ;
			return SUCCESS ;
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		return SUCCESS ;
	}
	
	private String validate(DcCommentDO comment){
		//输入为空
		if(StringUtils.isEmpty(comment.getContent())){
			return "dcome.addComment.content.required" ;
		}
		if(StringUtils.length(comment.getContent()) > 160){
			return "dcome.addComment.content.maxLength" ;
		}
		if(comment.getUserId() == null){
			return "dcome.addComment.userId.required" ;
		}
		if(comment.getItemId() == null){
			return "dcome.addComment.itemId.required" ;
		}
		return null ;
	}

	public JsonModel<DcCommentDTO> getJson() {
		return json;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
