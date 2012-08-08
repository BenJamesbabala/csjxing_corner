package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.DdzTaokeReportSettleDAO;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;

public class DdzTaokeReportServiceImpl implements DdzTaokeReportService {

	@Autowired
	private DdzTaokeReportDAO ddzTaokeReportDAO ;
	@Autowired
	private DdzTaokeReportSettleDAO ddzTaokeReportSettleDAO;
	
	private static final Log log = LogFactory.getLog(DdzTaokeReportServiceImpl.class);
	
	@Override
	public int createReport(DdzTaokeReportDO report) {
		return ddzTaokeReportDAO.insertReport(report) ;
	}

	@Override
	public int settleByTradeId(DdzTaokeReportSettleDO settle) {
		return ddzTaokeReportDAO.settleByTradeId(settle) ;
	}
	
	@Override
	public DdzTaokeReportDO getReportById(Long reportId) {
		try {
			return ddzTaokeReportDAO.getReportById(reportId);
		} catch (Exception e) {
			log.error("Get the taoke report failed[" + reportId + "].", e);
			return null;
		}
	}

	@Override
	public QueryResult<DdzTaokeReportDO> getReportsWithPagination(TaokeReportSearchCondition searchCondition , Pagination pagination) {
		int totalRecords = ddzTaokeReportDAO.countReportsWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DdzTaokeReportDO>(new ArrayList<DdzTaokeReportDO>(), pagination, totalRecords);
        }
        List<DdzTaokeReportDO> items = ddzTaokeReportDAO.selectReportsWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        return new QueryResult<DdzTaokeReportDO>(items, pagination, totalRecords);
	}
	
    @Override
    public List<DdzTaokeReportDO> getReports(TaokeReportSearchCondition searchCondition) {
        List<DdzTaokeReportDO> items = ddzTaokeReportDAO.selectReportsWithPagination(searchCondition, 1,
                                                                                     Integer.MAX_VALUE);
        return items;
    }
	
	@Override
	public int updateTaokeReportSettleStatusBySettleReport(List<DdzTaokeReportDO> reportDOs) {
		if (reportDOs == null || reportDOs.size() == 0) {
			return 0;
		}
		try {
			return ddzTaokeReportDAO.updateSettleStatusBySettleReport(reportDOs);
		} catch (Exception e) {
			log.error("update report settle status by settle report failed: " + reportDOs.toString(), e);
			return -1;
		}
	}
	
	@Override
	public List<AlipayItemDO> getUnMergedReportSettleInfo(Pagination pagination) {
		try {
			return ddzTaokeReportDAO.getUnMergedReportSettleInfo(pagination.getStart() , pagination.getSize());
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	@Override
	public int updateTaokeReportSettleId(List<Long> reportIds, Long settleId) throws Exception {
		if(reportIds == null || reportIds.size() == 0) {
			return 0;
		}
		try {
			return ddzTaokeReportDAO.updateTaokeReportSettleId(reportIds, settleId);
		} catch (Exception e) {
			log.error(e);
			return -1;
		}
	}

	@Override
	public QueryResult<DdzTaokeReportSettleDO> getSettlesWithPagination(DdzTaokeReportSettleSearchCondition searchCondition,Pagination pagination) {
		int totalRecords = ddzTaokeReportSettleDAO.countSettlesWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DdzTaokeReportSettleDO>(new ArrayList<DdzTaokeReportSettleDO>(), pagination, totalRecords);
        }
        List<DdzTaokeReportSettleDO> items = ddzTaokeReportSettleDAO.selectSettlesWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        return new QueryResult<DdzTaokeReportSettleDO>(items, pagination, totalRecords);
		
	}
	
	@Override
    public List<DdzTaokeReportSettleDO> getSettleReports(DdzTaokeReportSettleSearchCondition searchCondition) {
        List<DdzTaokeReportSettleDO> items = ddzTaokeReportSettleDAO.selectSettlesWithPagination(searchCondition, 1,
                                                                                     Integer.MAX_VALUE);
        return items;
    }

	@Override
	public List<DdzTaokeReportDO> getReportsBySettleId(Integer settleId) {
		return ddzTaokeReportDAO.selectReportsBySettleId(settleId) ;
	}	
	
}
