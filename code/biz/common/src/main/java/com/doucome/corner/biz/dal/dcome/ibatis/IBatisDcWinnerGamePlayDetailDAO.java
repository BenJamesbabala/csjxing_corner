package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGamePlayDetailDO;
import com.doucome.corner.biz.dal.dcome.DcWinnerGamePlayDetailDAO;

public class IBatisDcWinnerGamePlayDetailDAO extends SqlMapClientDaoSupport implements DcWinnerGamePlayDetailDAO {

	@Override 
	public long insertPlayDetail(DcWinnerGamePlayDetailDO playDetail) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DcWinnerGamePlayDetail.insertPlayDetail" , playDetail));
	}

	@Override
	public int countPlayDetaiByUser(long userId, Date gmtCreateStart , Date gmtCreateEnd) {
		Map<String , Object> map = new HashMap<String,Object>() ;
		map.put("userId", userId) ;
		map.put("gmtCreateStart", gmtCreateStart) ;
		map.put("gmtCreateEnd", gmtCreateEnd) ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcWinnerGamePlayDetail.countPlayDetaiByUser" , map)) ;
	}

}
