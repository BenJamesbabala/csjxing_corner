package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.horoscope.HsWeekFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsWeekFateDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/horoscope/biz-hs-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class HsFateWeekDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private HsWeekFateDAO hsWeekFateDAO;
	
	@Test
	public void testQueryDailyHsFate() {
		HsWeekFateDO hsFate = hsWeekFateDAO.queryHsWeekFate(1l);
		Assert.assertNotNull(hsFate);
	}
    
	@Test
	public void testQueryDailyHsFateByDate() {
		Calendar today = Calendar.getInstance();
		DateUtils.truncate(today, Calendar.HOUR_OF_DAY);
		Date weekStart = today.getTime();
		today.add(Calendar.DAY_OF_MONTH, 1);
		today.add(Calendar.SECOND, -1);
		Date weekEnd = today.getTime();
		HsWeekFateDO hsFateDailyDO = hsWeekFateDAO.queryHsWeekFate(1, weekStart, weekEnd);
		Assert.assertNotNull(hsFateDailyDO);
	}
}
