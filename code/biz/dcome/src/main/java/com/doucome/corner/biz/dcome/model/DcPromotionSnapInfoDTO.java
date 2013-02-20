package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionSnapInfoDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcPromotionSnapInfoDTO extends AbstractModel{

	private DcPromotionSnapInfoDO snapInfo;

	private String itemTitle;
	
	private String itemPicUrl160x160;
	
	private String itemPrice;
	
	public DcPromotionSnapInfoDTO(DcPromotionSnapInfoDO snapInfo){
		if(snapInfo == null){
			snapInfo = new DcPromotionSnapInfoDO() ;
		}
		this.snapInfo = snapInfo ;
	}
	
	public Long getId() {
		return snapInfo.getId();
	}

	public Long getPromotionId() {
		return snapInfo.getPromotionId() ;
	}
	
	public Long getItemId() {
		return snapInfo.getItemId();
	}

	public Integer getWishCount() {
		return snapInfo.getWishCount();
	}

	public Date getGmtModified() {
		return snapInfo.getGmtModified();
	}

	public Date getGmtCreate() {
		return snapInfo.getGmtCreate();
	}

	public DcPromotionSnapInfoDO getSnapInfo() {
		return snapInfo;
	}

	public void setSnapInfo(DcPromotionSnapInfoDO snapInfo) {
		this.snapInfo = snapInfo;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemPicUrl160x160() {
		return itemPicUrl160x160;
	}

	public void setItemPicUrl160x160(String itemPicUrl160x160) {
		this.itemPicUrl160x160 = itemPicUrl160x160;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
}
