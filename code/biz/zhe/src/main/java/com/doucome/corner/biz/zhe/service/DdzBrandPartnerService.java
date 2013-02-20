package com.doucome.corner.biz.zhe.service;

import java.util.Map;

import com.doucome.corner.biz.zhe.model.DdzBrandPartnerDTO;

public interface DdzBrandPartnerService {

	/**
	 * 获取品牌合作
	 * @return
	 */
	Map<String,DdzBrandPartnerDTO> getBrandPartnerMap() ;
	
	DdzBrandPartnerDTO getBrandPartnerBySid(String sid) ;
	
}
