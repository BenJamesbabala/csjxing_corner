package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzSyncReportTaskSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSyncReportTaskDO;

public interface DdzSyncReportTaskDAO {

	/**
	 * 插入报表task
	 * @return
	 */
	int insertReportTask(DdzSyncReportTaskDO taskReport) ;
	
	/**
	 * 根据TASK_ID查询
	 * @param taskId
	 * @return
	 */
	DdzSyncReportTaskDO queryByTaskId(String taskId) ;

	/**
	 * 更新（runoutData , isSuccess , reportMembCount）
	 * @param taskReport
	 * @return
	 */
	int updateByTaskId(DdzSyncReportTaskDO taskReport) ;
	
	/**
	 * 查询同步任务
	 * @param searchCondition 
	 * @param start
	 * @param size
	 * @return
	 */
	List<DdzSyncReportTaskDO> queryReportWithPagination(DdzSyncReportTaskSearchCondition searchCondition , int start , int size ) ;
}
