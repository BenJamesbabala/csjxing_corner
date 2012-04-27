package com.doucome.corner.biz.dal.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.DdzTransferUrlDAO;
import com.doucome.corner.biz.dal.dataobject.DdzTransferUrlDO;

public class IBatisDdzTransferUrlDAO extends SqlMapClientDaoSupport implements DdzTransferUrlDAO{

	@Override
	public long insertTransferUrl(DdzTransferUrlDO transferUrl) {
		return (Long)getSqlMapClientTemplate().insert("ddzTransferUrl.insertTransferUrl", transferUrl) ;
	}

}
