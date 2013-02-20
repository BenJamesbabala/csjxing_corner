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
    
    /**
     * 增加维权笔数
     * @param alipayId
     * @param count
     * @return
     */
    int incrRefundCountByAlipayId(String alipayId , int count) ;
    
    /**
     * 减少通知次数
     * @param alipayId
     * @param count
     * @return
     */
    int decrNotifyCountByAlipayId(String alipayId , int count) ;
    
    /**
     * 更新最后登陆时间
     * @param alipayId
     * @return
     */
    int updateLastLoginByAlipayId(String alipayId) ;
    
    /**
     * 更新最后访问时间
     * @param alipayId
     * @return
     */
    int updateLastVisitByAlipayId(String alipayId) ;
}
