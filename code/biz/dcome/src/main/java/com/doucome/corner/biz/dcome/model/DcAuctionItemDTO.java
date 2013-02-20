package com.doucome.corner.biz.dcome.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.constant.Constant;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.DateTool;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcPromScheduleEnum;

public class DcAuctionItemDTO extends AbstractModel {

	private static final long serialVersionUID = 1L;
	
	private DcAuctionItemDO dcAuctionItemDO ;
	
	private DcPromScheduleEnum status;
	
	private List<String> picUrlList;
	
	private List<DcMessageDTO> bidHistories;
	
	public DcAuctionItemDTO() {
		this.dcAuctionItemDO = new DcAuctionItemDO();
	}
	
	public DcAuctionItemDTO(DcAuctionItemDO auctionItemDO) {
		if(auctionItemDO == null){
			auctionItemDO = new DcAuctionItemDO() ;
		}
		this.dcAuctionItemDO = auctionItemDO ;
		status = DcPromScheduleEnum.toEnum(getGmtStart(), getGmtEnd());
		this.picUrlList = ArrayStringUtils.toList(auctionItemDO.getItemPictures()) ;
	}
	
	public String getPicUrl(int index , String type){
		if(CollectionUtils.isEmpty(this.picUrlList)){
			return null ;
		}
		if(index >= this.picUrlList.size()){
			return null;
		}
		
		String picUrl = this.picUrlList.get(index);
		
		String findPicUrl = PictureUtils.findPic(picUrl, PictureSizeEnums.toEnum(type)) ;
		
		if(StringUtils.startsWithIgnoreCase(findPicUrl, Constant.HTTP)){
			return findPicUrl ;
		}
		
		return DefaultUriService.getFactoryURI(URIConstant.DCOME_ITEM_UPLOADED_SERVER) +  findPicUrl ;	
	}
	
	public Long getId() {
		return dcAuctionItemDO.getId();
	}
	
	public void setId(Long id) {
		dcAuctionItemDO.setId(id);
	}
	
	public Long getItemId() {
		return dcAuctionItemDO.getItemId();
	}
	
	public void setItemId(Long itemId) {
		dcAuctionItemDO.setItemId(itemId);
	}
	
	public String getItemTitle() {
		return dcAuctionItemDO.getItemTitle();
	}
	
	public void setItemTitle(String itemTitle) {
		dcAuctionItemDO.setItemTitle(itemTitle);
	}
	
	public BigDecimal getItemPrice() {
		return dcAuctionItemDO.getItemPrice();
	}
	
	public void setItemPrice(BigDecimal itemPrice) {
		dcAuctionItemDO.setItemPrice(itemPrice);
	}
	
	public Integer getBaseIntegral() {
		return dcAuctionItemDO.getBaseIntegral();
	}
	
	public void setBaseIntegral(Integer baseIntegral) {
		dcAuctionItemDO.setBaseIntegral(baseIntegral);
	}
	
	public Integer getIntegralPer() {
		return dcAuctionItemDO.getIntegralPer();
	}

	public void setIntegralPer(Integer integralPer) {
		dcAuctionItemDO.setIntegralPer(integralPer);
	}
	
	public Date getGmtStart() {
		return dcAuctionItemDO.getGmtStart();
	}
	
	public void setGmtStart(Date gmtStart) {
		dcAuctionItemDO.setGmtStart(gmtStart);
	}
	
	public Date getGmtEnd() {
		return dcAuctionItemDO.getGmtEnd();
	}
	
	public String getGmtEndFmt() {
		return DateTool.defaultFormat(dcAuctionItemDO.getGmtEnd());
	}
	
	public void setGmtEnd(Date gmtEnd) {
		dcAuctionItemDO.setGmtEnd(gmtEnd);
	}
	
	public Integer getBidIntegral() {
		Integer integral = dcAuctionItemDO.getBidIntegral();
		return integral == null? getBaseIntegral(): integral;
	}
	
	public void setBidIntegral(Integer bidIntegral) {
		dcAuctionItemDO.setBidIntegral(bidIntegral);
	}
	
	public Integer getNextBidIntegral() {
		if (getBidIntegral() != null) {
			return getBidIntegral() + getIntegralPer();
		}
		return getBaseIntegral();
	}
	
	public Long getBidUserId() {
		Long userId = dcAuctionItemDO.getBidUserId();
		return userId == null? 10001l: userId;
	}
	
	public void setBidUserId(Long bidUserId) {
		dcAuctionItemDO.setBidUserId(bidUserId);
	}
	
	public String getBidUserNick() {
		String userNick = dcAuctionItemDO.getBidUserNick();
		return userNick == null? "豆花": userNick;
	}
	
	public void setBidUserNick(String bidUserNick) {
		dcAuctionItemDO.setBidUserNick(bidUserNick);
	}

	public Date getGmtModified() {
		return dcAuctionItemDO.getGmtModified();
	}

	public void setGmtModified(Date gmtModified) {
		dcAuctionItemDO.setGmtModified(gmtModified);
	}

	public Date getGmtCreate() {
		return dcAuctionItemDO.getGmtCreate();
	}

	public void setGmtCreate(Date gmtCreate) {
		dcAuctionItemDO.setGmtCreate(gmtCreate);
	}
	
	public DcPromScheduleEnum getStatus() {
		return status;
	}
	
	
	public String getReviewStatus() {
		return dcAuctionItemDO.getReviewStatus();
	}

	public void setReviewStatus(String reviewStatus) {
		dcAuctionItemDO.setReviewStatus(reviewStatus);
	}
	
	public String getPicUrl300x300() {
		return getPicUrl(0, PictureSizeEnums._300x300.getName());
	}
	
	public String getUserAvatar50x50() {
		return DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER)
		  + PictureUtils.findPic(AvatarUtils.buildAvatarPath(getBidUserId()), PictureSizeEnums._50x50);
	}
	
    public void setBidHistories(List<DcMessageDTO> bidHistories) {
		this.bidHistories = bidHistories;
	}
	
	public List<DcMessageDTO> getBidHistories() {
		return this.bidHistories == null? new ArrayList<DcMessageDTO>(): this.bidHistories;
	}
	
	public Long getCountdownTimeStamp() {
		if (status == DcPromScheduleEnum.ONGOING) {
			return getGmtEnd().getTime() - System.currentTimeMillis();
		} else if (status == DcPromScheduleEnum.FUTURE) {
			return getGmtStart().getTime() - System.currentTimeMillis();
		} else {
			return 0l;
		}
	}
	
	/**
	 * 判断出价是否合理.
	 * @param bidIntegral
	 * @return
	 */
	public boolean isBidValid(int bidIntegral) {
		return bidIntegral > getBaseIntegral() &&
				((bidIntegral - getBidIntegral()) % getIntegralPer() == 0);
	}
}