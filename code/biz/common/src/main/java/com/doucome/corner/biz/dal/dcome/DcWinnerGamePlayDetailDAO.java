package com.doucome.corner.biz.dal.dcome;
import java.util.Date;

import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGamePlayDetailDO;

public interface DcWinnerGamePlayDetailDAO {

	/**
	 * 
	 * @param playDetail
	 * @return
	 */
	long insertPlayDetail(DcWinnerGamePlayDetailDO playDetail) ;
	
	/**
	 * 
	 * @param userId
	 * @param gmtCreateStart
	 * @param gmtCreateEnd
	 * @return
	 */
	int countPlayDetaiByUser(long userId , Date gmtCreateStart , Date gmtCreateEnd) ;
}
