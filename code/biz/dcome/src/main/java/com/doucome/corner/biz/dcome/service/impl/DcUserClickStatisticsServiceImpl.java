package com.doucome.corner.biz.dcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserClickStatisticsDO;
import com.doucome.corner.biz.dal.dcome.DcUserClickStatisticsDAO;
import com.doucome.corner.biz.dcome.enums.DcUserClickStatisticsEnums;
import com.doucome.corner.biz.dcome.service.DcUserClickStatisticsService;

public class DcUserClickStatisticsServiceImpl implements DcUserClickStatisticsService {

	@Autowired
	private DcUserClickStatisticsDAO dcUserClickStatisticsDAO ;
	
	@Override
	public long createClickStatistics(DcUserClickStatisticsDO clickStatistics) {
		return dcUserClickStatisticsDAO.insertClickStatistics(clickStatistics) ;
	}

	@Override
	public DcUserClickStatisticsDO getClickStatisticsByUser(Long userId , DcUserClickStatisticsEnums clickType) {
		return dcUserClickStatisticsDAO.queryClickStatisticsByUser(userId, clickType.getValue()) ;
	}

	@Override
	public int incrClickStatisticsByUser(Long userId , DcUserClickStatisticsEnums clickType) {
		return dcUserClickStatisticsDAO.incrClickStatisticsByUser(userId, clickType.getValue()) ;
	}

	
	
}
