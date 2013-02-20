package com.doucome.corner.biz.zhe.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzBrandPartnerDAO;
import com.doucome.corner.biz.dal.dataobject.DdzBrandPartnerDO;
import com.doucome.corner.biz.zhe.model.DdzBrandPartnerDTO;
import com.doucome.corner.biz.zhe.service.DdzBrandPartnerService;

public class DdzBrandPartnerServiceImpl implements DdzBrandPartnerService{

	@Autowired
	private DdzBrandPartnerDAO ddzBrandPartnerDAO ;
	
	@Override
	public Map<String,DdzBrandPartnerDTO> getBrandPartnerMap() {
		List<DdzBrandPartnerDO> partnerList = ddzBrandPartnerDAO.queryBrandPartner() ;
		Map<String,DdzBrandPartnerDTO> map = new HashMap<String,DdzBrandPartnerDTO>() ;
		for(Iterator<DdzBrandPartnerDO> i = partnerList.iterator();i.hasNext();){
			DdzBrandPartnerDO part = i.next() ;
			map.put(part.getSid(), new DdzBrandPartnerDTO(part));
		}
		return map ;
	}

	@Override
	public DdzBrandPartnerDTO getBrandPartnerBySid(String sid) {
		DdzBrandPartnerDO p = ddzBrandPartnerDAO.queryBrandPartnerBySid(sid) ;
		if(p == null){
			return null ;
		}
		return new DdzBrandPartnerDTO(p) ;
	}

}
