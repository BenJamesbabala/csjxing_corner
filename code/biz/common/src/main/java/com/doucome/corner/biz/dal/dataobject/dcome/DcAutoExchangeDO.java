package com.doucome.corner.biz.dal.dataobject.dcome;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ∂πﬁ¢…Ã∆∑
 * @author langben 2012-7-21
 *
 */
public class DcAutoExchangeDO extends AbstractModel {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nativeId;
	
	private String itemSize;
	
	private String itemColor;
	
	private BigDecimal price;
	
	private BigDecimal postalFee;
	
	private String memo;
	
	private Long userId;
	
	private String userNick;
	
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNativeId() {
		return nativeId;
	}

	public void setNativeId(String nativeId) {
		this.nativeId = nativeId;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public String getItemColor() {
		return itemColor;
	}

	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}

	public BigDecimal getPostalFee() {
		return postalFee;
	}

	public void setPostalFee(BigDecimal postalFee) {
		this.postalFee = postalFee;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
}
