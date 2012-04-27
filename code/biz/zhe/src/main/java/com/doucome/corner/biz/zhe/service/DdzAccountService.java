package com.doucome.corner.biz.zhe.service;

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
}
