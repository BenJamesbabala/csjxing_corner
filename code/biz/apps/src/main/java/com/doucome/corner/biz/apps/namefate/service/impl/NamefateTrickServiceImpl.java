package com.doucome.corner.biz.apps.namefate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.namefate.model.NamefateTrickDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateTrickService;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.namefate.NamefateTrickDAO;
import com.doucome.corner.biz.dal.namefate.condition.NamefateTrickSearchCondition;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateTrickDO;

public class NamefateTrickServiceImpl implements NamefateTrickService {

	@Autowired
	private NamefateTrickDAO namefateTrickDAO ;
	
	@Override
	public long createTrick(NamefateTrickDO trick) {
		return namefateTrickDAO.insertTrick(trick) ;
	}

	@Override
	public QueryResult<NamefateTrickDTO> getTricksWithPagination(NamefateTrickSearchCondition condition, Pagination pagination) {
		int count = namefateTrickDAO.countTricksWithPagination(condition) ;
		
		List<NamefateTrickDTO> dtoList = new ArrayList<NamefateTrickDTO>() ;
		
		if(count <= 0){
			return new QueryResult<NamefateTrickDTO>(dtoList, pagination, 0) ;
		}
		
		List<NamefateTrickDO> doList = namefateTrickDAO.queryTricksWithPagination(condition, pagination.getStart(), pagination.getSize()) ;
		
		if(CollectionUtils.isNotEmpty(doList)){
			for(NamefateTrickDO item : doList){
				dtoList.add(new NamefateTrickDTO(item)) ;
			}
		}
		
		return new QueryResult<NamefateTrickDTO>(dtoList, pagination, count ) ;
	}

	@Override
	public NamefateTrickDTO getTrickById(Long trickId) {
		NamefateTrickDO trick = namefateTrickDAO.queryTrickById(trickId) ;
		if(trick == null){
			return null ;
		}
		return new NamefateTrickDTO(trick) ;
	}

}
