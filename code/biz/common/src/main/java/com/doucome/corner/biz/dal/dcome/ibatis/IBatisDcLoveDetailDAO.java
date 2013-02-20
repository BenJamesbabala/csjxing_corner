package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcLoveDetailDO;
import com.doucome.corner.biz.dal.dcome.DcLoveDetailDAO;

/**
 * Ï²»¶+1 ÏêÏ¸
 * @author langben 2012-7-22
 *
 */
public class IBatisDcLoveDetailDAO extends SqlMapClientDaoSupport implements DcLoveDetailDAO{

	@Override
	public Long insertDetail(DcLoveDetailDO loveDetail) {
		return (Long)getSqlMapClientTemplate().insert("DcLoveDetail.insertDetail", loveDetail) ;
	}

	@Override
	public DcLoveDetailDO queryDetailByUserAndItem(long userId, long itemId) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("userId", userId) ;
		condition.put("itemId", itemId) ;
		return (DcLoveDetailDO)getSqlMapClientTemplate().queryForObject("DcLoveDetail.queryDetailByUserAndItem",condition);
	}

	
}
