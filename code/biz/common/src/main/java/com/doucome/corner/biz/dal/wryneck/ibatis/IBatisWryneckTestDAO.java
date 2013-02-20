package com.doucome.corner.biz.dal.wryneck.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.wryneck.WryneckTestDAO;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckTestDO;

public class IBatisWryneckTestDAO extends SqlMapClientDaoSupport implements WryneckTestDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<WryneckTestDO> queryTests() {
		return getSqlMapClientTemplate().queryForList("WryneckTest.queryTests") ;
	}

}
