package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.SmsLogDO;

/**
 * ������־
 * @author langben 2012-9-10
 *
 */
public interface SmsLogDAO {

	/**
	 * ����һ��������־
	 * @param sms
	 * @return
	 */
	long insertSms(SmsLogDO sms) ;
}
