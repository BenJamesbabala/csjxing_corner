package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.dal.dataobject.DdzRecommendTaskDO;

/**
 * 
 * @author shenjia.caosj 2012-5-24
 *
 */
public interface DdzRecommendTaskService {

	DdzRecommendTaskDO getRecommendTask(String recommendType) ;

	void updateRecommendTaskBatch(String recommendType , String lastTaskBatch) ;
}
