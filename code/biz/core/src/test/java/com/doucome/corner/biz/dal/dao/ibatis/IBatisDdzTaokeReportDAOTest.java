package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.test.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/zhe/biz-zhe-dao.xml",
		"classpath:corner/bean/biz-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class IBatisDdzTaokeReportDAOTest extends AbstractBaseJUnit4Test  {

	@Autowired
	public DdzTaokeReportDAO ddzTaokeReportDAO ;
	
	@SuppressWarnings("unused")
	@Test
	public void test_insert(){
		for(int i = 0 ;i<30 ;i++){
			DdzTaokeReportDO report = new DdzTaokeReportDO() ;
			report.setCategoryId(32323L) ;
			report.setCommission(new BigDecimal("323.22")) ;
			report.setCommissionRate(new BigDecimal("323")) ;
			report.setGmtPaid(new Date()) ;
			report.setItemNum(32323L) ;
			report.setItemTitle("ÌØ¼ÛNIKEÅÜ²½Ð¬ " + i ) ;
			report.setNumIid(3244l) ;
			report.setOutCode("1324380") ;
			report.setPayPrice(new BigDecimal("323.333")) ;
			report.setRealPayFee(new BigDecimal("33.55")) ;
			report.setSellerNick("doucome2012") ;
			report.setSettleTaobaoNick("doucome2012") ;
			report.setSettleAlipay("doucome@163.com") ;
			report.setSettleFee(new BigDecimal("23.44")) ;
			report.setUserCommission(new BigDecimal("23.44")) ;
			report.setUserCommission(new BigDecimal("5.0")) ;
			report.setSettleUid("13233") ;
			report.setTradeId(12132324343L) ;
			int l = ddzTaokeReportDAO.insertReport(report) ;
		}
		int c = 2 ;
	}
	

	@Test
	public void test_updateSettle(){
		
	}
	
	@Test
	public void testGetReportById() {
		DdzTaokeReportDO tempDo = ddzTaokeReportDAO.getReportById(1L);
		Assert.assertNull(tempDo);
		tempDo = ddzTaokeReportDAO.getReportById(522L);
		Assert.assertNotNull(tempDo);
	}
	
	@Test
	public void testSelectReportsWithPagination() {
		TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition();
		List<DdzTaokeReportDO> reportDOs = ddzTaokeReportDAO.selectReportsWithPagination(searchCondition, 1, 10);
		Assert.assertNotNull(reportDOs);
	}
	
	@Test
	public void testUpdateTaokeReportSettleStatus() {
		List<Integer> settleIds = new ArrayList<Integer>();
		settleIds.add(400);
		settleIds.add(500);
		int count = ddzTaokeReportDAO.updateTaokeReportSettleStatus(settleIds, SettleStatusEnums.UNSETTLE.getValue());
		Assert.assertEquals(2, count);
	}
	
	@Test
	public void testUpdateSettleStatusBySettleReport() {
		List<DdzTaokeReportDO> reportDOs = new ArrayList<DdzTaokeReportDO>();
		DdzTaokeReportDO reportDO = new DdzTaokeReportDO();
		reportDO.setSettleId(1L);
		reportDO.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue());
		reportDOs.add(reportDO);
		reportDO = new DdzTaokeReportDO();
		reportDO.setSettleId(518L);
		reportDO.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue());
		reportDOs.add(reportDO);
		
		int count = ddzTaokeReportDAO.updateSettleStatusBySettleReport(reportDOs);
		System.out.println(count);
	}
	
	@Test
	public void testGetUnMergedReportSettleInfo() {
		Pagination pagination = new Pagination(1, 20);
		List<AlipayItemDO> payItemDOs = ddzTaokeReportDAO.getUnMergedReportSettleInfo(pagination);
		Assert.assertNotNull(payItemDOs);
		System.out.println(payItemDOs);
	}
	
	@Test
	public void testUpdateTaokeReportSettleId() {
		List<Long> reportIds = new ArrayList<Long>();
		reportIds.add(505L);
		reportIds.add(1L);
		int count = ddzTaokeReportDAO.updateTaokeReportSettleId(reportIds, null);
		System.out.println(count);
	}
}
