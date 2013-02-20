package com.doucome.corner.biz.dal.namefate.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.namefate.NamefateUserDAO;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateUserDO;

/**
 * 
 * @author langben 2013-1-1
 *
 */
public class IBatisNamefateUserDAO extends SqlMapClientDaoSupport implements NamefateUserDAO {

    @Override
    public Long insertUser(NamefateUserDO user) throws DuplicateKeyException {
        return (Long) getSqlMapClientTemplate().insert("NamefateUser.insertUser", user);
    }

    @Override
    public NamefateUserDO queryUser(Long userId) {
        return (NamefateUserDO) getSqlMapClientTemplate().queryForObject("NamefateUser.queryUser", userId);
    }
   
    @Override
    public NamefateUserDO queryUserByExternalId(String externalId) {
        return (NamefateUserDO) getSqlMapClientTemplate().queryForObject("NamefateUser.queryUserByExternalId", externalId);
    }
    
    @Override
	public int updateNewGuide(long userId, long newValue) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("newGuide", newValue);
		return getSqlMapClientTemplate().update("DcUser.updateNewGuide" , map) ;
	}

    @Override
    public int updateLoginInfo(NamefateUserDO user) {
        return getSqlMapClientTemplate().update("NamefateUser.updateLoginInfo", user);
    }
    
    @Override
    public int updateFollowQzone(Long userId) {
    	return getSqlMapClientTemplate().update("NamefateUser.updateFollowQzone", userId);
    }

	@Override
	public int incrUnreadMsgCount(Long userId) {
		return getSqlMapClientTemplate().update("NamefateUser.incrUnreadMsgCount" , userId) ;
	}
    
}
