package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcCommentDTO extends AbstractModel {

	private static final long serialVersionUID = 1347748747847848621L;

	private DcCommentDO comment ;
	
	public DcCommentDTO(DcCommentDO comment) {
		if(comment == null){
			comment = new DcCommentDO() ;
		}
		this.comment = comment ;
	}
	
	public String getUserAvatar30x30(){
		return PictureUtils.findPic(AvatarUtils.buildAvatarPath(comment.getUserId()) , PictureSizeEnums._30x30) ;
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

	public String getContent() {
		return comment.getContent();
	}

	public Long getUserId() {
		return comment.getUserId();
	}

	public String getUserNick() {
		return comment.getUserNick();
	}

	
	public Date getGmtModified() {
		return comment.getGmtModified();
	}


	public Date getGmtCreate() {
		return comment.getGmtCreate();
	}

}
