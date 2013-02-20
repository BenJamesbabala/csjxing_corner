package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGameConfigDO;
import com.doucome.corner.biz.dal.dcome.DcWinnerGameConfigDAO;

public class IBatisDcWinnerGameConfigDAO extends SqlMapClientDaoSupport implements DcWinnerGameConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DcWinnerGameConfigDO> queryConfigs() {
		return getSqlMapClientTemplate().queryForList("DcWinnerGameConfig.queryConfigs") ;
	}

	@Override
	public DcWinnerGameConfigDO queryConfigByCardName(String cardName) {
		return (DcWinnerGameConfigDO)getSqlMapClientTemplate().queryForObject("DcWinnerGameConfig.queryConfigByCardName" , cardName) ;
	}

}
