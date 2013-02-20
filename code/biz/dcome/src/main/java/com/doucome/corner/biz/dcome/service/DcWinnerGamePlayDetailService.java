package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGamePlayDetailDO;

public interface DcWinnerGamePlayDetailService {

	long insertPlayDetail(DcWinnerGamePlayDetailDO playDetail) ;
	
	int countTodayPlayDetaiByUser(long userId) ;
}
