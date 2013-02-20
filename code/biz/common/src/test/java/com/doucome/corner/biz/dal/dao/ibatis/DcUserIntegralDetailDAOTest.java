package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;
import com.doucome.corner.biz.dal.dcome.DcUserIntegralDetailDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;


@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcUserIntegralDetailDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcUserIntegralDetailDAO dcUserIntegralDetailDAO;
	@Test
	public void testGetLatestIntegralDetails() {
		List<DcUserIntegralDetailDO> detail =
			dcUserIntegralDetailDAO.getLatestIntegralDetails("NOTI", 0, 20);
		Assert.assertNotNull(detail);
		
		detail = dcUserIntegralDetailDAO.getLatestIntegralDetails("STL", 0, 20);
		Assert.assertTrue(detail.size() > 0);
		detail = dcUserIntegralDetailDAO.getLatestIntegralDetails(null, 0, 20);
		Assert.assertTrue(detail.size() > 0);
		detail = dcUserIntegralDetailDAO.getLatestIntegralDetails("", 0, 20);
		Assert.assertTrue(detail.size() > 0);
	}
	
	@Test
	public void testGetIntegralDetails() {
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(10003l);
		List<DcUserIntegralDetailDO> integralDetails =
			dcUserIntegralDetailDAO.getIntegralDetails(condition, 0, 10);
		Assert.assertEquals(10, integralDetails.size());
		
		condition.setUserId(-1l);
		integralDetails = dcUserIntegralDetailDAO.getIntegralDetails(condition, 0, 10);
		Assert.assertEquals(0, integralDetails.size());
		
		condition.setUserId(10003l);
		condition.setSource("test");
		integralDetails = dcUserIntegralDetailDAO.getIntegralDetails(condition, 0, 10);
		Assert.assertEquals(0, integralDetails.size());
		
		condition.setUserId(10003l);
		condition.setSource("SHR");
		condition.setGmtCreateStart(new Date());
		condition.setGmtCreateEnd(new Date());
		integralDetails = dcUserIntegralDetailDAO.getIntegralDetails(condition, 0, 10);
		Assert.assertTrue(integralDetails.size() == 0);
		
		condition.setUserId(10003l);
		condition.setSource(null);
		condition.addSource("SHR");
		condition.addSource("INV");
		condition.setGmtCreateStart(new Date());
		condition.setGmtCreateEnd(new Date());
		integralDetails = dcUserIntegralDetailDAO.getIntegralDetails(condition, 0, 10);
		Assert.assertTrue(integralDetails.size() == 0);
	}
	
	@Test
	public void testCountIntegralDetails() {
		DcIntegralCondition condition = new DcIntegralCondition();
		condition.setUserId(10003l);
		Integer count = dcUserIntegralDetailDAO.countIntegralDetails(condition);
		Assert.assertTrue(count > 0);
		condition.setGmtCreateStart(new Date());
		condition.setGmtCreateEnd(new Date());
		count = dcUserIntegralDetailDAO.countIntegralDetails(condition);
		Assert.assertTrue(count == 0);
	}
	
	@Test
	public void testGetAuctionList() {
		List<DcUserIntegralDetailDO> temp = dcUserIntegralDetailDAO.getAuctionList(1l, 10);
		Assert.assertNotNull(temp);
	}
}
