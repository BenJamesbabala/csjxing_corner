package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.DdzConfigDAO;
import com.doucome.corner.biz.dal.dataobject.DdzConfigDO;

/**
 * ��IBatisAccountDAO.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-3-4 ����07:02:59
 */
public class IBatisDdzConfigDAO extends SqlMapClientDaoSupport implements DdzConfigDAO {

    private static final String QUERY_FOR_MODULE = "ZHE-CONFIG.queryForModule";

    /*
     * (non-Javadoc)
     * @see com.doucome.corner.biz.dal.DdzConfigDAO#queryByModule(java.lang.String)
     */
    @Override
    public List<DdzConfigDO> queryForModule(String module) {
        return getSqlMapClientTemplate().queryForList(QUERY_FOR_MODULE, module);
    }

}
