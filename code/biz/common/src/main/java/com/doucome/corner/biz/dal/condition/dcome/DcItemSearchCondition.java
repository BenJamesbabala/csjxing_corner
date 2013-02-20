package com.doucome.corner.biz.dal.condition.dcome;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.model.OrderAndSortModel;

/**
 * 豆蔻商品查询条件
 * @author langben 2012-7-21
 *
 */
public class DcItemSearchCondition {
	
	private static final String[]  orderOptions = new String[]{"item_price","item_prom_price","recomm_type","postal_enable","commission_rate","taoke_sell_count","gmt_modified","gmt_create","status","gen_way","display_order"} ;
	
	private Long categoryId ;
	
//	private List<Long> categoryIds ;
	
	private String keywords ;
	
	private Date gmtCreateStart ;
	
	private Date gmtCreateEnd ;
	
	private Date gmtModifiedStart ;
	
	private Date gmtModifiedEnd ;
	
	private Long itemId ;
	
	private String nativeId ;
	
	private String itemStatus;
	
	private String genWay ;
	
	private String status ;
	
	/**
	 * 推荐类型
	 */
	private String recommType ;
	/**
	 * 时间戳，用于ugc过滤gmtCreate字段.
	 */
	private Long timeStamp;
	
	private Long creatorUserId ;
	
	private Date gmtItemUpdateStart;
	
	private Date gmtItemUpdateEnd;
	
	private String order;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("categoryId", categoryId);
//		condition.put("categoryIds", categoryIds) ;
		condition.put("keywords", keywords);
		condition.put("itemId", itemId);
		condition.put("nativeId", nativeId) ;
		condition.put("gmtCreateStart", gmtCreateStart) ;
		condition.put("gmtCreateEnd", gmtCreateEnd) ;
		condition.put("gmtModifiedStart", gmtModifiedStart) ;
		condition.put("gmtModifiedEnd", gmtModifiedEnd) ;
		condition.put("itemStatus", itemStatus);
		condition.put("genWay", genWay) ;
		condition.put("status", status) ;
		condition.put("creatorUserId", creatorUserId);
		condition.put("timeStampDate", getTimeStampDate());
		condition.put("gmtItemUpdateStart", gmtItemUpdateStart);
		condition.put("gmtItemUpdateEnd", gmtItemUpdateEnd);
		condition.put("recommType", recommType);
		
		OrderAndSortModel osm = new OrderAndSortModel(this.order, orderOptions) ;
		if(osm.isFormat()){
			condition.put("order", osm.getOrder()) ;
			condition.put("sort", osm.getSort()) ;
		}
		
		return condition ;
	}
	
	public String getGmtCreateStartFormat(){
		if(gmtCreateStart == null){
			return null ;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(gmtCreateStart) ;
	}
	
	public String getGmtCreateEndFormat(){
		if(gmtCreateEnd == null){
			return null ;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(gmtCreateEnd) ;
	}

//	public List<Long> getCategoryIds() {
//		return categoryIds;
//	}

//	public void setCategoryIds(List<Long> categoryIds) {
//		this.categoryIds = categoryIds;
//	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getGenWay() {
		return genWay;
	}

	public void setGenWay(String genWay) {
		this.genWay = genWay;
	}

	public Long getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(Long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = StringUtils.trim(keywords);
	}

	public Date getGmtCreateStart() {
		return gmtCreateStart;
	}

	public void setGmtCreateStart(Date gmtCreateStart) {
		this.gmtCreateStart = gmtCreateStart;
	}

	public Date getGmtCreateEnd() {
		return gmtCreateEnd;
	}

	public void setGmtCreateEnd(Date gmtCreateEnd) {
		this.gmtCreateEnd = gmtCreateEnd;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	public Date getGmtItemUpdateStart() {
		return gmtItemUpdateStart;
	}

	public void setGmtItemUpdateStart(Date gmtItemUpdateStart) {
		this.gmtItemUpdateStart = gmtItemUpdateStart;
	}

	public Date getGmtItemUpdateEnd() {
		return gmtItemUpdateEnd;
	}

	public void setGmtItemUpdateEnd(Date gmtItemUpdateEnd) {
		this.gmtItemUpdateEnd = gmtItemUpdateEnd;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}
	
	public Date getTimeStampDate() {
		if (timeStamp == null || timeStamp <= 0l) {
			return null;
		}
		return new Date(timeStamp);
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getRecommType() {
		return recommType;
	}

	public void setRecommType(String recommType) {
		this.recommType = recommType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Date getGmtModifiedStart() {
		return gmtModifiedStart;
	}

	public void setGmtModifiedStart(Date gmtModifiedStart) {
		this.gmtModifiedStart = gmtModifiedStart;
	}

	public Date getGmtModifiedEnd() {
		return gmtModifiedEnd;
	}

	public void setGmtModifiedEnd(Date gmtModifiedEnd) {
		this.gmtModifiedEnd = gmtModifiedEnd;
	}

	public String getNativeId() {
		return nativeId;
	}

	public void setNativeId(String nativeId) {
		this.nativeId = nativeId;
	}
	
}
