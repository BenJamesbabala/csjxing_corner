package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRateDetailDO;
import com.doucome.corner.biz.dal.dcome.DcRateDetailDAO;

public class IBatisDcRateDetailDAO extends SqlMapClientDaoSupport implements DcRateDetailDAO {

	@Override
	public Long insertRate(DcRateDetailDO rate) {
		return (Long)getSqlMapClientTemplate().insert("DcRateDetail.insertRate" , rate) ;
	}

	@Override
	public DcRateDetailDO queryRateByItemAndRateuser(long itemId,long rateUserId) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("itemId", itemId) ;
		condition.put("rateUserId", rateUserId) ;
		return (DcRateDetailDO)getSqlMapClientTemplate().queryForObject("DcRateDetail.queryRateByItemAndRateuser" ,condition) ;
	}

}
