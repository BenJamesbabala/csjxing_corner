package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dcome.DcUserDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcUserDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcUserDAO dcUserDAO;
	
	@Test
	public void testInsertUser() {
		DcUserDO userDO = new DcUserDO();
		userDO.setExternalId("kdfjalkjflajfadejfk");
		userDO.setExternalPf("QZONE");
		userDO.setNick("nicktest");
		userDO.setGender("F");
		userDO.setSource("QQ");
		userDO.setIntegralCount(20);
		Long userId = dcUserDAO.insertUser(userDO);
		int count = dcUserDAO.deleteUser(userId);
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void testQueryUser() {
		DcUserDO user = dcUserDAO.queryUser(10003l);
		Assert.assertNotNull(user);
	}
    
	@Test
	public void testQueryUsers() {
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(10003l);
		List<DcUserDO> users = dcUserDAO.queryUsers(userIds);
		Assert.assertEquals(1, users.size());
	}
	
	@Test
	public void testQueryUserByExternalId() {
		DcUserDO user = dcUserDAO.queryUserByExternalId("AE846D3D366242BAB4375103AAC2CACF", "qq");
		Assert.assertNotNull(user);
	}
}
