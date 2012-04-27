package com.doucome.corner.biz.zhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzSyncReportTaskDAO;
import com.doucome.corner.biz.dal.dataobject.DdzSyncReportTaskDO;
import com.doucome.corner.biz.zhe.service.DdzSyncReportTaskService;

public class DdzSyncReportTaskServiceImpl implements DdzSyncReportTaskService {

	@Autowired
	private DdzSyncReportTaskDAO ddzSyncReportTaskDAO ;
	
	@Override
	public int createReportTask(DdzSyncReportTaskDO taskReport) {
		return ddzSyncReportTaskDAO.insertReportTask(taskReport) ;
	}

	@Override
	public DdzSyncReportTaskDO getByTaskId(String taskId) {
		return ddzSyncReportTaskDAO.queryByTaskId(taskId) ;
	}
	

	@Override
	public int updateByTaskId(DdzSyncReportTaskDO taskReport) {
		return ddzSyncReportTaskDAO.updateByTaskId(taskReport) ;
	}

	

	
}
