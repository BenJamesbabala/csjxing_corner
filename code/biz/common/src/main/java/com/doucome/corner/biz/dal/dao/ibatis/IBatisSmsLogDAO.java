package com.doucome.corner.biz.dal.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.SmsLogDAO;
import com.doucome.corner.biz.dal.dataobject.SmsLogDO;

public class IBatisSmsLogDAO extends SqlMapClientDaoSupport implements SmsLogDAO {

	@Override
	public long insertSms(SmsLogDO sms) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("SmsLog.insertSms" , sms)) ;
	}

}
