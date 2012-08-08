package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.ShortUrlDO;

/**
 * 类ShortUrlDAO.java的实现描述：短urlDAO
 * 
 * @author ib 2012-3-4 下午06:58:35
 */
public interface ShortUrlDAO {

    ShortUrlDO queryByShorten(String shorten);
    
    List<ShortUrlDO> queryByMd5Url(String md5Url) ; 
    
    ShortUrlDO queryByUrl(String url);

    void insertShortUrl(ShortUrlDO shortUrlDO);

}
