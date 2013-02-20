package com.doucome.corner.biz.dcome.model;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSearchLogDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 
 * @author ze2200
 *
 */
public class DcSearchLogDTO extends AbstractModel implements Comparable<DcSearchLogDTO> {
	
	private static final long serialVersionUID = 1L;
	
	private DcSearchLogDO searchLog;
	
	private int searchCount = 1;
	
	public DcSearchLogDTO() {
		this(null);
	}
	
	public DcSearchLogDTO(DcSearchLogDO searchLog) {
		if (searchLog == null) {
			searchLog = new DcSearchLogDO();
			searchCount = 0;
		}
		this.searchLog = searchLog;
	}
	
	public Long getId() {
		return searchLog.getId();
	}

	public Long getUserId() {
		return searchLog.getUserId();
	}

	public void setUserId(Long userId) {
		searchLog.setUserId(userId);
	}
	
	public BigDecimal getCommissionRate() {
		return searchLog.getCommissionRate();
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		searchLog.setCommissionRate(commissionRate);
	}


	public String getNativeId() {
		return searchLog.getNativeId();
	}

	public void setNativeId(String nativeId) {
		searchLog.setNativeId(nativeId);
	}
	
	public String getTitle() {
		return searchLog.getTitle();
	}

	public void setTitle(String title) {
		if (title != null &&title.length() > 20) {
			title = title.substring(0, 19);
		}
		searchLog.setTitle(title);
	}

	public String getPicture() {
		return searchLog.getPicture();
	}

	public void setPicture(String picture) {
		if (picture != null && picture.length() > 150) {
			picture = picture.substring(0, 149);
		}
		searchLog.setPicture(picture);
	}
	
	public BigDecimal getPrice() {
		return searchLog.getPrice();
	}

	public void setPrice(BigDecimal price) {
		searchLog.setPrice(price);
	}

	public Date getGmtCreate() {
		return searchLog.getGmtCreate();
	}

	public void setGmtCreate(Date gmtCreate) {
		searchLog.setGmtCreate(gmtCreate);
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
	
	public DcSearchLogDO toDO() {
		return this.searchLog;
	}

	@Override
	public int compareTo(DcSearchLogDTO arg0) {
		int a = getUserId().compareTo(arg0.getUserId());
		if (a == 0) {
			return getNativeId().compareTo(arg0.getNativeId());
		}
		return a;
	}
}
