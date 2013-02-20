package com.doucome.corner.biz.dcome.service;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralRecordDO;
import com.doucome.corner.biz.dcome.enums.DcUserIntegralInOutTypeEnums;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcUserIntegralRecordServiceTest extends AbstractBaseJUnit4Test {

	@Autowired
	DcUserIntegralRecordService dcUserIntegralRecordService ;
	
	@Test
	public void testCreate(){
		DcUserIntegralRecordDO record = new DcUserIntegralRecordDO() ;
		record.setInOutType(DcUserIntegralInOutTypeEnums.INCOME.getValue()) ;
		record.setIntegralBalance(1000) ;
		record.setIntegralCount(200) ;
		record.setIntegralDesc("123456") ;
		record.setSource("BUY") ;
		record.setTaokeReportId(10002L) ;
		record.setUserId(2000L) ;
		
		long id = dcUserIntegralRecordService.createRecord(record) ;
		Assert.assertTrue(id > 0L) ;
	}
}
