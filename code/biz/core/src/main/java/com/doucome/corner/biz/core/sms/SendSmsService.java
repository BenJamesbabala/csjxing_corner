package com.doucome.corner.biz.core.sms;

import com.doucome.corner.biz.core.sms.model.SmsMtDO;
import com.doucome.corner.biz.core.sms.model.SmsMtResult;


/**
 * ���Ͷ��ŷ���
 * @author shenjia.caosj 2012-7-19
 *
 */
public interface SendSmsService {

	/**
	 * ���Ͷ���
	 * @param smsSendDO
	 * @return
	 */
	SmsMtResult sendMessage(SmsMtDO mtDO) ;
	
}
