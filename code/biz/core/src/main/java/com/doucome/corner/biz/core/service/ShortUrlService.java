package com.doucome.corner.biz.core.service;


/**
 * ��ShortUrlService.java��ʵ����������url����ӿڶ���
 * 
 * @author ib 2012-3-13 ����02:03:16
 */
public interface ShortUrlService {

    public String insertUrl(String urlAddress);

    public String queryByShorten(String shortName);
}
