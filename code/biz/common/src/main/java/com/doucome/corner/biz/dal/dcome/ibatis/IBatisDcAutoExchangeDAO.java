package com.doucome.corner.biz.dal.dcome.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcAutoExchangeDO;
import com.doucome.corner.biz.dal.dcome.DcAutoExchangeDAO;

public class IBatisDcAutoExchangeDAO extends SqlMapClientDaoSupport implements DcAutoExchangeDAO {

	@Override
	public Long insertAutoExchange(DcAutoExchangeDO autoExchangeDO) {
		return (Long)getSqlMapClientTemplate().insert("DcAutoExchange.insertAutoExchange", autoExchangeDO);
	}
}
