package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSearchLogDO;
import com.doucome.corner.biz.dal.dcome.DcSearchLogDAO;
import com.doucome.corner.biz.dcome.enums.DcAutoExchangeStatusEnum;
import com.doucome.corner.biz.dcome.model.DcSearchLogDTO;
import com.doucome.corner.biz.dcome.service.DcSearchLogService;

public class DcSearchLogServiceImpl implements DcSearchLogService {
	@Autowired
	private DcSearchLogDAO dcSearchLogDAO;
	
	@Override
	public Long insertSearchLog(DcSearchLogDTO searchLog) {
		if (searchLog == null) {
			return -1l;
		}
		return dcSearchLogDAO.insertSearchLog(searchLog.toDO());
	}

	@Override
	public QueryResult<DcSearchLogDTO> querySearchLogsPage(DcSearchLogCondition condition, Pagination page) {
		int count = dcSearchLogDAO.countSearchLogs(condition);
		if (count == 0) {
			return new QueryResult<DcSearchLogDTO>(new ArrayList<DcSearchLogDTO>(), page, 0);
		}
		List<DcSearchLogDO> searchLogs = dcSearchLogDAO.querySearchLogsPage(condition, page.getStart() - 1, page.getSize());
		List<DcSearchLogDTO> temps = new ArrayList<DcSearchLogDTO>();
		for (DcSearchLogDO searchLog: searchLogs) {
			temps.add(new DcSearchLogDTO(searchLog));
		}
		clearRepeat(temps);
		return new QueryResult<DcSearchLogDTO>(temps, page, count);
	}
	
	@Override
	public int updateSearchLogStatus(DcSearchLogCondition condition, DcAutoExchangeStatusEnum status) {
		if (status == null || status == DcAutoExchangeStatusEnum.UNKNOWN) {
			return 0;
		}
		return dcSearchLogDAO.updateSearchLogStatus(condition, status.getValue());
	}
	
	private void clearRepeat(List<DcSearchLogDTO> searchLogs) {
		if (CollectionUtils.isEmpty(searchLogs) || searchLogs.size() == 1) {
			return ;
		}
		Collections.sort(searchLogs);
		DcSearchLogDTO temp = searchLogs.get(searchLogs.size() - 1);
		for (int i = searchLogs.size() - 2; i >= 0; i--) {
			if (temp.compareTo(searchLogs.get(i)) == 0) {
				temp.setSearchCount(temp.getSearchCount() + 1);
				searchLogs.remove(i);
			} else {
				temp = searchLogs.get(i);
			}
		}
	}
}
