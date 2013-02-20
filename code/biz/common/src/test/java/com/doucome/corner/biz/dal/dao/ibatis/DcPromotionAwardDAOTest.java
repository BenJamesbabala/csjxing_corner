package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionAwardDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcPromotionAwardDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcPromotionAwardDAO dcPromotionAwardDAO;
	
	@Test
	public void testInsetAward() {
		DcPromotionAwardDO award = new DcPromotionAwardDO();
		award.setPromotionId(3L);
		award.setPromotionItemId(100L);
		award.setUserId(10003L);
		award.setItemId(100001l);
		award.setDelName("ze2200");
		award.setSendStatus("N");
		award.setDelProvince("test");
		award.setDelCity("test");
		award.setDelArea("test");
		award.setDelAddr("test");
		award.setReviewStatus("U");
		award.setCheckCode("test");
		
		Long id = dcPromotionAwardDAO.insertAward(award);
		Assert.assertNotNull(id);
	}
	
	@Test
	public void testqueryAwardByUserId() {
		List<DcPromotionAwardDO> awards = dcPromotionAwardDAO.queryAwardByUserId(10003l);
		Assert.assertNotNull(awards);
	}
	
	@Test
	public void testqueryAwardsWithPagination() {
		List<DcPromotionAwardDO> awards = dcPromotionAwardDAO.queryAwardsWithPagination(new DcPromotionAwardCondition(), 1, 10);
		Assert.assertNotNull(awards);
	}
	
	@Test
	public void testUpdateAwardUserInfo() {
		
	}
}
