package com.doucome.corner.biz.core.utils;

import com.doucome.corner.biz.core.sms.model.SmsMtDO;
import com.doucome.corner.biz.dal.dataobject.SmsLogDO;

public class SmsUtils {

	public static SmsLogDO buildLog(SmsMtDO mt , String resultCode){
		SmsLogDO log = new SmsLogDO() ;
		log.setBatId(mt.getBatId()) ;
		log.setBusinessId(mt.getBusinessId().getValue()) ;
		log.setMessageKey(mt.getMessageKey()) ;
		log.setMsgParameter(JacksonHelper.toJSON(mt.getMsgParameter())) ;
		log.setResultCode(resultCode) ;
		log.setToMobile(mt.getToMobile()) ;
		return log ;
	}
}
