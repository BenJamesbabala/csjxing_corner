package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserIntegralRecordSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;
import com.doucome.corner.biz.dal.dcome.DcUserIntegralRecordDAO;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.model.DcUserIntegralRecordDTO;
import com.doucome.corner.biz.dcome.service.DcUserIntegralRecordService;

public class DcUserIntegralRecordServiceImpl implements DcUserIntegralRecordService {

	@Autowired
	private DcUserIntegralRecordDAO dcUserIntegralRecordDAO ;
	
	@Override
	public long createRecord(DcUserIntegralRecordDO record) {
		return dcUserIntegralRecordDAO.insertRecord(record) ;
	}

	@Override
	public QueryResult<DcUserIntegralRecordDTO> getRecordsWithPagination(DcUserIntegralRecordSearchCondition condition, Pagination pagination) {
		
		int count = dcUserIntegralRecordDAO.countRecordsWithPagination(condition) ;
		
		List<DcUserIntegralRecordDTO> dtoList = new ArrayList<DcUserIntegralRecordDTO>() ;
		
		if(count <= 0){
			return new QueryResult<DcUserIntegralRecordDTO>(dtoList, pagination, 0) ;
		}
		
		List<DcUserIntegralRecordDO> doList = dcUserIntegralRecordDAO.queryRecordsWithPagination(condition, pagination.getStart(), pagination.getSize()) ;
		
		if(CollectionUtils.isNotEmpty(doList)){
			for(DcUserIntegralRecordDO item : doList){
				dtoList.add(new DcUserIntegralRecordDTO(item)) ;
			}
		}
		
		return new QueryResult<DcUserIntegralRecordDTO>(dtoList, pagination, count ) ;
	}

	@Override
	public int updateStatusBySettleIdList(DcExchangeApproveStatusEnums toStatus,Long[] settleIdList) {
		return dcUserIntegralRecordDAO.updateStatusBySettleIdList(toStatus.getValue() , settleIdList) ;
	}

	@Override
	public int updateStatusAndUserMemoByExApproveId(long exApproveId,DcExchangeApproveStatusEnums toStatus, String userMemo) {
		return dcUserIntegralRecordDAO.updateStatusAndUserMemoByExApproveId(exApproveId , toStatus.getValue() , userMemo) ;
	}

	@Override
	public DcUserIntegralRecordDTO getRecordByReportId(long reportId) {
		DcUserIntegralRecordDO recordDO = dcUserIntegralRecordDAO.queryRecordByReportId(reportId) ;
		if(recordDO == null){
			return null ;
		}
		return new DcUserIntegralRecordDTO(recordDO) ;
	}

}
