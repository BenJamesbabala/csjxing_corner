package com.doucome.corner.biz.dcome.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils.BitEnums;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcPromotionItemNewGuideEnums;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.model.util.DcPromotionItemUtils;

@SuppressWarnings("serial")
public class DcPromotionItemDTO extends AbstractModel {
	
	private DcPromotionItemDO promotionItem ;
	
	private DcItemDTO item ;
	
	private Integer rank;
	
	private Integer userIntegral;
	
	public DcPromotionItemDTO(DcPromotionItemDO promotionItem) {
		if(promotionItem == null){
			promotionItem = new DcPromotionItemDO() ;
		}
		this.promotionItem = promotionItem ;
	}
	
	/**
	 * absolue path
	 * @return
	 */
	public String getAvatarAbsoluteUrl30x30(){
		return DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER) + PictureUtils.findPic(AvatarUtils.buildAvatarPath(getUserId()), PictureSizeEnums._30x30);
	}
	
	public DcPromotionItemDO getPromItem() {
		return this.promotionItem;
	}
	
	public int getCanDrawCountByHour(int hour){
		return DcPromotionItemUtils.getCanDrawByHour(this, hour) ;
	}
	
	public int getCanDrawCount(){
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ;
		return getCanDrawCountByHour(hour) ;
	}
	
	/**
	 * 下一个可以领取愿望值剩余时间（单位秒）
	 * @return -1 下一个时间点未知
	 */
	public int getNextDrawRemainSeconds(){
		return DcPromotionItemUtils.getNextDrawRemainSeconds() ;
	}
	
	/**
	 * 是否已经引导过
	 * @param guideName
	 * @return
	 */
	public boolean isGuided(String guideName){
		
		DcPromotionItemNewGuideEnums guideEnums = DcPromotionItemNewGuideEnums.getInstanceByName(guideName) ;
		if(guideEnums == DcPromotionItemNewGuideEnums.UNKNOWN){
			return false ;
		}
		
		long guideValue = NumberUtils.parseLong(getNewGuide()) ;
		
		int index = guideEnums.getIndex() ;
		
		BitEnums bit = BitOperateUtils.getBit(guideValue, index) ;
		
		return bit.toBoolean() ;
		
	}
	
	public String getGuideJson(String guideNames){
		List<String> guideNameList = ArrayStringUtils.toList(guideNames) ;
		Map<String,Boolean> guideMap = new HashMap<String,Boolean>() ;
		if(CollectionUtils.isNotEmpty(guideNameList)){
			for(String guideName : guideNameList){
				if(StringUtils.isNotBlank(guideName)) {
					boolean isGuided = isGuided(guideName) ;
					if(isGuided){
						guideMap.put(guideName, Boolean.TRUE) ;
					}
				}
			}
		}
		return StringUtils.trim(JacksonHelper.toJSON(guideMap)) ;
	}
	
	/**
	 * ----------------------------------------------------------------------------------------------------------------
	 */
	
	public DcItemDTO getItem() {
		return item;
	}

	public void setItem(DcItemDTO item) {
		this.item = item;
	}

	public Long getId() {
		return promotionItem.getId();
	}
	
	
	public Long getPromotionId() {
		return promotionItem.getPromotionId();
	}
	
	
	public Long getItemId() {
		return promotionItem.getItemId();
	}
	

	public Long getUserId() {
		return promotionItem.getUserId();
	}
	
	public String getShareStatus() {
		return promotionItem.getShareStatus();
	}
	
	public boolean isShared() {
		return DcShareStatusEnum.YES.getValue().equals(getShareStatus());
	}
	
	public void setShareStatus(String shareStatus) {
		this.promotionItem.setShareStatus(shareStatus);
	}
	
	public Date getGmtCreate() {
		return promotionItem.getGmtCreate();
	}

	public Integer getRateCount() {
		return promotionItem.getRateCount();
	}

	public Date getGmtModified() {
		return promotionItem.getGmtModified();
	}

	public String getUserNick(){
		return promotionItem.getUserNick() ;
	}
	
	public Integer getHour8() {
		return promotionItem.getHour8();
	}

	public Integer getHour9() {
		return promotionItem.getHour9();
	}

	public Integer getHour10() {
		return promotionItem.getHour10();
	}

	public Integer getHour11() {
		return promotionItem.getHour11();
	}

	public Integer getHour12() {
		return promotionItem.getHour12();
	}

	public Integer getHour13() {
		return promotionItem.getHour13();
	}

	public Integer getHour14() {
		return promotionItem.getHour14();
	}

	public Integer getHour15() {
		return promotionItem.getHour15();
	}

	public Integer getHour16() {
		return promotionItem.getHour16();
	}
	
	public Integer getHour17() {
		return promotionItem.getHour17();
	}

	public Integer getHour18() {
		return promotionItem.getHour18();
	}

	public Integer getHour19() {
		return promotionItem.getHour19();
	}

	public Integer getHour20() {
		return promotionItem.getHour20();
	}

	public Integer getHour21() {
		return promotionItem.getHour21();
	}

	public Integer getHour22() {
		return promotionItem.getHour22();
	}
	
	public Long getNewGuide() {
		return promotionItem.getNewGuide();
	}
	
	public String getItemTitle() {
		return item ==null? "": item.getItemTitle();
	}
	
	public String getItemPriceFmt() {
		return item ==null? "": item.getItemPriceFmt();
	}
	
	public String getItemPicUrl160x160() {
		return item ==null? "": item.getPicUrl160x160();
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public Integer getRank() {
		return this.rank;
	}

	public Integer getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}
}
