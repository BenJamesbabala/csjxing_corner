package com.doucome.corner.biz.dal.condition.dcome;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 豆蔻商品查询条件
 * @author shenjia.caosj 2012-7-21
 *
 */
public class DcItemSearchCondition {
	
	private Long categoryId ;
	
	private List<Long> categoryIds ;
	
	private String keywords ;
	
	private Date gmtCreateStart ;
	
	private Date gmtCreateEnd ;
	
	private Long itemId ;
	
	private String itemStatus;
	
	/**
	 * 排除的ID
	 */
	private List<Long> exclusionIds ;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("categoryId", categoryId);
		condition.put("categoryIds", categoryIds) ;
		condition.put("keywords", keywords);
		condition.put("itemId", itemId);
		condition.put("gmtCreateStart", gmtCreateStart) ;
		condition.put("gmtCreateEnd", gmtCreateEnd) ;
		condition.put("exclusionIds", exclusionIds) ;
		condition.put("itemStatus", itemStatus);
		return condition ;
	}
	
	public List<Long> getExclusionIds() {
		return exclusionIds;
	}

	public void setExclusionIds(List<Long> exclusionIds) {
		this.exclusionIds = exclusionIds;
	}

	public String getGmtCreateStartFormat(){
		return new SimpleDateFormat("yyyy-MM-dd").format(gmtCreateStart) ;
	}
	
	public String getGmtCreateEndFormat(){
		return new SimpleDateFormat("yyyy-MM-dd").format(gmtCreateEnd) ;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Long getCategoryId() {
		return categoryId;
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
}
