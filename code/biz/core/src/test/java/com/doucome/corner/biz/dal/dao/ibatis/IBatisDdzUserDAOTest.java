package com.doucome.corner.biz.dal.dao.ibatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.enums.GenderEnums;
import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.dal.DdzUserDAO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.test.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/zhe/biz-zhe-dao.xml",
		"classpath:corner/bean/biz-dao.xml",
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class IBatisDdzUserDAOTest extends AbstractBaseJUnit4Test {

	@Autowired
	private DdzUserDAO ddzUserDAO ;
	
	@Test
	public void test_insert(){
		DdzUserDO user = new DdzUserDO() ;
		user.setEmail("csjxing@163.com") ;
		user.setGender(GenderEnums.MALE.getValue()) ;
		user.setLoginId("bluecol") ;
		user.setUid("uid032323") ;
		user.setMd5Password("dwwefewfefe") ;
		user.setTaobaoId("bluecol");
		user.setUserActive(TrueFalseEnums.TRUE.getValue()) ;
		
		
		ddzUserDAO.insertUser(user) ;
	}
	
	/**
	 * 
	 */
	@Test
	public void test_getByLoginId(){
		DdzUserDO user = ddzUserDAO.queryByLoginId("bluecol") ;
		System.out.println(user);
	}
}
