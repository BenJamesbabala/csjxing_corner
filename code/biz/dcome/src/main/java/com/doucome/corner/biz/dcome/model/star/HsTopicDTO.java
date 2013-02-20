package com.doucome.corner.biz.dcome.model.star;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsTopicDO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dal.model.HsTopicPicModel;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

/**
 * HsMonthFateDTO
 * @author 
 *
 */
public class HsTopicDTO extends AHsFateDTO {
	
	private static final long serialVersionUID = 1L;
	
    private HsTopicDO hsTopic;
    
    private HsTopicPicModel picModel;
	
	public HsTopicDTO() {
		this(null);
	}
	
	public HsTopicDTO(HsTopicDO hsTopic){
		if(hsTopic == null){
			hsTopic = new HsTopicDO();
		}
		try {
			picModel = JacksonHelper.fromJSON(hsTopic.getPicture(), HsTopicPicModel.class);
		} catch (Exception e) {
			
		}
		this.hsTopic = hsTopic ;
	}
	
	public String getPicUrl(String type){
		if(picModel == null){
			return null;
		}
		String picUrl = picModel.getPath();
		return DcItemUtils.getPictureAbsoluteUrl(picUrl, PictureSizeEnums.toEnum(type)) ;
	}
	
	public Long getId() {
		return hsTopic.getId();
	}

	public void setId(Long id) {
		hsTopic.setId(id);
	}

	public String getTitle() {
		return hsTopic.getTitle();
	}

	public void setTitle(String title) {
		hsTopic.setTitle(title);
	}

	public String getContent() {
		return hsTopic.getContent();
	}

	public void setContent(String content) {
		hsTopic.setContent(content);
	}

	public String getPicture() {
		return hsTopic.getPicture();
	}

	public void setPicture(String picture) {
		hsTopic.setPicture(picture);
	}

	public Date getGmtDay() {
		return hsTopic.getGmtDay();
	}

	public void setGmtDay(Date gmtDay) {
		hsTopic.setGmtDay(gmtDay);
	}

	public Date getGmtCreate() {
		return hsTopic.getGmtCreate();
	}

	public void setGmtCreate(Date gmtCreate) {
		hsTopic.setGmtCreate(gmtCreate);
	}
	
	public HsTopicPicModel getPicModel() {
		return picModel;
	}
	
	public String getPic220x220() {
		return getPicUrl("220x220");
	}

	public HsTopicDO toDO() {
		return this.hsTopic;
	}
}
