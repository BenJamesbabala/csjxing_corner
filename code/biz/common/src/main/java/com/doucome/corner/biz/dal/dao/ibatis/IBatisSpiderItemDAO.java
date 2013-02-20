package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.SpiderItemDAO;
import com.doucome.corner.biz.dal.condition.SpiderItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;

/**
 * @author ib 2012-9-8 ÏÂÎç10:44:30
 */
public class IBatisSpiderItemDAO extends SqlMapClientDaoSupport implements SpiderItemDAO {

    @Override
    public Long insertItem(SpiderItemDO spiderItemDO) {
        return (Long) getSqlMapClientTemplate().insert("spiderItem.insertItem", spiderItemDO);
    }

    @Override
    public Long countWithPagination(SpiderItemSearchCondition condition) {
        return (Long) getSqlMapClientTemplate().queryForObject("spiderItem.countItems", condition.toMap());
    }

    @Override
    public List<SpiderItemDO> queryListWithPagination(SpiderItemSearchCondition condition, long start, long size) {
        Map map = condition.toMap();
        map.put("start", start);
        map.put("size", size);
        return getSqlMapClientTemplate().queryForList("spiderItem.searchItems", map);
    }

	@Override
	public SpiderItemDO queryItem(long tbItemId) {
		return (SpiderItemDO) getSqlMapClientTemplate().queryForObject("spiderItem.queryItemByItemId",tbItemId);
	}

}
