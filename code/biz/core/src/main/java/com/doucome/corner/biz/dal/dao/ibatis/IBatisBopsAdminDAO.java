package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.BopsAdminDAO;
import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;

/**
 * 类IBatisBopsAdminDAO.java的实现描述：BopsAdminDAO的ibatis实现
 * 
 * @author ib 2012-4-21 下午02:30:17
 */
public class IBatisBopsAdminDAO extends SqlMapClientDaoSupport implements BopsAdminDAO {

    private static final String QUERY_BY_ADMINID_AND_PASS = "BOPS-ADMIN.queryByAdminIdAndPass";

    @Override
    public BopsAdminDO queryByAdminIdAndPass(String adminId, String password) {
        Map map = new HashMap<String, String>();
        map.put("adminId", adminId);
        map.put("password", password);
        return (BopsAdminDO) this.getSqlMapClientTemplate().queryForObject(QUERY_BY_ADMINID_AND_PASS, map);
    }

}
