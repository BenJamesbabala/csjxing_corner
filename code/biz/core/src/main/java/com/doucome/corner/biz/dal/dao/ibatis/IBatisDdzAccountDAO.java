package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.core.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzAccountDAO;
import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;

/**
 * 类IBatisAccountDAO.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-3-4 下午07:02:59
 */
public class IBatisDdzAccountDAO extends SqlMapClientDaoSupport implements DdzAccountDAO {

    private static final String QUERY_BY_ACCOUNTID = "ZHE_ACCOUNT.queryByAccountId";
    private static final String QUERY_BY_UID       = "ZHE_ACCOUNT.queryByUid";
    private static final String QUERY_BY_ALIPAYID  = "ZHE_ACCOUNT.queryByAlipayId";
    private static final String INSERT_ACCOUNT     = "ZHE_ACCOUNT.insertAccount";
    private static final String UPDATE_ACCOUNT     = "ZHE_ACCOUNT.updateAccount";

    @Override
    public DdzAccountDO queryByAccountId(String accountId) {
        return (DdzAccountDO) getSqlMapClientTemplate().queryForObject(QUERY_BY_ACCOUNTID, accountId);
    }

    @Override
    public DdzAccountDO queryByAlipayId(String alipayId) {
        return (DdzAccountDO) getSqlMapClientTemplate().queryForObject(QUERY_BY_ALIPAYID, alipayId);
    }

    @Override
    public void insertAccount(DdzAccountDO accountDO) {
        getSqlMapClientTemplate().insert(INSERT_ACCOUNT, accountDO);
    }

    @Override
    public DdzAccountDO queryByUid(String uid) {
        return (DdzAccountDO) getSqlMapClientTemplate().queryForObject(QUERY_BY_UID, uid);
    }

    @Override
    public void updateAccount(DdzAccountDO accountDO) {
        getSqlMapClientTemplate().update(UPDATE_ACCOUNT, accountDO);

    }

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzAccountDO> queryAccountsWithPagination(DdzAccountSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("gmtCreateStart", searchCondition.getGmtCreateStart()) ;
		condition.put("gmtCreateEnd", searchCondition.getGmtCreateEnd()) ;
		condition.put("alipayId", searchCondition.getAlipayId()) ;
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("ZHE_ACCOUNT.queryAccountsWithPagination" , condition) ;
	}

	@Override
	public int countAccountsWithPagination(DdzAccountSearchCondition searchCondition) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("gmtCreateStart", searchCondition.getGmtCreateStart()) ;
		condition.put("gmtCreateEnd", searchCondition.getGmtCreateEnd()) ;
		condition.put("alipayId", searchCondition.getAlipayId()) ;
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("ZHE_ACCOUNT.countAccountsWithPagination" , condition)) ;
	}

}
