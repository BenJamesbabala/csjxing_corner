package com.doucome.corner.biz.zhe.service;

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
}
