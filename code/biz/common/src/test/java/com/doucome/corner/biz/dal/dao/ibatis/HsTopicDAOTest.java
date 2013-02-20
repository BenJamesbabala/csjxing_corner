package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.condition.dcome.hs.HsTopicCondition;
import com.doucome.corner.biz.dal.horoscope.HsTopicDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsTopicDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/horoscope/biz-hs-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class HsTopicDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private HsTopicDAO hsTopicDAO;
	
	@Test
	public void testQueryHsTopic() {
		HsTopicDO topicDO = hsTopicDAO.queryHsTopic(1l);
		Assert.assertNull(topicDO);
	}
	
	@Test
	public void testQueryHsTopics() {
		HsTopicCondition condition = new HsTopicCondition();
		List<HsTopicDO> topicDOs = hsTopicDAO.queryHsTopics(condition, 0, 10);
		Assert.assertEquals(0, topicDOs.size());
		condition.setGmtDay(new Date());
		topicDOs = hsTopicDAO.queryHsTopics(condition, 0, 10);
		Assert.assertEquals(0, topicDOs.size());
	}
}
