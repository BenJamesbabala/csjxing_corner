package com.doucome.corner.biz.core.mail;

import java.util.Map;

/**
 * ��SendMailService.java��ʵ���������ʼ����ͷ���
 * 
 * @author ib 2012-4-29 ����03:37:41
 */
public interface SendMailService {

    public boolean send(String mailID, String toAddess, Map<String,Object> context);
}
