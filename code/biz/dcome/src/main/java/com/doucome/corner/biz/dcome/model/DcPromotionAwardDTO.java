package com.doucome.corner.biz.dcome.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.DateTool;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;

/**
 * 
 * @author ze2200
 *
 */
public class DcPromotionAwardDTO extends AbstractModel {

	private static final long serialVersionUID = 2159478210036658634L;

	private DcItemDTO dcItemDTO;

	private DcPromotionAwardDO promotionAward;

	public DcPromotionAwardDTO() {
		this(null);
	}
	
	public DcPromotionAwardDTO(DcPromotionAwardDO promotionAward) {
		if (promotionAward == null) {
			promotionAward = new DcPromotionAwardDO();
		}
		this.promotionAward = promotionAward;
	}

	
	
	public DcItemDTO getDcItemDTO() {
		return dcItemDTO;
	}



	public void setDcItemDTO(DcItemDTO dcItemDTO) {
		this.dcItemDTO = dcItemDTO;
	}

	public String getGmtCreateFormat(){
		if(getGmtCreate() == null){
			return StringUtils.EMPTY ;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
		return format.format(getGmtCreate()) ;
	}

	/**
	 * -------------------------------------------------------------------
	 */
	
	public Date getGmtCreate() {
		return promotionAward.getGmtCreate();
	}

	public Date getGmtModified() {
		return promotionAward.getGmtModified();
	}
	
	public void setId(Long id) {
		promotionAward.setId(id);
	}
	
	public Long getId() {
		return promotionAward.getId();
	}

	public Long getUserId() {
		return promotionAward.getUserId();
	}
	
	public void setUserId(Long userId) {
		promotionAward.setUserId(userId);
	}

	public String getDelName() {
		return promotionAward.getDelName();
	}
	
	public void setDelName(String delName) {
		promotionAward.setDelName(delName);
	}

	public String getDelMobile() {
		return promotionAward.getDelMobile();
	}
	
	public void setDelMobile(String delMobile) {
		promotionAward.setDelMobile(delMobile);
	}

	public String getDelProvince() {
		return promotionAward.getDelProvince();
	}

	public String getDelCity() {
		return promotionAward.getDelCity();
	}

	public String getDelArea() {
		return promotionAward.getDelArea();
	}

	public String getDelAddr() {
		return promotionAward.getDelAddr();
	}
	
	public void setDelAddr(String delAddr) {
		promotionAward.setDelAddr(delAddr);
	}
	
	public String getDelOther() {
		return promotionAward.getDelOther();
	}
	
	public void setDelOther(String delOther) {
		promotionAward.setDelOther(delOther);
	}

	public String getDelZipcode() {
		return promotionAward.getDelZipcode();
	}
	
	public void setDelZipcode(String delZipcode) {
		promotionAward.setDelZipcode(delZipcode);
	}

	public Long getPromotionItemId() {
		return promotionAward.getPromotionItemId();
	}

	public String getUserNick() {
		return promotionAward.getUserNick();
	}
	
	public String getUserAvatar50x50() {
		return DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER)
		  + PictureUtils.findPic(AvatarUtils.buildAvatarPath(getUserId()), PictureSizeEnums._50x50);
	}

	public Long getPromotionId() {
		return promotionAward.getPromotionId();
	}

	public Long getItemId() {
		return promotionAward.getItemId();
	}
	
	public String getItemSize() {
		return promotionAward.getItemSize();
	}
	
	public void setItemSize(String itemSize) {
		promotionAward.setItemSize(itemSize);
	}
	
	public String getItemColor() {
		return promotionAward.getItemColor();
	}
	
	public void setItemColor(String itemColor) {
		promotionAward.setItemColor(itemColor);
	}
	
	public String getItemType() {
		return promotionAward.getItemType();
	}
	
	public void setItemType(String itemType) {
		promotionAward.setItemType(itemType);
	}

	public Integer getRateCount() {
		return promotionAward.getRateCount();
	}
	
	public Integer getItemNum() {
		return promotionAward.getItemNum();
	}

	public String getSendStatus() {
		return promotionAward.getSendStatus();
	}
	
	public boolean isSend() {
		return DcPromotionAwardSendStatusEnums.SUCCESS.getStatus().equals(getSendStatus());
	}

	public String getPromotionType() {
		return promotionAward.getPromotionType();
	}

	public String getReviewStatus() {
		return promotionAward.getReviewStatus();
	}

	public void setCheckCode(String checkCode) {
		promotionAward.setCheckCode(checkCode);
	}
	
	public String getCheckCode() {
		return promotionAward.getCheckCode();
	}
	
	public String getItemTitle() {
		return dcItemDTO == null? "" : dcItemDTO.getItemTitle();
	}
	
	public String getShareStatus() {
		return promotionAward.getShareStatus();
	}
	
	public String getItemPriceFmt() {
		if (dcItemDTO == null) {
			return "";
		}
		return DecimalUtils.format(dcItemDTO.getItemPrice(), "###,##0");
	}
	
	public String getItemPic60x60() {
		return dcItemDTO == null? "" : dcItemDTO.getPicUrl(0, PictureSizeEnums._60x60.getName());
	}
	
	public String getItemPic120x120() {
		return dcItemDTO == null? "" : dcItemDTO.getPicUrl(0, PictureSizeEnums._120x120.getName());
	}
	
	public String getItemPic160x160() {
		return dcItemDTO == null? "" : dcItemDTO.getPicUrl(0, PictureSizeEnums._160x160.getName());
	}
	
	public String getGmtCreateFmt() {
		return DateTool.formatToDay(promotionAward.getGmtCreate());
	}
}
