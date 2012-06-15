package com.doucome.corner.biz.zhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzRecommendTaskDAO;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendTaskDO;
import com.doucome.corner.biz.zhe.service.DdzRecommendTaskService;

public class DdzRecommendTaskServiceImpl implements DdzRecommendTaskService {
	
	@Autowired
	private DdzRecommendTaskDAO ddzRecommendTaskDAO ;

	@Override
	public DdzRecommendTaskDO getRecommendTask(String recommendType) {
		return ddzRecommendTaskDAO.queryRecommendTask(recommendType) ;
	}

	@Override
	public void updateRecommendTaskBatch(String recommendType,String lastTaskBatch) {
		ddzRecommendTaskDAO.updateRecommendTaskBatch(recommendType, lastTaskBatch) ;
	}

}
