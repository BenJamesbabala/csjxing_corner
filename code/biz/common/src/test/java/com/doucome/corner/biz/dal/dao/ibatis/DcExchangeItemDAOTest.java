package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;
import com.doucome.corner.biz.dal.dcome.DcExchangeItemDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcExchangeItemDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcExchangeItemDAO dcExchangeItemDAO;
	
	@Test
	public void testInsertExchangeItem() {
		DcExchangeItemDO exchangeItem = new DcExchangeItemDO();
		exchangeItem.setItemId(101062L);
		exchangeItem.setItemTitle("243ÌðÃÀ¹«Ö÷¿îÔ²Áì´ÌÐå¸´¹ÅÀ®°ÈÐäÆ´½ÓÍøÉ´Ñ©·ÄÉÀ ±ðÖÂÒÂ°Ú³¬ÏÉ");
		exchangeItem.setItemPrice(new BigDecimal("85.00"));
		exchangeItem.setItemPictures("/upload/product/201210/11/1339161085498481.jpg");
		exchangeItem.setExIntegral(670);
		exchangeItem.setExCount(10);
		Long id = dcExchangeItemDAO.insertExchangeItem(exchangeItem);
		Assert.assertNotNull(id);
	}
	
	@Test
	public void testGetExchangeItem() {
		DcExchangeItemDO exchangeItem = dcExchangeItemDAO.getExchangeItem(1l);
		Assert.assertNotNull(exchangeItem);
	}
	
	@Test
	public void testDecExchangeNum() {
		Integer count = dcExchangeItemDAO.decExchangeNum(1l, 1);
		Assert.assertTrue(count == 1);
		count = dcExchangeItemDAO.decExchangeNum(2l, 2);
		Assert.assertTrue(count == 0);
	}
	
	@Test
	public void testQueryExchangeItemsPage() {
		DcExchangeItemCondition condition = new DcExchangeItemCondition();
		List<DcExchangeItemDO> exchangeItems = dcExchangeItemDAO.queryExchangeItemsPage(condition, 1, 10);
		Assert.assertNotNull(exchangeItems.size() > 0);
		condition.setStatus("ON");
		exchangeItems = dcExchangeItemDAO.queryExchangeItemsPage(condition, 1, 10);
		Assert.assertTrue(exchangeItems.size() > 0);
	}
	
	@Test
	public void testCountExchangeItems() {
		DcExchangeItemCondition condition = new DcExchangeItemCondition();
		int count = dcExchangeItemDAO.countExchangeItems(condition);
		Assert.assertTrue(count > 0);
		condition.setStatus("EN");
		count = dcExchangeItemDAO.countExchangeItems(condition);
		Assert.assertTrue(count == 0);
	}
}
