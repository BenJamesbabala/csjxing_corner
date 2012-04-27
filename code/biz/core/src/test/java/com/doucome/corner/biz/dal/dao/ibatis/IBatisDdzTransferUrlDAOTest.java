package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.DdzTransferUrlDAO;
import com.doucome.corner.biz.dal.dataobject.DdzTransferUrlDO;
import com.doucome.corner.test.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/zhe/biz-zhe-dao.xml",
		"classpath:corner/bean/biz-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class IBatisDdzTransferUrlDAOTest extends AbstractBaseJUnit4Test {

	@Autowired
	private DdzTransferUrlDAO ddzTransferUrlDAO ;
	
	@SuppressWarnings("unused")
	@Test
	public void test_insert(){
		DdzTransferUrlDO transferUrl = new DdzTransferUrlDO() ;
		transferUrl.setAlipayId("alipay") ;
		transferUrl.setCommission(new BigDecimal("222")) ;
		transferUrl.setCommissionRate(new BigDecimal("323")) ;
		transferUrl.setNumIid(43443L) ;
		transferUrl.setPrice(new BigDecimal("434")) ;
		transferUrl.setUid("uidus") ;
		long id = ddzTransferUrlDAO.insertTransferUrl(transferUrl) ;
		int a = 5;
	}
}
