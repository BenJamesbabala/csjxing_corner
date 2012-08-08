package com.doucome.corner.biz.dal.dataobject.dcome;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 豆蔻商品
 * @author shenjia.caosj 2012-7-21
 *
 */
public class DcItemDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 外部商品ID，如淘宝的itemId
	 */
	private String nativeId ;
	
	/**
	 * 外部商品类目，如淘宝的categoryId
	 */
	private Long nativeCategoryId ;
	
	/**
	 * 商品标题
	 */
	private String itemTitle ;
	
	/**
	 * 描述信息
	 */
	private String itemDesc ;
	
	/**
	 * 价格
	 */
	private BigDecimal itemPrice ;
	
	/**
	 * 商品来源
	 */
	private String source ;
	
	/**
	 * 商品原始URL
	 */
	private String srcUrl ;
	
	/**
	 * 图片描述地址，多个“,”隔开
	 */
	private String picUrls ;          
	
	/**
	 * 点击URL（如生成的淘宝客URL）
	 */
	private String clickUrl ;
	
	/**
	 * 喜欢
	 */
	private Integer loves ;
	
	/**
	 * 售出数量
	 */
	private Integer sells ;
	
	/**
	 * 评论数量
	 */
	private Integer comments ;
	
	/**
	 * 类目
	 */
	private Long categoryId ;
		
	private Date gmtModified ;
	
	private Date gmtCreate ;
	
	/**
	 * 商品更新时间
	 */
	private Date gmtItemUpdate ;
	/**
	 * 商品状态，详见DcItemStatusEnum.
	 */
	private String status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNativeId() {
		return nativeId;
	}

	public void setNativeId(String nativeId) {
		this.nativeId = nativeId;
	}

	public Long getNativeCategoryId() {
		return nativeCategoryId;
	}

	public void setNativeCategoryId(Long nativeCategoryId) {
		this.nativeCategoryId = nativeCategoryId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}
	
	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	

	public Integer getLoves() {
		return loves;
	}

	public void setLoves(Integer loves) {
		this.loves = loves;
	}

	public Integer getSells() {
		return sells;
	}

	public void setSells(Integer sells) {
		this.sells = sells;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtItemUpdate() {
		return gmtItemUpdate;
	}

	public void setGmtItemUpdate(Date gmtItemUpdate) {
		this.gmtItemUpdate = gmtItemUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
