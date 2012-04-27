package com.doucome.corner.biz;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.doucome.corner.biz.core.utils.DateUtils;

public class DateUtilsTest {
	
	@Test
	public void testTrimDate() {
		Date testDate = new Date();
		System.err.println(DateUtils.trimDate(testDate, Calendar.MINUTE));
		System.out.println(DateUtils.trimDate(testDate, Calendar.HOUR_OF_DAY));
		System.out.println(DateUtils.trimDate(testDate, Calendar.DAY_OF_MONTH));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(calendar.getTime());
	}
}
