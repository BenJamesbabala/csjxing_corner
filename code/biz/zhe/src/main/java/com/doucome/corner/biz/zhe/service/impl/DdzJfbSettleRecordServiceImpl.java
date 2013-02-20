package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.DdzJfbSettleRecordDAO;
import com.doucome.corner.biz.dal.condition.DdzJfbSettleRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzJfbSettleRecordDO;
import com.doucome.corner.biz.zhe.exception.DdzDuplicateKeyException;
import com.doucome.corner.biz.zhe.service.DdzJfbSettleRecordService;

public class DdzJfbSettleRecordServiceImpl implements DdzJfbSettleRecordService {

	@Autowired
	private DdzJfbSettleRecordDAO ddzJfbSettleRecordDAO ;
	
	@Override
	public long createRecord(DdzJfbSettleRecordDO settleRecord) {
		return ddzJfbSettleRecordDAO.insertRecord(settleRecord) ;
	}
	
	@Override
	public DdzJfbSettleRecordDO getRecordById(Long id) {
		return ddzJfbSettleRecordDAO.queryRecordById(id) ;
	}

	@Override
	public QueryResult<DdzJfbSettleRecordDO> getRecordsWithPagination(DdzJfbSettleRecordSearchCondition condition, Pagination pagination) {
		int totalRecords = ddzJfbSettleRecordDAO.countRecordsWithPagination(condition) ;
		if(totalRecords <= 0){
			return new QueryResult<DdzJfbSettleRecordDO>(new ArrayList<DdzJfbSettleRecordDO>() , pagination , totalRecords ) ;
		}
		List<DdzJfbSettleRecordDO> doList = ddzJfbSettleRecordDAO.queryRecordsWithPagination(condition, pagination.getStart(), pagination.getSize()) ;
		return new QueryResult<DdzJfbSettleRecordDO>(doList, pagination, totalRecords) ;
	}

	@Override
	public int updateSuccessInfoById(Long id, DdzJfbSettleRecordDO settleRecord) throws DdzDuplicateKeyException  {
		try {
			return ddzJfbSettleRecordDAO.updateSuccessInfoById(id, settleRecord) ;
		} catch (DuplicateKeyException e){
			throw new DdzDuplicateKeyException(e) ;
		}
	}

	

}
