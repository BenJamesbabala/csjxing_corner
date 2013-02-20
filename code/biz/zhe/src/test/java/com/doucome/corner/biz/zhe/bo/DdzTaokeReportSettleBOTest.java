package com.doucome.corner.biz.zhe.bo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:biz-zhe-test.xml"})
public class DdzTaokeReportSettleBOTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private DdzTaokeReportSettleBO ddzTaokeReportSettleBO ;
	
	@Test
	public void testCombineManualSettlesByUser(){
		ddzTaokeReportSettleBO.manualCombineSettlesByUser("824714081@qq.com") ;
	}
	
}
