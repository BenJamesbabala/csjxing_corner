package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dcome.DcItemDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcItemDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcItemDAO dcItemDAO;
	
	@Test
	public void testGetItemByCreatorAndExtItemId() {
		DcItemDO itemDO = dcItemDAO.getItemByCreatorAndExtItemId(1L, "16119315297");
		Assert.assertNotNull(itemDO);
	}
	
	public void testGetItemsByExtId() {
		List<DcItemDO> dcItemDOs = dcItemDAO.getItemsByExtId("16119315297");
		Assert.assertNotNull(dcItemDOs);
	}
	
	@Test
	public void testQueryItemById() {
		DcItemDO item = dcItemDAO.queryItemById(10L);
		Assert.assertNotNull(item);
	}
	
	@Test
	public void testQueryItemsByIds() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(10l);
		List<DcItemDO> items = dcItemDAO.queryItemsByIds(ids);
		Assert.assertNotNull(items);
	}
	
	@Test
	public void testGueryItemIdsWithPagination() {
		DcItemSearchCondition itemCondition = new DcItemSearchCondition() ;
		itemCondition.setGenWay("U") ;
		itemCondition.setItemStatus("N");
		itemCondition.setTimeStamp(System.currentTimeMillis());
		List<Long> ids = dcItemDAO.queryItemIdsWithPagination(itemCondition, 1, 15);
		Assert.assertTrue(ids.size() > 0);
		itemCondition.setGenWay("P");
		itemCondition.setTimeStamp(null);
		ids = dcItemDAO.queryItemIdsWithPagination(itemCondition, 1, 15);
		Assert.assertTrue(ids.size() == 15);
	}
	
	@Test
	public void test_incrTaokeSellCount(){
		long itemId = 100062L ;
		DcItemDO i = dcItemDAO.queryItemById(itemId) ;
		int effectCount = dcItemDAO.incrTaokeSellCount(100062L, 2) ;
		DcItemDO ii = dcItemDAO.queryItemById(itemId) ;
		Assert.assertTrue((ii.getTaokeSellCount() - i.getTaokeSellCount()) == 2) ;
	}
}
