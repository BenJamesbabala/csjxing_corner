package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;

public class DdzTaokeReportServiceImpl implements DdzTaokeReportService {

	@Autowired
	private DdzTaokeReportDAO ddzTaokeReportDAO ;
	
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
	public QueryResult<AlipayItemDO> getAlipayItemWithPagination(Pagination pagination) {
		try {
			int totalRecord = ddzTaokeReportDAO.countAlipayItem();
			if (totalRecord <= 0) {
				return new QueryResult<AlipayItemDO>(new ArrayList<AlipayItemDO>(), pagination, totalRecord);
			}
			List<AlipayItemDO> payItems = this.ddzTaokeReportDAO.getAlipayItemWithPagination(pagination);
			return new QueryResult<AlipayItemDO>(payItems, pagination, totalRecord);
		} catch (Exception e) {
			log.error("get alipay item failed", e);
			return null;
		}
	}
	
	@Override
	public int updateTaokeReportSettleStatus(List<String> reportIds, SettleStatusEnums settleStatus,
			     String internalBatchNO) {
		if (reportIds == null || reportIds.size() == 0 || settleStatus == null
			  || StringUtils.isEmpty(internalBatchNO)) {
			return -1;
		}
		try {
			return ddzTaokeReportDAO.updateTaokeReportSettleStatus(reportIds, settleStatus.getValue(), internalBatchNO);
		} catch (Exception e) {
			log.error("update report settle status failed: " + reportIds + ", " + settleStatus, e);
			return -1;
			
		}
	}
	
	public int updateTaokeReportAlipayResult(List<String> reportIds, AlipayStatusEnum alipayStatus,
			     String alipayBatchNO){
		if (reportIds == null || reportIds.size() == 0 || alipayStatus == null) {
			return -1;
		}
		try {
			return ddzTaokeReportDAO.updateTaokeReportAlipayResult(reportIds, alipayStatus.getValue(), alipayBatchNO);
		} catch (Exception e) {
			log.error(String.format("update report alipay status failed: [%s] [%s] [%s] ",
					       reportIds, alipayStatus.toString(), alipayBatchNO), e);
			return -1;
			
		}
	}
}
