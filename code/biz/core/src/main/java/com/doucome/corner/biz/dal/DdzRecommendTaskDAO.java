package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.DdzRecommendTaskDO;

public interface DdzRecommendTaskDAO {

	DdzRecommendTaskDO queryRecommendTask(String recommendType) ;

	void updateRecommendTaskBatch(String recommendType , String lastTaskBatch) ;
	
	
}
