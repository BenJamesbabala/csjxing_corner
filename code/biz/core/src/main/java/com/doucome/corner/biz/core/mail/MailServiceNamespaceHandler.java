package com.doucome.corner.biz.core.mail;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 类MailServiceNamespaceHandler.java的实现描述：mails.xsd Handler
 * 
 * @author ib 2012-4-29 下午03:14:30
 */
public class MailServiceNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("mails", new MailServiceBeanDefinitionParser());
    }
}
