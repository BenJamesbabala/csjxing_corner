package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 点点折搜索框查询日志
 * 
 * @author shenjia.caosj 2012-3-6
 * 
 */
public class DdzSearchLogDO extends AbstractModel {

	private long id;

	private String uid;

	private String alipayId;

	private String searchBrief;

	private String searchWay;

	private BigDecimal commission;

	private BigDecimal commissionRate;

	private BigDecimal price;
	
	private String searchTitle ;

	private Date gmtCreate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	public String getSearchBrief() {
		return searchBrief;
	}

	public void setSearchBrief(String searchBrief) {
		this.searchBrief = searchBrief;
	}

	public String getSearchWay() {
		return searchWay;
	}

	public void setSearchWay(String searchWay) {
		this.searchWay = searchWay;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	
	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

}
