package com.doucome.corner.biz.zhe.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.DdzSearchLogDAO;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;
import com.doucome.corner.biz.zhe.service.DdzSearchLogService;

public class DdzSearchLogServiceImpl implements DdzSearchLogService {

	private static final Log logger = LogFactory.getLog(DdzSearchLogServiceImpl.class);

	@Autowired
	private DdzSearchLogDAO ddzSearchLogDAO;

	@Override
	public long createLog(DdzSearchLogDO searchLog) {
		try {
			return ddzSearchLogDAO.insertLog(searchLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return 0;
	}

}
