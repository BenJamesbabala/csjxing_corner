package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.DdzTaokeReportSettleDAO;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/zhe/biz-zhe-dao.xml",
		"classpath:corner/bean/biz-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DdzTaokeReportSettleDAOTest extends AbstractBaseJUnit4Test { 
	@Autowired
	private DdzTaokeReportSettleDAO ddzTaokeReportSettleDAO;
	
	@Test
	public void testInsert() {
		DdzTaokeReportSettleDO settleDO = new DdzTaokeReportSettleDO();
		settleDO.setSettleAlipay("zhang88187@gmail.com");
		settleDO.setSettleFee(new BigDecimal("0.01"));
		settleDO.setSettleStatus("U");
		Long id = ddzTaokeReportSettleDAO.insertSettleReport(settleDO);
		Assert.assertEquals(true, id > 0);
		int count = ddzTaokeReportSettleDAO.deleteSettleReport(id);
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testGetAliSettleItems() {
		List<AlipayItemDO> itemDOs = ddzTaokeReportSettleDAO.getAliSettleItems(1,20);
		Assert.assertNotNull(itemDOs);
	}
	
	@Test
	public void testCountAlipayItem() {
		int count = ddzTaokeReportSettleDAO.countAlipayItem();
		System.out.println(count);
	}
	
//	@Test
//	public void testUpdateSettleStatus() {
//		List<DdzTaokeReportSettleDO> settleDOs = new ArrayList<DdzTaokeReportSettleDO>();
//		DdzTaokeReportSettleDO settleDO = new DdzTaokeReportSettleDO();
//		settleDO.setId(517L);
//		settleDO.setSettleStatus(SettleStatusEnums.SETTLE_FAIL.getValue());
//		settleDOs.add(settleDO);
//		settleDO = new DdzTaokeReportSettleDO();
//		settleDO.setId(2L);
//		settleDO.setSettleStatus(SettleStatusEnums.SETTLE_PENDING.getValue());
//		settleDO.setSettleBatchno("asdf");
//		settleDOs.add(settleDO);
//		int count = ddzTaokeReportSettleDAO.updateSettleStatus(settleDOs);
//		Assert.assertEquals(1, count);
//	}
	
//	@Test
//	public void testUpdateAlipayResult() {
//		List<DdzTaokeReportSettleDO> settleDOs = new ArrayList<DdzTaokeReportSettleDO>();
//		DdzTaokeReportSettleDO settleDO = new DdzTaokeReportSettleDO();
//		settleDO.setId(517L);
//		settleDO.setSettleStatus(AlipayStatusEnum.FAIL.getValue());
//		settleDOs.add(settleDO);
//		int count = ddzTaokeReportSettleDAO.updateAlipayResult(settleDOs);
//		Assert.assertEquals(1, count);
//	}
}
