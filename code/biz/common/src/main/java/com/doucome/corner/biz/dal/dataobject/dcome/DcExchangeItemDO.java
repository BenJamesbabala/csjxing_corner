package com.doucome.corner.biz.dal.dataobject.dcome;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 积分兑换商品DO
 * @author ze2200
 *
 */
public class DcExchangeItemDO extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long itemId;
	
	private String itemTitle;
	
	private BigDecimal itemPrice;
	
	private String itemPictures;
	
	private Integer exIntegral;
	
	private Integer exCount;
	
	private String itemType;
	
	private Integer exSuccCount = 0;
	
	/**
	 * 兑换类型
	 */
	private String exType ;
	
	/**
	 * 需要的字段
	 */
	private String requireFields ;
	
	private Long userId;

	private Date gmtModified;
	
	private Date gmtCreate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemPictures() {
		return itemPictures;
	}

	public void setItemPictures(String itemPictures) {
		this.itemPictures = itemPictures;
	}

	public Integer getExIntegral() {
		return exIntegral;
	}

	public void setExIntegral(Integer exIntegral) {
		this.exIntegral = exIntegral;
	}
	
	public void setExCount(Integer exCount) {
		this.exCount = exCount;
	}
	
	
	
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getExCount() {
		return this.exCount;
	}

	public Integer getExSuccCount() {
		return exSuccCount;
	}

	public void setExSuccCount(Integer exSuccCount) {
		this.exSuccCount = exSuccCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getExType() {
		return exType;
	}

	public void setExType(String exType) {
		this.exType = exType;
	}

	public String getRequireFields() {
		return requireFields;
	}

	public void setRequireFields(String requireFields) {
		this.requireFields = requireFields;
	}
	
}