package com.doucome.corner.biz.dcome.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.enums.DcPromScheduleEnum;
import com.doucome.corner.biz.dcome.enums.DcUserLevelEnum;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.biz.dcome.utils.DcNumberUtils;

/**
 * 
 * @author ze2200
 *
 */
public class DcExchangeItemDTO extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	
	private DcExchangeItemDO exchangeItem;
	
	private DcPromScheduleEnum schedule;
	
	private List<DcItemPicModel> picUrlModels;
	
	private String itemPromPriceFmt;
	
	private String itemCommissionFmt;
	
	private int unreviewCount;
	
	private int unSendCount;
	
	private DcUserLevelEnum userLevelEnum;
	
	public DcExchangeItemDTO() {
		this.exchangeItem = new DcExchangeItemDO();
		picUrlModels  = new ArrayList<DcItemPicModel>();
	}
	
	public DcExchangeItemDTO(DcExchangeItemDO exchangeItem) {
		this.exchangeItem = exchangeItem;
		if (DcNumberUtils.isGreaterThan0(getExIntegral()) && DcNumberUtils.isGreaterThan0(getExCount())) {
			this.schedule = DcPromScheduleEnum.ONGOING;
		} else if (getExIntegral() == null || getExCount() == null) {
			this.schedule = DcPromScheduleEnum.FUTURE;
		} else {
			this.schedule = DcPromScheduleEnum.ENDED;
		}
		this.picUrlModels = DcItemUtils.parsePicUrls(exchangeItem.getItemPictures());
	}
	
	public String getPicUrl(int index , String type){
		if(index >= CollectionUtils.size(this.picUrlModels)){
			return null;
		}
		
		DcItemPicModel picModel = this.picUrlModels.get(index) ;
		
		if(picModel == null){
			return null ;
		}
		
		String picUrl = picModel.getUrl();
		if (picUrl.indexOf("taobaocdn") != -1) {
			return picUrl;
		}
		return DcItemUtils.getPictureAbsoluteUrl(picUrl, PictureSizeEnums.toEnum(type)) ;	
	}
	
    public DcItemPicModel getPicModel(int index){
		if(index >= CollectionUtils.size(this.picUrlModels)){
			return null ;
		}
		return this.picUrlModels.get(index) ;
	}
	
	public Long getId() {
		return exchangeItem.getId();
	}

	public void setId(Long id) {
		exchangeItem.setId(id);
	}

	public Long getItemId() {
		return exchangeItem.getItemId();
	}
	
	public void setItemId(Long itemId) {
		exchangeItem.setItemId(itemId);
	}
	
	public String getItemTitle() {
		return exchangeItem.getItemTitle();
	}
	
	public void setItemTitle(String itemTitle) {
		exchangeItem.setItemTitle(itemTitle);
	}
	
	public BigDecimal getItemPrice() {
		return exchangeItem.getItemPrice();
	}
	
	public void setItemPrice(BigDecimal itemPrice) {
		exchangeItem.setItemPrice(itemPrice);
	}
	
	public String getItemPriceFmt() {
		return DecimalUtils.format(getItemPrice(), "###,##0.00");
	}

	public String getItemPictures() {
		return exchangeItem.getItemPictures();
	}
	
	public void setItemPictures(String pictures) {
		exchangeItem.setItemPictures(pictures);
	}
	
	public String getItemType() {
		return exchangeItem.getItemType();
	}
	
	public void setItemType(String itemType) {
		exchangeItem.setItemType(itemType);
	}
	
	public Integer getUserExIntegral() {
		Integer exIntegral = exchangeItem.getExIntegral();
		if (userLevelEnum == null) {
			return exIntegral;
		}
		return userLevelEnum.getDiscount().multiply(new BigDecimal(exIntegral)).intValue();
	}
	
	public Integer getExIntegral() {
		return exchangeItem.getExIntegral();
	}
	
	public void setExIntegral(Integer exIntegral) {
		exchangeItem.setExIntegral(exIntegral);
	}

	public Integer getExCount() {
		return exchangeItem.getExCount();
	}
	
	public void setExCount(Integer exCount) {
		exchangeItem.setExCount(exCount);
	}
	
	public Integer getExSuccCount() {
		return exchangeItem.getExSuccCount();
	}
	
	public Long getUserId() {
		return exchangeItem.getUserId();
	}

	public void setUserId(Long userId) {
		exchangeItem.setUserId(userId);
	}
	
	public Date getGmtModified() {
		return exchangeItem.getGmtModified();
	}
	
	public Date getGmtCreate() {
		return exchangeItem.getGmtCreate();
	}
	
	public DcPromScheduleEnum getSchedule() {
		return this.schedule;
	}
	
	public String getScheduleValue() {
		return schedule == null? "" : schedule.getValue();
	}
	
	public void setItemPromPriceFmt(String itemPromPrice) {
		this.itemPromPriceFmt = itemPromPrice;
	}
	
	public String getItemPromPriceFmt() {
		return this.itemPromPriceFmt;
	}
	
	public void setItemCommissionFmt(String commissionFmt) {
		this.itemCommissionFmt = commissionFmt;
	}
	
	public String getItemCommissionFmt() {
		return itemCommissionFmt;
	}

	public int getUnreviewCount() {
		return unreviewCount;
	}

	public void setUnreviewCount(int unreviewCount) {
		this.unreviewCount = unreviewCount;
	}

	public int getUnSendCount() {
		return unSendCount;
	}

	public void setUnSendCount(int unSendCount) {
		this.unSendCount = unSendCount;
	}
	
	public void setUserLevelEnum(DcUserLevelEnum userLevelEnum) {
		this.userLevelEnum = userLevelEnum;
	}
	
	public DcExchangeItemDO toDO() {
		return exchangeItem;
	}
	
	public String getExType() {
		return exchangeItem.getExType();
	}

	public String getRequireFields() {
		return exchangeItem.getRequireFields();
	}
}
