package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserClickStatisticsDO;
import com.doucome.corner.biz.dal.dcome.DcUserClickStatisticsDAO;

/**
 * 
 * @author langben 2012-9-15
 *
 */
public class IBatisDcUserClickStatisticsDAO extends SqlMapClientDaoSupport implements DcUserClickStatisticsDAO {

	@Override
	public long insertClickStatistics(DcUserClickStatisticsDO clickStatistics) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DcUserClickStatistics.insertClickStatistics" , clickStatistics)) ;
	}

	@Override
	public DcUserClickStatisticsDO queryClickStatisticsByUser(Long userId, String clickType) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("userId", userId) ;
		condition.put("clickType", clickType) ;
		return (DcUserClickStatisticsDO)getSqlMapClientTemplate().queryForObject("DcUserClickStatistics.queryClickStatisticsByUser" , condition) ;
	}

	@Override
	public int incrClickStatisticsByUser(Long userId, String clickType) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("userId", userId) ;
		condition.put("clickType", clickType) ;
		return getSqlMapClientTemplate().update("DcUserClickStatistics.incrClickStatisticsByUser", condition) ;
	}

}
