package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcUserExchangeApproveSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dal.dcome.DcUserExchangeApproveDAO;

public class IBatisDcUserExchangeApproveDAO extends SqlMapClientDaoSupport implements DcUserExchangeApproveDAO {

	@Override
	public long insertExchangeApprove(DcUserExchangeApproveDO exchangeApprove) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DcUserExchangeApprove.insertExchangeApprove" , exchangeApprove)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcUserExchangeApproveDO> queryExchangeApproveWithPagination(DcUserExchangeApproveSearchCondition condition, int start, int size) {
		Map<String,Object> map = condition.toMap() ;
		map.put("start", start - 1);
		map.put("size", size);
		return getSqlMapClientTemplate().queryForList("DcUserExchangeApprove.queryExchangeApproveWithPagination" , map ) ;
	}

	@Override
	public int countExchangeApproveWithPagination(DcUserExchangeApproveSearchCondition condition) {
		Map<String,Object> map = condition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcUserExchangeApprove.countExchangeApproveWithPagination" , map)) ;
	}

	@Override
	public int updateStatusBySettleIdList(String status, Long[] settleIdList) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status) ;
		map.put("settleIdList", settleIdList) ;
		return getSqlMapClientTemplate().update("DcUserExchangeApprove.updateStatusBySettleIdList" , map);
	}

	@Override
	public int updateStatusAndUserMemoById(long id,
			String status, String userMemo) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status) ;
		map.put("id", id) ;
		map.put("userMemo", userMemo) ;
		return getSqlMapClientTemplate().update("DcUserExchangeApprove.updateStatusAndUserMemoById" , map);
	}

}
