package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.horoscope.HsUserDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsUserDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/horoscope/biz-hs-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class HsUserDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private HsUserDAO hsUserDAO;
	
	@Test
	public void testQueryUser() {
		HsUserDO user = hsUserDAO.queryUser(10003l);
		Assert.assertNotNull(user);
	}
    
	@Test
	public void testQueryUsers() {
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(10003l);
		List<HsUserDO> users = hsUserDAO.queryUsers(userIds);
		Assert.assertEquals(1, users.size());
	}
	
	@Test
	public void testQueryUserByExternalId() {
		HsUserDO user = hsUserDAO.queryUserByExternalId("355DCDBE56E46EB2FDC55DB8BA932455");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testUpdateHsId() {
		Integer count = hsUserDAO.updateHsId(10003l, null);
		Assert.assertNotNull(count);
	}
}
