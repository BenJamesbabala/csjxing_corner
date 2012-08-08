package com.doucome.corner.biz.core.service.taobao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.taobao.constant.TaobaoUserConstant;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaoFields;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations = { "classpath:biz-core-test.xml", })
public class TaobaoServiceDecoratorTest extends AbstractBaseJUnit4Test{

	@Autowired
	private TaobaoServiceDecorator taobaoServiceDecorator ;
	
	@Test
	public void test_getUser(){
		
		TaobaoUserDTO user = taobaoServiceDecorator.getUser("yefniu", new String[]{
				TaobaoUserConstant.alipay_account , 
				TaobaoUserConstant.sex
		}, "61005269709d98521bdce063eb8ac1a6ec1e7bc3bc45065126826491") ;
		int a =  5 ;
	}
	
	@Test
	public void test_getItem(){
		
		TaobaoItemDTO item = taobaoServiceDecorator.getItem(15360521459L, TaobaoFields.taobao_item_fields_full) ;
		
		int a =  5 ;
	}
}
