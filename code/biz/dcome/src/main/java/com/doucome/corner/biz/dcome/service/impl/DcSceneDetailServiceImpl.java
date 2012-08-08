package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDetailDO;
import com.doucome.corner.biz.dal.dcome.DcSceneDetailDAO;
import com.doucome.corner.biz.dcome.service.DcSceneDetailService;

public class DcSceneDetailServiceImpl implements DcSceneDetailService {

	@Autowired
	private DcSceneDetailDAO dcSceneDetailDAO ;
	
	@Override
	public Long createSceneDetail(DcSceneDetailDO detail) {
		return dcSceneDetailDAO.insertSceneDetail(detail) ;
	}

	@Override
	public List<Long> getItemsByScene(long sceneId,int size) {
		return dcSceneDetailDAO.queryItemsBySceneWithPagination(sceneId, 1 , size) ;
	}
	
	@Override
	public QueryResult<Long> getItemsBySceneWithPagination(long sceneId , Pagination pagination) {
		int totalRecords = dcSceneDetailDAO.countItemsBySceneWithPagination(sceneId) ;
        if (totalRecords <= 0) {
            return new QueryResult<Long>(new ArrayList<Long>(), pagination, totalRecords);
        }
        List<Long> itemIdList = dcSceneDetailDAO.queryItemsBySceneWithPagination(sceneId, pagination.getStart(), pagination.getSize());
                
        return new QueryResult<Long>(itemIdList, pagination, totalRecords);
	}

	@Override
	public int removeSceneDetail(long itemId, long sceneId) {
		return dcSceneDetailDAO.deleteSceneDetail(itemId, sceneId) ;
	}

	

}
