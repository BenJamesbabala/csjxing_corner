package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.dal.dataobject.DdzSyncReportTaskDO;

public interface DdzSyncReportTaskService {

	int createReportTask(DdzSyncReportTaskDO taskReport) ;
	
	DdzSyncReportTaskDO getByTaskId(String taskId) ;

	int updateByTaskId(DdzSyncReportTaskDO taskReport) ;
}
