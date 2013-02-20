package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.DdzBrandPartnerDAO;
import com.doucome.corner.biz.dal.dataobject.DdzBrandPartnerDO;

public class IBatisDdzBrandPartnerDAO extends SqlMapClientDaoSupport implements DdzBrandPartnerDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzBrandPartnerDO> queryBrandPartner() {
		return getSqlMapClientTemplate().queryForList("ddzBrandPartner.queryBrandPartner") ;
	}

	@Override
	public DdzBrandPartnerDO queryBrandPartnerBySid(String sid) {
		return (DdzBrandPartnerDO)getSqlMapClientTemplate().queryForObject("ddzBrandPartner.queryBrandPartnerBySid", sid) ;
	}

}
