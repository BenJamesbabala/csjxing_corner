package com.doucome.corner.biz.dal.dao.ibatis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcGameRuleDO;
import com.doucome.corner.biz.dal.dcome.DcGameRuleDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;


@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcGameRuleDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcGameRuleDAO dcGameRuleDAO;
	@Test
	public void testInsertGameRule() {
		DcGameRuleDO gameRule = new DcGameRuleDO();
		gameRule.setUserId(10003l);
		gameRule.setType("EGG");
		gameRule.setTimeId("2012-09-18");
		gameRule.setStatus("R");
		gameRule.setPlayLimit(20);
		gameRule.setExchangeAmount(5);
		gameRule.setTodayPlayCount(2);
		gameRule.setTodayClickCount(0);
		long id = dcGameRuleDAO.insertGameRule(gameRule);
		System.out.println(id);
	}
	
	@Test
	public void testGetGameRule() {
		DcGameRuleDO gameRuleDO = dcGameRuleDAO.getGameRule(4l);
		Assert.assertNotNull(gameRuleDO);
	}
	
	@Test
	public void testGetUserGameRule() {
		DcGameRuleDO gameRuleDO = dcGameRuleDAO.getUserGameRule(10003l, "EGG", "2012-09-18");
		Assert.assertNotNull(gameRuleDO);
	}
	/**
	@Test
	public void testUpdateGameDataById() {
		DcGameRuleDO gameRule = new DcGameRuleDO();
		gameRule.setUserId(10003l);
		gameRule.setTodayPlayCount(2);
		gameRule.setTodayClickCount(0);
		int count = dcGameRuleDAO.updateGameDataByID(gameRule);
		Assert.assertEquals(1, count);
	}
	**/
}
