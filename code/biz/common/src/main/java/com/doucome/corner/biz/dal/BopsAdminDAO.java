package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;

/**
 * ��BopsAdminDAO.java��ʵ����������̨�ʺ���ص�DAO
 * 
 * @author ib 2012-4-21 ����02:22:52
 */
public interface BopsAdminDAO {

    public BopsAdminDO queryByAdminIdAndPass(String adminId, String password);
}
