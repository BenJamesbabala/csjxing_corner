package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzJfbSettleRecordDAO;
import com.doucome.corner.biz.dal.condition.DdzJfbSettleRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzJfbSettleRecordDO;

public class IBatisDdzJfbSettleRecordDAO extends SqlMapClientDaoSupport implements DdzJfbSettleRecordDAO {

	@Override
	public long insertRecord(DdzJfbSettleRecordDO settleRecord) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("ddzJfbSettleRecord.insertRecord" , settleRecord)) ;
	}

	@Override
	public DdzJfbSettleRecordDO queryRecordById(Long id) {
		return (DdzJfbSettleRecordDO)getSqlMapClientTemplate().queryForObject("ddzJfbSettleRecord.queryRecordById", id) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DdzJfbSettleRecordDO> queryRecordsWithPagination(DdzJfbSettleRecordSearchCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap() ;
		params.put("start", start - 1);
		params.put("size", size);
		return getSqlMapClientTemplate().queryForList("ddzJfbSettleRecord.queryRecordsWithPagination", params) ;
	}

	@Override
	public int countRecordsWithPagination(DdzJfbSettleRecordSearchCondition condition) {
		Map<String, Object> params = condition.toMap() ;
		
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("ddzJfbSettleRecord.countRecordsWithPagination", params));
	}

	@Override
	public int updateSuccessInfoById(Long id, DdzJfbSettleRecordDO settleRecord) {
		settleRecord.setId(id) ;
		return getSqlMapClientTemplate().update("ddzJfbSettleRecord.updateSuccessInfoById" , settleRecord) ;
	}

	

}
