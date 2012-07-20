package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.DdzSearchLogDAO;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/zhe/biz-zhe-dao.xml",
		"classpath:corner/bean/biz-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class IBatisDdzSearchLogDAOTest extends AbstractBaseJUnit4Test {

	@Autowired
	private DdzSearchLogDAO ddzSearchLogDAO ;
	
	@SuppressWarnings("unused")
	@Test
	public void test_insert(){
		DdzSearchLogDO searchLog = new DdzSearchLogDO() ;
		searchLog.setAlipayId("alfdeihf") ;
		searchLog.setUid("uid") ;
		searchLog.setCommission(new BigDecimal("22.22")) ;
		searchLog.setCommissionRate(new BigDecimal("323")) ;
		searchLog.setPrice(new BigDecimal("222.33")) ;
		searchLog.setSearchBrief("34re") ;
		searchLog.setSearchWay("keyword") ;
		long id = ddzSearchLogDAO.insertLog(searchLog) ;
		int a  = 5;
	}
	
	@SuppressWarnings("unused")
	@Test
	public void test_insert_null(){
		DdzSearchLogDO searchLog = new DdzSearchLogDO() ;
		searchLog.setAlipayId("alfdeihf") ;
		searchLog.setUid("uid") ;
		
		
		searchLog.setPrice(new BigDecimal("222.33")) ;
		searchLog.setSearchBrief("34re") ;
		searchLog.setSearchWay("keyword") ;
		long id = ddzSearchLogDAO.insertLog(searchLog) ;
		int a  = 5;
	}
}
