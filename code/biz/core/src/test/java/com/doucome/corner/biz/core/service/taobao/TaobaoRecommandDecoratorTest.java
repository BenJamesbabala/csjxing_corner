package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoRecommendTypeEnums;
import com.doucome.corner.biz.core.taobao.model.TaobaoRecommendItemCondition;
import com.doucome.corner.test.AbstractBaseJUnit4Test;

@ContextConfiguration(locations = { "classpath:corner/bean/biz-remote.xml",
		"classpath:corner/bean/biz-service.xml", "classpath:corner/bean/biz-common.xml", })
public class TaobaoRecommandDecoratorTest extends AbstractBaseJUnit4Test  {

	@Autowired
	private TaobaoRecommandDecorator taobaoRecommandDecorator ;
	
	@Test
	public void test_getRecommandItems(){
		
		TaobaoRecommendItemCondition condition = new TaobaoRecommendItemCondition() ;
		condition.setCount(10L) ;
		condition.setItemId(9841891789L) ;
		condition.setRecommendType(TaobaoRecommendTypeEnums.SAME_STYLE) ;
		List<TaobaoFavoriteItemDTO> items = taobaoRecommandDecorator.getRecommandItems(condition) ;
		
		
		int a = 5 ;
	}
	
	@Test
	public void test_getPromotion(){
		TaobaoPromotionDisplayDTO p = taobaoRecommandDecorator.getPromotionUmp(13836204775L, null) ;
		System.out.println(p);
	}
}
