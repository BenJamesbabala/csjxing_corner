package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.UidCreateUtil;
import com.doucome.corner.biz.dal.DdzAccountDAO;
import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;

/**
 * 类AccountServiceImpl.java的实现描述：AccountService的实现类
 * 
 * @author ib 2012-3-4 下午11:21:16
 */
public class DdzAccountServiceImpl implements DdzAccountService {

    private DdzAccountDAO ddzAccountDAO;

    @Override
    public String getAccountIdByAlipayId(String alipayId) {
        DdzAccountDO accountDO = queryAccountDOByAlipayId(alipayId);
        if (accountDO == null) {
            return null;
        }
        return accountDO.getAccountId();
    }

    @Override
    public void insertAccountDO(DdzAccountDO accountDO) {
        ddzAccountDAO.insertAccount(accountDO);
    }

    @Override
    public DdzAccountDO queryAccountDOByAlipayId(String alipayId) {
        if (alipayId == null) return null;
        DdzAccountDO accountDO = ddzAccountDAO.queryByAlipayId(alipayId);
        if (accountDO == null) {
            // 还没存在，插入一个
            accountDO = new DdzAccountDO();
            accountDO.setAccountId(UidCreateUtil.createUid(alipayId, 11));
            accountDO.setAlipayId(alipayId);
            insertAccountDO(accountDO);
        }

        return accountDO;
    }

    public void setDdzAccountDAO(DdzAccountDAO ddzAccountDAO) {
        this.ddzAccountDAO = ddzAccountDAO;
    }

    @Override
    public DdzAccountDO queryAccountDOByUid(String uid) {
        if (uid == null) return null;
        return ddzAccountDAO.queryByUid(uid);
    }

    @Override
    public DdzAccountDO queryAccountByAccountId(String accountId) {
        return ddzAccountDAO.queryByAccountId(accountId);
    }

    @Override
    public DdzAccountDO insertOrUpdateAccount(String uid, String alipayId) {
        if (StringUtils.isBlank(alipayId)) {
            return null;
        }

        DdzAccountDO accountDO = queryAccountDOByUid(uid);
        if (accountDO != null) {
            return accountDO;
        }

        accountDO = queryAccountDOByAlipayId(alipayId);
        if (StringUtils.isNotBlank(accountDO.getUid())) {
            // 此支付宝帐号已经被其他user绑定
            return null;
        }
        accountDO.setUid(uid);
        updateDdzAccount(accountDO);
        return accountDO;
    }

    @Override
    public void updateDdzAccount(DdzAccountDO accountDO) {
        if (accountDO != null) {
            ddzAccountDAO.updateAccount(accountDO);
        }
    }

	@Override
	public QueryResult<DdzAccountDO> getAccountsWithPagination(DdzAccountSearchCondition searchCondition, Pagination pagination) {
		int totalRecords = ddzAccountDAO.countAccountsWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DdzAccountDO>(new ArrayList<DdzAccountDO>(), pagination, totalRecords);
        }
        List<DdzAccountDO> items = ddzAccountDAO.queryAccountsWithPagination(searchCondition, pagination.getStart(), pagination.getSize()) ;
        return new QueryResult<DdzAccountDO>(items, pagination, totalRecords);
		
	}

}
