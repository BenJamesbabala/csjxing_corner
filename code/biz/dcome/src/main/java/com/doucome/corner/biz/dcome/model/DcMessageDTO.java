package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcMessageTimeEnum;
/**
 * 豆蔻信息
 * @author ze2200
 *
 */
public class DcMessageDTO extends AbstractModel {
	
	private static final String DEFAULT_TEXT = "";
	
	private static final long serialVersionUID = 96739412072065149L;
	
	private Long userId;
	/**
	 * 消息拥有者.
	 */
	private String userNick;
	/**
	 * 
	 */
	private String activity;
	/**
	 * 消息相关者.
	 */
	private String relateObjName;
	/**
	 * 
	 */
	private Long relateObjId;
	
	private String otherInfo;
	
	private int integral;
	
	private Date gmtCreate;
	
	private DcMessageTimeEnum messageTime;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick == null ? DEFAULT_TEXT: userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getRelateObjName() {
		return relateObjName == null ? DEFAULT_TEXT: relateObjName;
	}

	public void setRelateObjName(String relateObjName) {
		this.relateObjName = relateObjName;
	}

	public Long getRelateObjId() {
		return relateObjId;
	}

	public void setRelateObjId(Long relateObjId) {
		this.relateObjId = relateObjId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public int getIntegral() {
		return this.integral;
	}
	
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	
	public String getOtherInfo() {
		return this.otherInfo;
	}
	
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		messageTime = DcMessageTimeEnum.getEnum(gmtCreate);
	}
	
	public Date getGmtCreate() {
		return this.gmtCreate;
	}
	
	public String getTimeType() {
		return messageTime.getType();
	}

	public String getTimeFmt() {
		return messageTime.getFmtTime(gmtCreate);
	}
	
	public String getUserAvatar30x30() {
		return DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER)
		  + PictureUtils.findPic(AvatarUtils.buildAvatarPath(getUserId()), PictureSizeEnums._30x30);
	}
}
