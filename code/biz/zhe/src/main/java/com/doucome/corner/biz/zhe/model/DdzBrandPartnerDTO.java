package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.model.URLModel;
import com.doucome.corner.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.biz.dal.dataobject.DdzBrandPartnerDO;

public class DdzBrandPartnerDTO {

	private DdzBrandPartnerDO partner ;
	
	private URLModel taokeUrl  ;
	
	
	
	public DdzBrandPartnerDTO(DdzBrandPartnerDO partner){
		if(partner == null){
			partner = new DdzBrandPartnerDO() ;
		}
		this.partner = partner ;
		if(StringUtils.isNotBlank(partner.getClickUrl())){
	
			taokeUrl = HttpUrlHelper.parseURL(partner.getClickUrl()) ;
		}
	}
	
	/**
	 * -------------------------------------------------------------------------------------------------------------------
	 * @return
	 */
	
	public Integer getId() {
		return partner.getId();
	}

	public URLModel getTaokeUrl() {
		return taokeUrl;
	}

	public void setTaokeUrl(URLModel taokeUrl) {
		this.taokeUrl = taokeUrl;
	}

	public String getSid() {
		return partner.getSid();
	}

	public String getShopTitle() {
		return partner.getShopTitle();
	}

	public BigDecimal getCommissionRate() {
		return partner.getCommissionRate();
	}

	public String getClickUrl() {
		return partner.getClickUrl();
	}

	public Date getGmtCreate() {
		return partner.getGmtCreate();
	}

	

	public DdzBrandPartnerDO getPartner() {
		return partner;
	}

	public void setPartner(DdzBrandPartnerDO partner) {
		this.partner = partner;
	}


	
	
}
