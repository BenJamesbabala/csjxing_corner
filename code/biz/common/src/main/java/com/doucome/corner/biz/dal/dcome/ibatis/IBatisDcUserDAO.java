package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dcome.DcUserDAO;

/**
 * @author ib 2012-7-28 ÏÂÎç10:13:58
 */
public class IBatisDcUserDAO extends SqlMapClientDaoSupport implements DcUserDAO {

    @Override
    public long insertUser(DcUserDO dcUserDO) {
        return (Long) getSqlMapClientTemplate().insert("DcUser.insertUser", dcUserDO);
    }

    @Override
    public DcUserDO queryUser(long userId) {
        return (DcUserDO) getSqlMapClientTemplate().queryForObject("DcUser.queryUser", userId);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<DcUserDO> queryUsers(List<Long> userIds) {
    	return (List<DcUserDO>) getSqlMapClientTemplate().queryForList("DcUser.queryUsers", userIds);
    }

    @Override
    public int updateLastLoginTime(long userId) {
        return getSqlMapClientTemplate().update("DcUser.updateLastLoginTime", userId);
    }

    @Override
    public int updateUser(DcUserDO dcUserDO) {
        return getSqlMapClientTemplate().update("DcUser.updateUser", dcUserDO);
    }

    @Override
    public DcUserDO queryUserByExternalId(String externalId, String externalPf) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("externalId", externalId);
        map.put("externalPf", externalPf);
        return (DcUserDO) getSqlMapClientTemplate().queryForObject("DcUser.queryUserByExternalId", map);
    }
}
