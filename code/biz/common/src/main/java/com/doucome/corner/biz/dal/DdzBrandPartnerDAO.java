package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.DdzBrandPartnerDO;

/**
 * 合作的商铺
 * @author langben 2012-8-11
 *
 */
public interface DdzBrandPartnerDAO {

	List<DdzBrandPartnerDO> queryBrandPartner() ;
	
	DdzBrandPartnerDO queryBrandPartnerBySid(String sid) ;
}
