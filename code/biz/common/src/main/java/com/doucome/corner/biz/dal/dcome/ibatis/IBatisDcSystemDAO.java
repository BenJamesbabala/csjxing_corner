package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSystemDO;
import com.doucome.corner.biz.dal.dcome.DcSystemDAO;

public class IBatisDcSystemDAO extends SqlMapClientDaoSupport implements DcSystemDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<DcSystemDO> querySystems() {
		return getSqlMapClientTemplate().queryForList("DcSystem.querySystems") ;
	}

	@Override
	public DcSystemDO querySystemById(Long id) {
		return (DcSystemDO)getSqlMapClientTemplate().queryForObject("DcSystem.querySystemById" , id) ;
	}

}
