package com.doucome.corner.biz.dal.dao.ibatis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.condition.dcome.DcBidInfo;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;
import com.doucome.corner.biz.dal.dcome.DcAuctionItemDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcAuctionItemDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcAuctionItemDAO dcAuctionItemDAO;
	
	@Test
	public void testInsertAuctionItem() {
		DcAuctionItemDO auctionItem = new DcAuctionItemDO();
		auctionItem.setItemId(100051l);
		auctionItem.setItemTitle("【missaoy】2012欧美日韩版女手提斜挎波士顿宝蓝糖果色果冻包");
		auctionItem.setItemPrice(new BigDecimal("120"));
		auctionItem.setBaseIntegral(800);
		auctionItem.setGmtStart(new Date());
		auctionItem.setGmtEnd(new Date());
		Long id = dcAuctionItemDAO.insertAuctionItem(auctionItem);
		Assert.assertNotNull(id);
	}
	
	@Test
	public void testGetAuctionItem() {
		DcAuctionItemDO temp = dcAuctionItemDAO.getAuctionItem(1l);
		Assert.assertNotNull(temp);
	}
	
	@Test
	public void testUpdateBidInfo() {
		DcBidInfo bidInfo = new DcBidInfo();
		bidInfo.setUserId(10003l);
		bidInfo.setUserNick("ze2200");
		bidInfo.setIntegral(1000);
		Integer count = dcAuctionItemDAO.updateBidInfo(1l, bidInfo);
		Assert.assertTrue(count == 0);
	}
	
	@Test
	public void testGetAuctionItemInIng() {
		List<DcAuctionItemDO> auctionItems = dcAuctionItemDAO.getAuctionItemInIng(new Date(), 0, 10);
		Assert.assertNotNull(auctionItems);
	}
}
