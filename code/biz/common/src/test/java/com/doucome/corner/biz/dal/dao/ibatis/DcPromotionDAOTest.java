package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.common.utils.CalendarUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcPromotionDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcPromotionDAO dcPromotionDAO;
	
	@Test
	public void testInsertPromotion() {
		DcPromotionDO dcPromotionDO = new DcPromotionDO();
		dcPromotionDO.setLimitTopPrice(new BigDecimal("100"));
		dcPromotionDO.setPromType("PK");
		Date[] dates = getDayStartEnd(1);
		dcPromotionDO.setStartTime(dates[0]);
		dcPromotionDO.setEndTime(dates[1]);
		dcPromotionDO.setStatus("S");
//		Long id = dcPromotionDAO.insertPromotion(dcPromotionDO);
//		Assert.assertNotNull(id);
		
		dcPromotionDO.setStartTime(getNDayAfter(dates[0], 1));
		dcPromotionDO.setEndTime(getNDayAfter(dates[1], 1));
		Long id = dcPromotionDAO.insertPromotion(dcPromotionDO);
	}
	
	private Date[] getDayStartEnd(int ndayAfter) {
		Calendar calendar = Calendar.getInstance();
		CalendarUtils.trimTo(calendar, Calendar.HOUR_OF_DAY);
		calendar.add(Calendar.DAY_OF_YEAR, ndayAfter);
		Date dayStart = calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return new Date[] {dayStart, calendar.getTime()};
	}
	
	private Date getNDayAfter(Date date, int nday) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, nday);
		return calendar.getTime();
	}
	
	@Test
	public void testQueryPromotionById() {
		
	}
	
	@Test
	public void testQueryPromotionByDate() {
		Date date = new Date();
		DcPromotionDO promotionDO = dcPromotionDAO.queryPromotionByDate(date);
		Assert.assertNotNull(promotionDO);
	}
}
