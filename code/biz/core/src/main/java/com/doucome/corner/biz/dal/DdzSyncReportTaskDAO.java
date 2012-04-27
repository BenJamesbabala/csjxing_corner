package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzSyncReportTaskSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSyncReportTaskDO;

public interface DdzSyncReportTaskDAO {

	/**
	 * ���뱨��task
	 * @return
	 */
	int insertReportTask(DdzSyncReportTaskDO taskReport) ;
	
	/**
	 * ����TASK_ID��ѯ
	 * @param taskId
	 * @return
	 */
	DdzSyncReportTaskDO queryByTaskId(String taskId) ;

	/**
	 * ���£�runoutData , isSuccess , reportMembCount��
	 * @param taskReport
	 * @return
	 */
	int updateByTaskId(DdzSyncReportTaskDO taskReport) ;
	
	/**
	 * ��ѯͬ������
	 * @param searchCondition 
	 * @param start
	 * @param size
	 * @return
	 */
	List<DdzSyncReportTaskDO> queryReportWithPagination(DdzSyncReportTaskSearchCondition searchCondition , int start , int size ) ;
}
