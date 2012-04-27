package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;

/**
 * 类AccountDAO.java的实现描述：账户DAO
 * 
 * @author ib 2012-3-4 下午06:58:35
 */
public interface DdzAccountDAO {

    public DdzAccountDO queryByAlipayId(String alipayId);

    public DdzAccountDO queryByAccountId(String accountId);

    public DdzAccountDO queryByUid(String uid);

    public void insertAccount(DdzAccountDO accountDO);
    
    public void updateAccount(DdzAccountDO accountDO);
}
