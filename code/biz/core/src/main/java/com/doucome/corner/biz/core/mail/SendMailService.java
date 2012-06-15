package com.doucome.corner.biz.core.mail;

import java.util.Map;

/**
 * 类SendMailService.java的实现描述：邮件发送服务
 * 
 * @author ib 2012-4-29 下午03:37:41
 */
public interface SendMailService {

    public boolean send(String mailID, String toAddess, Map<String,Object> context);
}
