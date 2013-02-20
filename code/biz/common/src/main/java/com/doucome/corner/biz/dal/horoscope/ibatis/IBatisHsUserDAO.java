package com.doucome.corner.biz.dal.horoscope.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.horoscope.HsUserDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsUserDO;

/**
 * ÐÇ×ù±´±´ÓÃ»§dao
 * @author ze2200
 *
 */
public class IBatisHsUserDAO extends SqlMapClientDaoSupport implements HsUserDAO {

    @Override
    public Long insertUser(HsUserDO stUserDO) throws DuplicateKeyException {
        return (Long) getSqlMapClientTemplate().insert("HsUser.insertUser", stUserDO);
    }

    @Override
    public HsUserDO queryUser(Long userId) {
        return (HsUserDO) getSqlMapClientTemplate().queryForObject("HsUser.queryUser", userId);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<HsUserDO> queryUsers(List<Long> userIds) {
    	return (List<HsUserDO>) getSqlMapClientTemplate().queryForList("HsUser.queryUsers", userIds);
    }
    
    @Override
    public HsUserDO queryUserByExternalId(String externalId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("externalId", externalId);
        return (HsUserDO) getSqlMapClientTemplate().queryForObject("HsUser.queryUserByExternalId", map);
    }

    @Override
    public int updateLoginInfo(HsUserDO user) {
        return getSqlMapClientTemplate().update("HsUser.updateLoginInfo", user);
    }
    
    @Override
    public int updateFollowQzone(Long userId) {
    	return getSqlMapClientTemplate().update("HsUser.updateFollowQzone", userId);
    }
    
    @Override
    public Integer updateHsId(Long userId, Integer hsId) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("userId", userId);
    	params.put("hsId", hsId);
        return (Integer) getSqlMapClientTemplate().update("HsUser.updateHsId", params);
    }
}
