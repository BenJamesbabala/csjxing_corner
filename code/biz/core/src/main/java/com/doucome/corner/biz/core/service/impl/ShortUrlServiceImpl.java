package com.doucome.corner.biz.core.service.impl;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.service.ShortUrlService;
import com.doucome.corner.biz.core.utils.ShortUrlUtil;
import com.doucome.corner.biz.dal.ShortUrlDAO;
import com.doucome.corner.biz.dal.dataobject.ShortUrlDO;

/**
 * 类ShortUrlServiceImpl.java的实现描述：短url服务的实现
 * 
 * @author ib 2012-3-13 上午02:05:36
 */
public class ShortUrlServiceImpl implements ShortUrlService {

    private static final Log logger = LogFactory.getLog(ShortUrlServiceImpl.class);
    
    @Autowired
    private ShortUrlDAO      shortUrlDAO;

    @Override
    public String insertUrl(String urlAddress) {
        if (StringUtils.isBlank(urlAddress)) {
        	return null;
        }
        
        String md5Url = ShortUrlUtil.md5(urlAddress) ;
        
        List<ShortUrlDO> shortUrlList = shortUrlDAO.queryByMd5Url(md5Url) ;
        if(!CollectionUtils.isEmpty(shortUrlList)){
        	for(ShortUrlDO shortUrl : shortUrlList){
        		if(shortUrl != null && StringUtils.equalsIgnoreCase(shortUrl.getUrl(), urlAddress)){
        			return shortUrl.getShorten() ; //find url 
        		}
        	}
        }
       
        String[] shortNames = ShortUrlUtil.ShortText(urlAddress);
        ShortUrlDO shortUrl = new ShortUrlDO();
        shortUrl.setUrl(urlAddress);
        if(!ArrayUtils.isEmpty(shortNames)){
        	shortUrl.setShorten(shortNames[0]);
        	shortUrl.setMd5Url(md5Url) ;
        	
        	shortUrlDAO.insertShortUrl(shortUrl);
        	return shortUrl.getShorten();
        }
       
        return null;
    }

    @Override
    public String queryByShorten(String shortName) {
        if (StringUtils.isBlank(shortName)) return null;

        ShortUrlDO shortUrlDO = shortUrlDAO.queryByShorten(shortName);
        if (shortUrlDO != null) {
            return shortUrlDO.getUrl();
        } else {
            return null;
        }
    }


}
