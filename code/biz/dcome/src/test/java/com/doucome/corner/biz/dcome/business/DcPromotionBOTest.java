package com.doucome.corner.biz.dcome.business;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcPromotionBOTest extends AbstractBaseJUnit4Test{

	@Autowired
	private DcPromotionBO dcPromotionBO;
	
	@Test
	public void testGetDefaultRateItems(){
		List<DcPromotionItemDTO> list = dcPromotionBO.getDefaultItems(10L, new Pagination(1)) ;
		System.out.println(list);
	}

	@Test
	public void addRate(){
//		DcPromotionRateDetailDO rateDetail = new DcPromotionRateDetailDO() ;
//		
//		rateDetail.setPromotionId(100L);
//		rateDetail.setRateUserId(100L) ;
//		rateDetail.setRateUserNick("¿À±æ¿À¬•");
//		dcPromotionBO.addRate(rateDetail) ;
	}
}
