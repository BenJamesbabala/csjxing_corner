package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaoCommentDTO;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcCommentDTO extends AbstractModel {

	private static final long serialVersionUID = 1347748747847848621L;

	private DcCommentDO comment ;
	
	public DcCommentDTO() {
		this.comment = new DcCommentDO();
	}
	
	public DcCommentDTO(DcCommentDO comment) {
		if(comment == null){
			comment = new DcCommentDO() ;
		}
		this.comment = comment ;
	}
	
	public DcCommentDTO(TaobaoCommentDTO comment) {
		this();
		setSource(comment.getSource());
		setContent(comment.getContent());
		setUserNick(comment.getUserNick());
	}
	
	public String getUserAvatar30x30(){
		return PictureUtils.findPic(AvatarUtils.buildAvatarPath(comment.getUserId()) , PictureSizeEnums._30x30) ;
	}
	
	public String getUserAvatar50x50(){
		return PictureUtils.findPic(AvatarUtils.buildAvatarPath(comment.getUserId()) , PictureSizeEnums._50x50) ;
	}
	
	public String getContentEsc(){
		return StringEscapeUtils.escapeHtml(this.getContent()) ;
	}
	
	public Long getId() {
		return comment.getId();
	}

	public String getStatus() {
		return comment.getStatus();
	}

	public Long getItemId() {
		return comment.getItemId();
	}

	public String getSource() {
		return comment.getSource();
	}
	
	public void setSource(String source) {
		this.comment.setSource(source);
	}
	
	public String getContent() {
		return comment.getContent();
	}
	
	public void setContent(String content) {
		this.comment.setContent(content);
	}

	public Long getUserId() {
		return comment.getUserId();
	}

	public String getUserNick() {
		return comment.getUserNick();
	}
	
	public void setUserNick(String userNick) {
		this.comment.setUserNick(userNick);
	}
	
	public Date getGmtModified() {
		return comment.getGmtModified();
	}

	public Date getGmtCreate() {
		return comment.getGmtCreate();
	}

}