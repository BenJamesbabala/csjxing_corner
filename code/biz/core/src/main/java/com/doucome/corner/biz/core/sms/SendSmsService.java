package com.doucome.corner.biz.core.sms;

import com.doucome.corner.biz.core.sms.model.SmsMtDO;
import com.doucome.corner.biz.core.sms.model.SmsMtResult;


/**
 * 发送短信服务
 * @author shenjia.caosj 2012-7-19
 *
 */
public interface SendSmsService {

	/**
	 * 发送短信
	 * @param smsSendDO
	 * @return
	 */
	SmsMtResult sendMessage(SmsMtDO mtDO) ;
	
}
