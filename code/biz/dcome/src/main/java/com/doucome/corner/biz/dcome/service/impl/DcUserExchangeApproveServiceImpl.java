package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserExchangeApproveSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserExchangeApproveDO;
import com.doucome.corner.biz.dal.dcome.DcUserExchangeApproveDAO;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.model.DcUserExchangeApproveDTO;
import com.doucome.corner.biz.dcome.service.DcUserExchangeApproveService;

public class DcUserExchangeApproveServiceImpl implements DcUserExchangeApproveService {

	@Autowired
	private DcUserExchangeApproveDAO dcUserExchangeApproveDAO ;
	
	@Override
	public long createExchangeApprove(DcUserExchangeApproveDO exchangeApprove) {
		return dcUserExchangeApproveDAO.insertExchangeApprove(exchangeApprove) ;
	}

	@Override
	public QueryResult<DcUserExchangeApproveDTO> getExchangeApproveWithPagination(DcUserExchangeApproveSearchCondition condition,
			Pagination pagination) {
		int count = dcUserExchangeApproveDAO.countExchangeApproveWithPagination(condition) ;
		
		List<DcUserExchangeApproveDTO> dtoList = new ArrayList<DcUserExchangeApproveDTO>() ;
		
		if(count <= 0){
			return new QueryResult<DcUserExchangeApproveDTO>(dtoList, pagination, 0) ;
		}
		
		List<DcUserExchangeApproveDO> doList = dcUserExchangeApproveDAO.queryExchangeApproveWithPagination(condition, pagination.getStart(), pagination.getSize()) ;
		
		if(CollectionUtils.isNotEmpty(doList)){
			for(DcUserExchangeApproveDO item : doList){
				dtoList.add(new DcUserExchangeApproveDTO(item)) ;
			}
		}
		
		return new QueryResult<DcUserExchangeApproveDTO>(dtoList, pagination, count ) ;
	}

	@Override
	public int updateStatusBySettleIdList(DcExchangeApproveStatusEnums toStatus,Long[] settleIdList) {
		return dcUserExchangeApproveDAO.updateStatusBySettleIdList(toStatus.getValue() , settleIdList) ;
	}

	@Override
	public int updateStatusAndUserMemoById(long id,
			DcExchangeApproveStatusEnums toStatus, String userMemo) {
		return dcUserExchangeApproveDAO.updateStatusAndUserMemoById(id , toStatus.getValue() , userMemo) ;
	}

}
