package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcRobOtherActivityDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionRateDetailDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcPromotionRateDetailDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcPromotionRateDetailDAO dcPromotionRateDetailDAO;
	
	@Test
	public void testGetUserRobActivities() {
		List<DcRobOtherActivityDO> robActivities = dcPromotionRateDetailDAO.getUserRobActivities(474L);
		Assert.assertNotNull(robActivities);
	}
	
	@Test
	public void testGetPromRobActivities() {
		List<DcRobOtherActivityDO> robActivities = dcPromotionRateDetailDAO.getPromRobActivities(474L);
		Assert.assertNotNull(robActivities);
	}
}
