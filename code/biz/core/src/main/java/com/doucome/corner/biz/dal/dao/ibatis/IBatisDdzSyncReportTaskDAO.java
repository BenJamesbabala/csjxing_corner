package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.DdzSyncReportTaskDAO;
import com.doucome.corner.biz.dal.condition.DdzSyncReportTaskSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzSyncReportTaskDO;

public class IBatisDdzSyncReportTaskDAO extends SqlMapClientDaoSupport implements DdzSyncReportTaskDAO {

	@Override
	public int insertReportTask(DdzSyncReportTaskDO taskReport) {
		return (Integer)getSqlMapClientTemplate().insert("ddzSyncReportTask.insertReportTaskId" , taskReport) ;
	}

	@Override
	public DdzSyncReportTaskDO queryByTaskId(String taskId) {
		return (DdzSyncReportTaskDO)getSqlMapClientTemplate().queryForObject("ddzSyncReportTask.queryByTaskId" , taskId) ;
	}

	@Override
	public int updateByTaskId(DdzSyncReportTaskDO taskReport) {
		return getSqlMapClientTemplate().update("ddzSyncReportTask.updateByTaskId" , taskReport) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzSyncReportTaskDO> queryReportWithPagination(DdzSyncReportTaskSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("gmtReportStart", searchCondition.getGmtReportStart()) ;
		condition.put("gmtReportEnd", searchCondition.getGmtReportEnd()) ;
		return getSqlMapClientTemplate().queryForList("ddzSyncReportTask.queryReportWithPagination" , condition );
	}

	
}
