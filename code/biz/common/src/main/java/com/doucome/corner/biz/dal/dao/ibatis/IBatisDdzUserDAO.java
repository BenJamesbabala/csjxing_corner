package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzUserDAO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

public class IBatisDdzUserDAO extends SqlMapClientDaoSupport implements DdzUserDAO {

    @Override
    public int insertUser(DdzUserDO user) {
        return NumberUtils.parseInt((Integer) getSqlMapClientTemplate().insert("ddzUser.insertUser", user));
    }

    @Override
    public DdzUserDO queryByUid(String uid) {
        return (DdzUserDO) getSqlMapClientTemplate().queryForObject("ddzUser.queryByUid", uid);
    }

    @Override
    public DdzUserDO queryByLoginId(String loginId) {
        return (DdzUserDO) getSqlMapClientTemplate().queryForObject("ddzUser.queryByLoginId", loginId);
    }

    @Override
    public DdzUserDO queryByLoginIdAndPassword(String loginId, String md5Password) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("loginId", loginId);
        condition.put("md5Password", md5Password);
        return (DdzUserDO) getSqlMapClientTemplate().queryForObject("ddzUser.queryByLoginIdAndPassword", condition);
    }

    @Override
    public void updateUser(DdzUserDO user) {
        getSqlMapClientTemplate().update("ddzUser.updateUser", user);

    }

    @Override
    public void updateLastLoginTime(String uid) {
        getSqlMapClientTemplate().update("ddzUser.updateLastLoginTime", uid);
    }

	@Override
	public int updateAlipayIdByLoginId(String loginId, String alipayId) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("loginId", loginId) ;
		condition.put("alipayId", alipayId) ;
		return getSqlMapClientTemplate().update("ddzUser.updateAlipayIdByLoginId" , condition) ;
	}

	@Override
	public int incrModificationCount(String loginId) {
		return getSqlMapClientTemplate().update("ddzUser.incrModificationCount" , loginId) ;
	}

}
