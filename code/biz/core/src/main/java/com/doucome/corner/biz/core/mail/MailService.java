package com.doucome.corner.biz.core.mail;

import java.util.HashMap;
import java.util.Map;

/**
 * 类MailService.java的实现描述：邮件服务
 * 
 * @author ib 2012-4-29 下午02:57:39
 */
public class MailService {

    private Map<String, Mail> configMap = new HashMap<String, Mail>();

    public Map<String, Mail> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(Map<String, Mail> configMap) {
        this.configMap = configMap;
    }

    public static class Mail {

        private String id;
        private String host;
        private String from;
        private String sender;
        private String password;
        private String title;
        private String template;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

    }
}
