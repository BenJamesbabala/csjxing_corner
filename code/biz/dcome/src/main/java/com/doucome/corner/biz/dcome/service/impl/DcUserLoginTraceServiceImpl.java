package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcUserLoginTraceCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserLoginTraceDO;
import com.doucome.corner.biz.dal.dcome.DcUserLoginTraceDAO;
import com.doucome.corner.biz.dcome.model.DcUserLoginTraceDTO;
import com.doucome.corner.biz.dcome.service.DcUserLoginTraceService;

public class DcUserLoginTraceServiceImpl implements DcUserLoginTraceService {

	@Autowired
	private DcUserLoginTraceDAO dcUserLoginTraceDAO ;
	
	@Override
	public long createLoginTrace(DcUserLoginTraceDO loginTrace) {
		return dcUserLoginTraceDAO.insertLoginTrace(loginTrace) ;
	}

	@Override
	public QueryResult<DcUserLoginTraceDTO> getTraceWithPagination(DcUserLoginTraceCondition condition, Pagination pagination) {
		int count = dcUserLoginTraceDAO.countTraceWithPagination(condition) ;
		if(count < 1){
			return new QueryResult<DcUserLoginTraceDTO>(new ArrayList<DcUserLoginTraceDTO>(), pagination, 0) ;
		}
		List<DcUserLoginTraceDO> traceList = dcUserLoginTraceDAO.queryTraceWithPagination(condition, pagination.getStart(), pagination.getSize()) ;
		List<DcUserLoginTraceDTO> dtoList = new ArrayList<DcUserLoginTraceDTO>() ;
		if(CollectionUtils.isNotEmpty(traceList)){
			for(DcUserLoginTraceDO trace : traceList){
				dtoList.add(new DcUserLoginTraceDTO(trace)) ;
			}
		}
		return new QueryResult<DcUserLoginTraceDTO>(dtoList, pagination, count) ;
	}

	

}
