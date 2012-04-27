package com.doucome.corner.biz.core.service;


/**
 * 类ShortUrlService.java的实现描述：短url服务接口定义
 * 
 * @author ib 2012-3-13 上午02:03:16
 */
public interface ShortUrlService {

    public String insertUrl(String urlAddress);

    public String queryByShorten(String shortName);
}
