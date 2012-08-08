package com.doucome.corner.biz.core.qq;

import java.util.HashMap;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.qq.constant.QqApiConstant;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations = { "classpath:biz-core-test.xml" })
public class QqQueryServiceTest extends AbstractBaseJUnit4Test{

	@Autowired
	private QqQueryService qqQueryService;
	
	@Test
	public void test_csecWords(){
		
		HashMap m = new HashMap() ;
		m.put("content", "°¢Ë¹Àû¿µÓ¹6") ;
		//qqQueryService.callQqApi(QqApiConstant.SCRIPT_WORD_FILTER, params)
	}
}
