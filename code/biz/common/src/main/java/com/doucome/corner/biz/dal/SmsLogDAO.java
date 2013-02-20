package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.SmsLogDO;

/**
 * 短信日志
 * @author langben 2012-9-10
 *
 */
public interface SmsLogDAO {

	/**
	 * 插入一条短信日志
	 * @param sms
	 * @return
	 */
	long insertSms(SmsLogDO sms) ;
}
