package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.SpiderConfigDAO;
import com.doucome.corner.biz.dal.dataobject.SpiderConfigDO;

/**
 * @author ib 2012-9-8 обнГ10:44:30
 */
public class IBatisSpiderConfigDAO extends SqlMapClientDaoSupport implements SpiderConfigDAO {

    @Override
    public List<SpiderConfigDO> queryEnableList() {
        return getSqlMapClientTemplate().queryForList("spiderConfig.queryConfigs");
    }

}
