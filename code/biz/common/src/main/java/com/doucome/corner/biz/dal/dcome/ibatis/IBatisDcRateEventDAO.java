package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRateEventDO;
import com.doucome.corner.biz.dal.dcome.DcRateEventDAO;

/**
 * @author ib 2012-7-28 ÏÂÎç10:13:58
 */
public class IBatisDcRateEventDAO extends SqlMapClientDaoSupport implements DcRateEventDAO {

    @Override
    public long insert(DcRateEventDO rateEventDO) {
        return (Long) getSqlMapClientTemplate().insert("DcRateEvent.insert", rateEventDO);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DcRateEventDO> queryByItemId(long itemId) {
        Map map = new HashMap();
        map.put("itemId", itemId);
        return getSqlMapClientTemplate().queryForList("DcRateEvent.queryRateEvent", map);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<DcRateEventDO> queryByItemId(long itemId, int inMinutes) {
        Map map = new HashMap();
        map.put("itemId", itemId);
        map.put("inMinutes", inMinutes);
        return getSqlMapClientTemplate().queryForList("DcRateEvent.queryRateEvent", map);
    }
}
