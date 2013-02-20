package com.doucome.corner.biz.dal.wryneck.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.wryneck.WryneckUserDAO;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckUserDO;

/**
 * 
 * @author langben 2013-1-1
 *
 */
public class IBatisWryneckUserDAO extends SqlMapClientDaoSupport implements WryneckUserDAO {

    @Override
    public Long insertUser(WryneckUserDO user) throws DuplicateKeyException {
        return (Long) getSqlMapClientTemplate().insert("WryneckUser.insertUser", user);
    }

    @Override
    public WryneckUserDO queryUser(Long userId) {
        return (WryneckUserDO) getSqlMapClientTemplate().queryForObject("WryneckUser.queryUser", userId);
    }
   
    @Override
    public WryneckUserDO queryUserByExternalId(String externalId) {
        return (WryneckUserDO) getSqlMapClientTemplate().queryForObject("WryneckUser.queryUserByExternalId", externalId);
    }

    @Override
    public int updateLoginInfo(WryneckUserDO user) {
        return getSqlMapClientTemplate().update("WryneckUser.updateLoginInfo", user);
    }
    
    @Override
    public int updateFollowQzone(Long userId) {
    	return getSqlMapClientTemplate().update("WryneckUser.updateFollowQzone", userId);
    }

	@Override
	public int updateTestResult(Long userId, String result) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("userId", userId) ;
		map.put("testResult", result) ;
		return getSqlMapClientTemplate().update("WryneckUser.updateTestResult", map);
	}
    
}
