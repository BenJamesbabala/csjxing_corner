package com.doucome.corner.biz.dcome.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGamePlayDetailDO;
import com.doucome.corner.biz.dal.dcome.DcWinnerGamePlayDetailDAO;
import com.doucome.corner.biz.dcome.service.DcWinnerGamePlayDetailService;

public class DcWinnerGamePlayDetailServiceImpl implements DcWinnerGamePlayDetailService {
 
	@Autowired 
	private DcWinnerGamePlayDetailDAO dcWinnerGamePlayDetailDAO ;

	@Override
	public long insertPlayDetail(DcWinnerGamePlayDetailDO playDetail) {
		return dcWinnerGamePlayDetailDAO.insertPlayDetail(playDetail) ;
	}

	@Override
	public int countTodayPlayDetaiByUser(long userId) {
		Date date = new Date() ;
		Date gmtCreateStart = DateUtils.setTime(date, 0, 0, 0) ;
		Date gmtCreateEnd = DateUtils.setTime(date, 23, 59, 59) ;
		return dcWinnerGamePlayDetailDAO.countPlayDetaiByUser(userId, gmtCreateStart, gmtCreateEnd) ;
	}
	
	
}
