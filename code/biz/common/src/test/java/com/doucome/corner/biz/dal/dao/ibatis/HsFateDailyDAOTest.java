package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.horoscope.HsDailyFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsDailyFateDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/horoscope/biz-hs-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class HsFateDailyDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private HsDailyFateDAO hsDailyFateDAO;
	
	@Test
	public void testQueryDailyHsFate() {
		HsDailyFateDO hsFateDailyDO = hsDailyFateDAO.queryHsDailyFate(1l);
		Assert.assertNotNull(hsFateDailyDO);
	}
    
	@Test
	public void testQueryDailyHsFateByDate() {
		Calendar today = Calendar.getInstance();
		DateUtils.truncate(today, Calendar.HOUR_OF_DAY);
		Date dayStart = today.getTime();
		today.add(Calendar.DAY_OF_MONTH, 1);
		today.add(Calendar.SECOND, -1);
		Date dayEnd = today.getTime();
		HsDailyFateDO hsFateDailyDO = hsDailyFateDAO.queryHsDailyFate(1, dayStart, dayEnd);
		Assert.assertNotNull(hsFateDailyDO);
	}
}
