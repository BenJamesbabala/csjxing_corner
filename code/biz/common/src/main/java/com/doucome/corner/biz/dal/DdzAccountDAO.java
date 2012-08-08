package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
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
    
    /**
     * 查询account
     * @param searchCondition
     * @param start
     * @param size
     * @return
     */
    List<DdzAccountDO> queryAccountsWithPagination(DdzAccountSearchCondition searchCondition , int start , int size ) ;
    
    /**
     * count account
     * @param searchCondition
     * @return
     */
    int countAccountsWithPagination(DdzAccountSearchCondition searchCondition) ;
}
