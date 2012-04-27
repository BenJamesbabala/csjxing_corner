package com.doucome.corner.biz.dal.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.DdzSearchLogDAO;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;

public class IBatisDdzSearchLogDAO extends SqlMapClientDaoSupport implements DdzSearchLogDAO{

	@Override
	public long insertLog(DdzSearchLogDO searchLog) {
		return (Long)getSqlMapClientTemplate().insert("ddzSearchLog.insertLog" ,searchLog) ;
	}

}
