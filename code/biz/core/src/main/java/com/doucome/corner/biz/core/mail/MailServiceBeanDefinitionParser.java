package com.doucome.corner.biz.core.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.doucome.corner.biz.core.mail.MailService.Mail;

/**
 * 类MailServiceBeanDefinitionParser.java的实现描述：mails.xsd解析器
 * 
 * @author ib 2012-4-29 下午03:13:51
 */
public class MailServiceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return MailService.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        List<Element> elements = subElements(element, "mails:mail");
        Map<String, Mail> configMap = new HashMap<String, Mail>();
        for (Element e : elements) {

            String id = e.getAttribute("id");
            String from = e.getAttribute("from");
            String sender = e.getAttribute("sender");
            String password = e.getAttribute("password");
            String title = e.getAttribute("title");
            String template = e.getAttribute("template");
            String host = e.getAttribute("host");
            Mail mail = new Mail();
            mail.setId(id);
            mail.setFrom(from);
            mail.setSender(sender);
            mail.setPassword(password);
            mail.setTitle(title);
            mail.setTemplate(template);
            mail.setHost(host);
            configMap.put(id, mail);
        }
        bean.addPropertyValue("configMap", configMap);
    }

    /**
     * 取得所有子elements，如果未指定selector，则返回所有elements。
     */
    public static List<Element> subElements(Element element, String name) {
        NodeList nodes = element.getElementsByTagName(name);
        List<Element> subElements = new ArrayList<Element>(nodes.getLength());

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node instanceof Element) {
                Element subElement = (Element) node;
                subElements.add(subElement);
            }
        }

        return subElements;
    }
}
