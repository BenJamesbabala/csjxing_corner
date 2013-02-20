package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsMonthFateCondition;
import com.doucome.corner.biz.dal.horoscope.HsMonthFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsMonthFateDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/horoscope/biz-hs-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class HsFateMonthDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private HsMonthFateDAO hsMonthFateDAO;
	
	@Test
	public void testQueryDailyHsFate() {
		HsMonthFateDO hsFate = hsMonthFateDAO.queryHsMonthFate(1l);
		Assert.assertNotNull(hsFate);
	}
    
	@Test
	public void testQueryDailyHsFateByDate() {
		Calendar today = Calendar.getInstance();
		DateUtils.truncate(today, Calendar.HOUR_OF_DAY);
		Date monthStart = today.getTime();
		today.add(Calendar.DAY_OF_MONTH, 1);
		today.add(Calendar.SECOND, -1);
		Date monthEnd = today.getTime();
		HsMonthFateDO hsFate = hsMonthFateDAO.queryHsMonthFate(1, monthStart, monthEnd);
		Assert.assertNotNull(hsFate);
	}
	
	@Test
	public void testQueryDailyHsFates() {
		HsMonthFateCondition condition = new HsMonthFateCondition();
		hsMonthFateDAO.queryHsMonthFates(condition, 1, 10);
	}
}
