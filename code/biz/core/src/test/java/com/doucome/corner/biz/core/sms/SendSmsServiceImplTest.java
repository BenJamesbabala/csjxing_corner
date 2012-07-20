package com.doucome.corner.biz.core.sms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.sms.model.SmsBusinessEnums;
import com.doucome.corner.biz.core.sms.model.SmsMtDO;
import com.doucome.corner.biz.core.sms.model.SmsMtResult;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-core-test.xml" 
	})
public class SendSmsServiceImplTest extends AbstractBaseJUnit4Test {
	
	@Autowired
	private SendSmsService sendSmsService ;
	
	/**
	 * 
	 */
	@Test
	public void test_sendMessage(){
		
		SmsMtDO mt = new SmsMtDO();
		mt.setBusinessId(SmsBusinessEnums.DDZ_SETTLE_SMS) ;
		mt.setMsgParameter(new String[]{"13.22","csjxin**com"}) ;
		mt.setToMobile("13777840845") ;
		
		SmsMtResult r = sendSmsService.sendMessage(mt) ;
	}

}
