package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;

/**
 * 类AccountService.java的实现描述：帐号相关逻辑（以支付宝帐号为主）
 * 
 * @author ib 2012-3-4 下午11:12:52
 */
public interface DdzAccountService {

    /**
     * 根据支付宝id获取账户id
     * 
     * @param alipayId
     * @return
     */
    public String getAccountIdByAlipayId(String alipayId);

    public void insertAccountDO(DdzAccountDO accountDO);

    public DdzAccountDO queryAccountDOByAlipayId(String alipayId);

    public DdzAccountDO queryAccountDOByUid(String uid);

    public DdzAccountDO queryAccountByAccountId(String accountId);

    public DdzAccountDO insertOrUpdateAccount(String uid, String alipayId);

    public void updateDdzAccount(DdzAccountDO accountDO);
        
    /**
     * 查询Account
     * @param searchCondition
     * @param pagination
     * @return
     */
    QueryResult<DdzAccountDO> getAccountsWithPagination(DdzAccountSearchCondition searchCondition , Pagination pagination) ;
    
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
