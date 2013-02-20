package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;

/**
 * ��AccountService.java��ʵ���������ʺ�����߼�����֧�����ʺ�Ϊ����
 * 
 * @author ib 2012-3-4 ����11:12:52
 */
public interface DdzAccountService {

    /**
     * ����֧����id��ȡ�˻�id
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
     * ��ѯAccount
     * @param searchCondition
     * @param pagination
     * @return
     */
    QueryResult<DdzAccountDO> getAccountsWithPagination(DdzAccountSearchCondition searchCondition , Pagination pagination) ;
    
    /**
     * ����άȨ����
     * @param alipayId
     * @param count
     * @return
     */
    int incrRefundCountByAlipayId(String alipayId , int count) ;
    
    /**
     * ����֪ͨ����
     * @param alipayId
     * @param count
     * @return
     */
    int decrNotifyCountByAlipayId(String alipayId , int count) ;
    
    /**
     * ��������½ʱ��
     * @param alipayId
     * @return
     */
    int updateLastLoginByAlipayId(String alipayId) ;
    
    /**
     * ����������ʱ��
     * @param alipayId
     * @return
     */
    int updateLastVisitByAlipayId(String alipayId) ;
}
