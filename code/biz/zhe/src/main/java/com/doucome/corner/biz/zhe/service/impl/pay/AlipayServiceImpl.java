package com.doucome.corner.biz.zhe.service.impl.pay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.AlipayStatusEnum;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.core.utils.UidCreateUtil;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.service.AlipayService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.utils.PayUtils;

public class AlipayServiceImpl implements AlipayService {
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService;
	
	private boolean productionMode;
	
	private int pageSize;
	
	private static final Log log = LogFactory.getLog(AlipayServiceImpl.class);
	
	@Override
	public List<AlipayItemDO> getAlipayItems() {
		Pagination pagination = new Pagination(1, pageSize);
		QueryResult<AlipayItemDO> payItem = ddzTaokeReportService.getAlipayItemWithPagination(pagination);
		if (payItem == null) {
			return null;
		}
		List<AlipayItemDO> payItemDOs = payItem.getItems();
		if (!productionMode) {
			PayUtils.resetPayAmount(payItemDOs);
		}
		
		log.error("pay to: " + getTaokeReportIds(payItemDOs));
		return payItemDOs;
	}
	
	/**
	 * 
	 * @param alipayItems
	 * @return
	 */
	private String getTaokeReportIds(List<AlipayItemDO> alipayItems) {
		if (alipayItems == null || alipayItems.size() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(alipayItems.get(0).getIds().toString());
		for (int i = 1; i < alipayItems.size(); i++) {
			builder.append(",").append(alipayItems.get(i).getIds());
		}
		return builder.toString();
	}
	
	@Override
	public int updateTaokeReportSettleStatus(List<String> expectedIds, List<String> actualIds,
			     SettleStatusEnums settleStatus) {
		String batchNO = UidCreateUtil.createUid(expectedIds.get(0), 15);
		List<String> failIds = PayUtils.difference(expectedIds, actualIds);
		int count1 = ddzTaokeReportService.updateTaokeReportSettleStatus(actualIds, settleStatus, batchNO);
		int count2 = ddzTaokeReportService.updateTaokeReportSettleStatus(failIds, SettleStatusEnums.SETTLE_FAIL,
				       batchNO);
		return count1 + count2;
	}
	
	@Override
	public int updateTaokeReportAlipayResult(List<String> reportIds, AlipayStatusEnum alipayStatus, String alipayBatchNO) {
		if (reportIds == null || reportIds.size() == 0 || alipayStatus == null) {
			return -1;
		}
		return ddzTaokeReportService.updateTaokeReportAlipayResult(reportIds, alipayStatus, alipayBatchNO);
	}
	
	/**
	 * 获取当日某个结算状态数据.
	 * @param settleStatus 结算状态.
	 * @return
	 */
	public QueryResult<DdzTaokeReportDO> getTodaySettleRelatedReport(SettleStatusEnums settleStatus, Pagination pagination) {
		TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition();
		Calendar today = Calendar.getInstance();
		searchCondition.setGmtSettledStart(DateUtils.trimDate(today.getTime(), Calendar.HOUR_OF_DAY));
		//tomorrow
		today.add(Calendar.DAY_OF_MONTH, 1);
		searchCondition.setGmtSettledEnd(DateUtils.trimDate(today.getTime(), Calendar.HOUR_OF_DAY));
		searchCondition.setSettleStatus(SettleStatusEnums.SETTLE_PENDING.getValue());
		try {
			return ddzTaokeReportService.getReportsWithPagination(searchCondition, pagination);
		} catch (Exception e) {
			log.error("get today settle related report failed: " + settleStatus, e);
			return new QueryResult<DdzTaokeReportDO>(new ArrayList<DdzTaokeReportDO>(), pagination, 0);
		}
	}

	public boolean isProductionMode() {
		return productionMode;
	}

	public void setProductionMode(boolean productionMode) {
		this.productionMode = productionMode;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
