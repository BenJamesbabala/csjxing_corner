package com.doucome.corner.biz.zhe.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.DdzTaokeReportSettleDAO;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleStatisticsDO;
import com.doucome.corner.biz.zhe.model.SettleResult;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.utils.SettleUtils;

public class DdzTaokeReportSettleServiceImpl implements DdzTaokeReportSettleService {
	
	@Autowired
	private DdzTaokeReportSettleDAO ddzTaokeReportSettleDAO;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService;
	
	@Autowired
	private DdzTaokeReportDAO ddzTaokeReportDAO ;
	
	private boolean productionMode;
	
	private static final Log log = LogFactory.getLog(DdzTaokeReportSettleServiceImpl.class);
	
	private static final Log settleResetLog = LogFactory.getLog("report-settle-log");
	
	@Override
	public Long insertSettleReport(DdzTaokeReportSettleDO settleDO) {
		return ddzTaokeReportSettleDAO.insertSettleReport(settleDO);
	}
	
	@Override
	public Integer deleteSettleReport(Long id) {
		try {
			return ddzTaokeReportSettleDAO.deleteSettleReport(id);
		} catch (Exception e) {
			log.error(e);
			return -1;
		}
	}
	
	@Override
	public List<AlipayItemDO> getAlipayItems(int pageSize) {
		List<AlipayItemDO> settleItems = null;
		try {
			settleItems = ddzTaokeReportSettleDAO.getAliSettleItems(1,pageSize);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
		if (!productionMode) {
			SettleUtils.resetPayAmount(settleItems);
		}
		return settleItems;
	}
	
	@Override
	public SettleResult updateSettleStatus(List<DdzTaokeReportSettleDO> settleDOs) {
		SettleResult result = new SettleResult();
		
		if (settleDOs == null || settleDOs.size() == 0) {
			return result;
		}
		try {
			int count = ddzTaokeReportSettleDAO.updateSettleStatus(settleDOs);
			result.setUpdateSettleCount(count);
			count = ddzTaokeReportService.updateTaokeReportSettleStatusBySettleReport(convert(settleDOs));
			result.setUpdateReportCount(count);
		} catch(Exception e) {
			log.error("----update settle status failed: " + settleDOs.toString(), e);
			result.setSucc(false);
			result.setMessage("update report settle status failed, please update manual");
		}
		return result;
	}
	
	private List<DdzTaokeReportDO> convert(List<DdzTaokeReportSettleDO> settleDOs) {
		List<DdzTaokeReportDO> reportDOs = new ArrayList<DdzTaokeReportDO>();
		if (settleDOs == null) {
			return reportDOs;
		}
		for(DdzTaokeReportSettleDO settleDO: settleDOs) {
			DdzTaokeReportDO reportDO = new DdzTaokeReportDO();
			reportDO.setSettleId(settleDO.getId());
			reportDO.setSettleStatus(settleDO.getSettleStatus());
			reportDOs.add(reportDO);
		}
		return reportDOs;
	}
	
	@Override
	public int updateAlipayResult(List<DdzTaokeReportSettleDO> settleDOs) {
		if (settleDOs == null || settleDOs.size() == 0) {
			return 0;
		}
		try {
			return ddzTaokeReportSettleDAO.updateAlipayResult(settleDOs);
		} catch(Exception e) {
			log.error(e);
			return -1;
		}
	}
	
	@Override
	public int updateMemoById(Integer settleId, String memo) {
		return ddzTaokeReportSettleDAO.updateMemoById(settleId, memo) ;
	}

	public boolean isProductionMode() {
		return productionMode;
	}

	public void setProductionMode(boolean productionMode) {
		this.productionMode = productionMode;
	}

    @Override
    public int updateTaokeSettleStatus(List<Long> settleIdList, SettleStatusEnums toSettleStatus) {
        int count = ddzTaokeReportSettleDAO.updateSettleStatus(settleIdList, toSettleStatus.getValue());
        int count1 = ddzTaokeReportDAO.updateTaokeReportSettleStatus(settleIdList, toSettleStatus.getValue());
        return count;
    }
    
    @Override
	public int updateTaokeSettleStatus(DdzTaokeReportSettleUpdateCondition condition,SettleStatusEnums toSettleStatus , String settleBatchno) {
    	int count = ddzTaokeReportSettleDAO.updateSettleStatus(condition, toSettleStatus.getValue() , settleBatchno);
    	int count1 = ddzTaokeReportDAO.updateTaokeReportSettleStatus(condition, toSettleStatus.getValue());
        return count;
	}
    
    @Override
	public DdzTaokeReportSettleStatisticsDO statisticsWithPagination(DdzTaokeReportSettleSearchCondition searchCondition) {
		return ddzTaokeReportSettleDAO.statisticsWithPagination(searchCondition) ;
	}

    @Override
    public DdzTaokeReportSettleDO getById(Long id) {
        if (id != null) {
            return ddzTaokeReportSettleDAO.getById(id);
        }
        return null;
    }

	@Override
	public BigDecimal getTotalSettleFee(String settleAlipay,String[] settleStatus) {
		return ddzTaokeReportSettleDAO.sumTotalSettleFee(settleAlipay ,settleStatus) ;
	}

	@Override
	public int countTotalSettle(String settleAlipay, String[] settleStatus) {
		return ddzTaokeReportSettleDAO.countTotalSettle(settleAlipay ,settleStatus) ;
	}

	@Override
	public int countSettleReports(
			DdzTaokeReportSettleSearchCondition searchCondition) {
		return ddzTaokeReportSettleDAO.countSettlesWithPagination(searchCondition) ;
	}

	

	
	
}
