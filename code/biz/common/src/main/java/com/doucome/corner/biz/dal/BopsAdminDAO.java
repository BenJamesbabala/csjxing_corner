package com.doucome.corner.biz.dal;

import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;

/**
 * 类BopsAdminDAO.java的实现描述：后台帐号相关的DAO
 * 
 * @author ib 2012-4-21 下午02:22:52
 */
public interface BopsAdminDAO {

    public BopsAdminDO queryByAdminIdAndPass(String adminId, String password);
}
