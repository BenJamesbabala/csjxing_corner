package com.doucome.corner.biz.dal.dao.ibatis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionItemDAO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:corner/bean/biz-common.xml" ,
		"classpath:corner/bean/dcome/biz-dcome-dao.xml",
		"classpath:corner/bean/biz-dao.xml" ,
		"classpath:corner/bean/biz-datasource.xml" ,
	})
public class DcPromotionItemDAOTest extends AbstractBaseJUnit4Test {
	@Autowired
	private DcPromotionItemDAO dcPrmotionItemDAO;
	
	@Test
	public void testInsertPromotionItem() {
//		for(int i = 100 ;i< 200 ;i++){
			DcPromotionItemDO promotionItemDO = new DcPromotionItemDO();
			promotionItemDO.setPromotionId(7L);
			promotionItemDO.setItemId(100006L);//Long.valueOf(i));
			promotionItemDO.setUserId(10003L);
			promotionItemDO.setUserNick("ze2200");
			//promotionItemDO.setRateCount(RandomUtils.nextInt(100));
			Long id = dcPrmotionItemDAO.insertPromotionItem(promotionItemDO);
			Assert.assertNotNull(id);
//		}
		
	}
	
	@Test
	public void testGetByPromIdAndExtItemId() {
		//DcPromotionItemDO temp = dcPrmotionItemDAO.getByPromIdAndExtItemId(10L, "17936108970");
		//Assert.assertNotNull(temp);
	}
	
//	@Test
//	public void testGetPromotionTopRankItemsWithPage() {
//		List<DcItemDO> dcItems = dcPrmotionItemDAO.getPromotionTopRankItemsWithPage(0L, 0, 10);
//		Assert.assertNotNull(dcItems);
//	}
//	
//	@Test
//	public void testGetItemInPkPromotions() {
//		List<DcPromotionDO> promotions = dcPrmotionItemDAO.getItemInPkPromotions(0L);
//		Assert.assertNotNull(promotions);
//	}
}