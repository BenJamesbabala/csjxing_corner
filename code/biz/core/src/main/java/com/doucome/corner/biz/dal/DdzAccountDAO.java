package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;

/**
 * ��AccountDAO.java��ʵ���������˻�DAO
 * 
 * @author ib 2012-3-4 ����06:58:35
 */
public interface DdzAccountDAO {

    public DdzAccountDO queryByAlipayId(String alipayId);

    public DdzAccountDO queryByAccountId(String accountId);

    public DdzAccountDO queryByUid(String uid);

    public void insertAccount(DdzAccountDO accountDO);
    
    public void updateAccount(DdzAccountDO accountDO);
    
    /**
     * ��ѯaccount
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
