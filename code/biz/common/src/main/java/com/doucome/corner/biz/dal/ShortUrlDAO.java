package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.ShortUrlDO;

/**
 * ��ShortUrlDAO.java��ʵ����������urlDAO
 * 
 * @author ib 2012-3-4 ����06:58:35
 */
public interface ShortUrlDAO {

    ShortUrlDO queryByShorten(String shorten);
    
    List<ShortUrlDO> queryByMd5Url(String md5Url) ; 
    
    ShortUrlDO queryByUrl(String url);

    void insertShortUrl(ShortUrlDO shortUrlDO);

}
