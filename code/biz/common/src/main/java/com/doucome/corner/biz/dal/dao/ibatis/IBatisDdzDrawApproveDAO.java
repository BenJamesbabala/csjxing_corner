package com.doucome.corner.biz.dal.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzDrawApproveDAO;
import com.doucome.corner.biz.dal.dataobject.DdzDrawApproveDO;


public class IBatisDdzDrawApproveDAO extends SqlMapClientDaoSupport implements DdzDrawApproveDAO {

	@Override
	public long insertApprove(DdzDrawApproveDO approve) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DdzDrawApprove.insertApprove" , approve)) ;
	}

}
