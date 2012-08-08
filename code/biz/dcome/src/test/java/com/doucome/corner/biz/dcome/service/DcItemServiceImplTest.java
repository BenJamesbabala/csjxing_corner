package com.doucome.corner.biz.dcome.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnums;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;


@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcItemServiceImplTest extends AbstractBaseJUnit4Test {

	@Autowired
	DcItemService dcItemService ;
	
	@Test
	public void testCreate(){
		for(int i=0 ;i<30 ;i++){
			DcItemDO item = new DcItemDO() ;
			item.setCategoryId(4L) ;
			item.setClickUrl("http://baidu.com") ;
			item.setComments(8) ;
			item.setItemDesc("描述") ;
			item.setItemPrice(new BigDecimal("20.90")) ;
			item.setItemTitle("草家*美白保湿玫瑰花水 保湿补水美白抗皱爽肤水/化妆水 120ml" + i) ;
			item.setLoves(10) ;
			item.setPicUrls("http://img03.taobaocdn.com/bao/uploaded/i3/T1Rm1AXglQXXbm4aQ0_034307.jpg") ;
			item.setSells(20) ;
			item.setSource(DcItemSourceEnums.TAOBAO.getValue()) ;
			item.setSrcUrl("http://item.taobao.com/item.htm?id=14831179443&ali_trackid=2:mm_30421514_0_0:1343444535_4z5_549628964&spm=2014.12526345.1.0") ;
			Long id = dcItemService.createItem(item);
			System.out.println(id);
		}
	}
}
