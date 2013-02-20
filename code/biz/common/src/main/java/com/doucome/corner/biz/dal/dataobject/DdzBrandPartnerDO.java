package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

public class DdzBrandPartnerDO extends AbstractModel{

	private Integer id ;
	
	/**
	 * µêÆÌID
	 */
	private String sid ;
	
	/**
	 * µêÆÌtitle
	 */
	private String shopTitle ;
	
	/**
	 * Ó¶½ð±ÈÀý
	 */
	private BigDecimal commissionRate ;
	
	/**
	 * µã»÷URL
	 */
	private String clickUrl ;
	
	private Date gmtCreate ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getShopTitle() {
		return shopTitle;
	}

	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
