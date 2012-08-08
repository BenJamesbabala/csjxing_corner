package com.doucome.corner.biz.dal.condition.dcome;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 豆蔻评论查询条件
 * @author shenjia.caosj 2012-7-21
 *
 */
public class DcCommentSearchCondition {

	private String keywords ;
	
	private Date gmtCreateStart ;
	
	private Date gmtCreateEnd ;
	
	private Long userId ;
	
	private String status ;
	
	private Long itemId ;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("itemId", itemId) ;
		condition.put("status", status);
		condition.put("keywords", keywords);
		condition.put("userId", userId);
		condition.put("gmtCreateStart", gmtCreateStart) ;
		condition.put("gmtCreateEnd", gmtCreateEnd) ;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	
}
