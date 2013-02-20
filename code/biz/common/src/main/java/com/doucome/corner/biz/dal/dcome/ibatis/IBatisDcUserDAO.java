package com.doucome.corner.biz.dal.dcome.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dcome.DcUserDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * @author ib 2012-7-28 ÏÂÎç10:13:58
 */
public class IBatisDcUserDAO extends SqlMapClientDaoSupport implements DcUserDAO {

    @Override
    public long insertUser(DcUserDO dcUserDO) {
        return (Long) getSqlMapClientTemplate().insert("DcUser.insertUser", dcUserDO);
    }
    
    @Override
    public int deleteUser(Long userId) {
    	return (Integer) getSqlMapClientTemplate().delete("DcUser.deleteUser", userId);
    }

    @Override
    public DcUserDO queryUser(Long userId) {
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
	public int updateLastLoginTimeAndTrace(long userId, String trace) {
    	Map<String,Object> condition = new HashMap<String,Object>() ;
    	condition.put("userId", userId) ;
    	condition.put("lastLoginTrace", trace) ;
		return getSqlMapClientTemplate().update("DcUser.updateLastLoginTimeAndTrace" , condition) ;
	}
    
    @Override
    public int updateLastCheckinTime(long userId, int checkInCount) {
    	Map<String,Object> condition = new HashMap<String,Object>() ;
    	condition.put("userId", userId);
    	condition.put("checkInCount", checkInCount);
        return getSqlMapClientTemplate().update("DcUser.updateLastCheckinTime", condition);
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

	@Override
	public int incrIntegralByUser(long userId, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("count", count);
		return getSqlMapClientTemplate().update("DcUser.incrIntegralByUser" , map) ;
	}

	@Override
	public int decrIntegralByUser(long userId, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("count", count);
		return getSqlMapClientTemplate().update("DcUser.decrIntegralByUser" , map) ;
	}

	@Override
	public int incrGoldEggByUser(long userId, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("count", count);
		return getSqlMapClientTemplate().update("DcUser.incrGoldEggByUser" , map) ;
	}

	@Override
	public int decrGoldEggByUser(long userId, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("count", count);
		return getSqlMapClientTemplate().update("DcUser.decrGoldEggByUser" , map) ;
	}
	
	@Override
	public int incrUnreadMsgCountByUser(long userId, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("count", count);
		return getSqlMapClientTemplate().update("DcUser.incrUnreadMsgCountByUser" , map) ;
	}
	
	@Override
	public int clearUnreadMsgCountByUser(long userId) {
		return getSqlMapClientTemplate().update("DcUser.clearUnreadMsgCountByUser" , userId) ;
	}

	@Override
	public int updateNewGuide(long userId, long newValue) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("newGuide", newValue);
		return getSqlMapClientTemplate().update("DcUser.updateNewGuide" , map) ;
	}
	
	@Override
	public Integer updateDailyShare(Long userId) {
		return (Integer)getSqlMapClientTemplate().update("DcUser.updateDailyShare" , userId) ;
	}
	@Override
	public int unfrozenIntegralByUser(long userId, Integer integralCount) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("integralCount", integralCount);
		return getSqlMapClientTemplate().update("DcUser.unfrozenIntegralByUser" , map) ;
	}

	@Override
	public int frozenIntegralByUser(long userId, Integer integralCount) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("integralCount", integralCount);
		return getSqlMapClientTemplate().update("DcUser.frozenIntegralByUser" , map) ;
	}

	
	@Override
	public Integer updateFollowQzone(Long userId) {
		return (Integer)getSqlMapClientTemplate().update("DcUser.updateFollowQzone" , userId) ;
	}

	@Override
	public int descFrozenIntegralByUser(long userId, Integer integralCount) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("integralCount", integralCount);
		return getSqlMapClientTemplate().update("DcUser.descFrozenIntegralByUser" , map) ;
	}

	@Override
	public int updateNick(Long userId, String nick)
			throws DuplicateKeyException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("nick", nick);
		return getSqlMapClientTemplate().update("DcUser.updateNick" , map) ;
	}

	@Override
	public int incrWinnerScoreByUser(long userId, int score) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("score", score);
		return getSqlMapClientTemplate().update("DcUser.incrWinnerScoreByUser" , map) ;
	}

	@Override
	public int updateWinnerScoreToIntegralByUser(long userId) {
        return getSqlMapClientTemplate().update("DcUser.updateWinnerScoreToIntegralByUser" , userId) ;
	}

	@Override
	public int updateAlipayAccountByUser(long userId, String alipayAccount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("alipayAccount", alipayAccount) ;
		return getSqlMapClientTemplate().update("DcUser.updateAlipayAccountByUser" ,map) ;
	}

	@Override
	public int updateExtendDescByUser(long userId, String extendDesc) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("extendDesc", extendDesc) ;
		return getSqlMapClientTemplate().update("DcUser.updateExtendDescByUser" ,map) ;
	}
	
	@Override
	public int batchUpdateUserLevel(final List<DcUserDO> users) {
		if (CollectionUtils.isEmpty(users)) {
			return 0;
		}
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (DcUserDO user: users) {
					executor.update("DcUser.updateUserLevel", user);
				}
				return executor.executeBatch();
			}
		});
	}
}
