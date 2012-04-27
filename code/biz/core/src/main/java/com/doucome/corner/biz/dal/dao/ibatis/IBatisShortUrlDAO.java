package com.doucome.corner.biz.dal.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.ShortUrlDAO;
import com.doucome.corner.biz.dal.dataobject.ShortUrlDO;

/**
 * 类IBatisShortUrlDAO.java的实现描述：ShortUrlDAO的ibatis实现
 * 
 * @author ib 2012-3-4 下午07:02:59
 */
public class IBatisShortUrlDAO extends SqlMapClientDaoSupport implements ShortUrlDAO {

    private static final String QUERY_BY_SHORTEN = "SHORT-URL.queryByShorten";
    private static final String QUERY_BY_URL        = "SHORT-URL.queryByURL";
    private static final String INSERT_SHORT_URL    = "SHORT-URL.insertShortUrl";

    @Override
    public ShortUrlDO queryByShorten(String shorten) {
        return (ShortUrlDO) getSqlMapClientTemplate().queryForObject(QUERY_BY_SHORTEN, shorten);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ShortUrlDO> queryByMd5Url(String md5Url) {
    	return getSqlMapClientTemplate().queryForList("SHORT-URL.queryByMd5Url", md5Url);
    }

    @Override
    public ShortUrlDO queryByUrl(String url) {
        return (ShortUrlDO) getSqlMapClientTemplate().queryForObject(QUERY_BY_URL, url);
    }

    @Override
    public void insertShortUrl(ShortUrlDO shortUrlDO) {
        getSqlMapClientTemplate().insert(INSERT_SHORT_URL, shortUrlDO);
    }

}
