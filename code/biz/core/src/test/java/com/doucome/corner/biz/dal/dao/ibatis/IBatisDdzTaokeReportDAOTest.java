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
import com.doucome.corner.biz.core.utils.UidCreateUtil;
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
		tempDo = ddzTaokeReportDAO.getReportById(165L);
		Assert.assertNotNull(tempDo);
	}
	
	@Test
	public void testUpdateTaokeReportSettleStatus() {
		List<String> reportIds = new ArrayList<String>();
		String batchNO = UidCreateUtil.createUid("162");
		reportIds.add("162");
		reportIds.add("163");
		int count = ddzTaokeReportDAO.updateTaokeReportSettleStatus(reportIds, SettleStatusEnums.UNSETTLE.getValue(), batchNO);
		Assert.assertEquals(2, count);
	}
	
	@Test
	public void testGetAlipayItemWithPagination() {
		Pagination pagination = new Pagination(1, 20);
		List<AlipayItemDO> itemDOs = ddzTaokeReportDAO.getAlipayItemWithPagination(pagination);
		Assert.assertNotNull(itemDOs);
	}
	
	@Test
	public void testCountAlipayItem() {
		Integer count = ddzTaokeReportDAO.countAlipayItem();
	}
}
