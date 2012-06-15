package com.doucome.corner.biz.core.mail.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.mail.MailService;
import com.doucome.corner.biz.core.mail.SendMailService;
import com.doucome.corner.biz.core.mail.MailService.Mail;
import com.doucome.corner.biz.core.service.VelocityMergeService;

/**
 * 类SendMailServiceImpl.java的实现描述：SendMailService邮件发送服务的默认实现
 * 
 * @author ib 2012-4-29 下午04:05:44
 */
public class SendMailServiceImpl implements SendMailService {

    private static final Log     log = LogFactory.getLog(SendMailServiceImpl.class);
    @Autowired
    private MailService          mailService;
    @Autowired
    private VelocityMergeService velocityMergeService;

    private Mail getMail(String mailId) {
        if (StringUtils.isBlank(mailId)) {
            return null;
        }
        Mail mail = mailService.getConfigMap().get(mailId);
        return mail;
    }

    @Override
    public boolean send(String mailID, String toAddess, Map<String, Object> context) {
        if (StringUtils.isBlank(mailID) || StringUtils.isBlank(toAddess)) {
            return false;
        }
        Mail mail = getMail(mailID);
        if (mail == null) {
            return false;
        }
        String content = "";
        try {
            VelocityContext velocityContext = new VelocityContext();
            if (context != null) {
                for (Iterator iterator = context.entrySet().iterator(); iterator.hasNext();) {
                    Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
                    velocityContext.put(entry.getKey(), entry.getValue());
                }
            }
            content = velocityMergeService.doExecute(mail.getTemplate(), velocityContext);
        } catch (Exception e) {
            log.error("merge velocity fail:" + mail.getTemplate(), e);
            return false;
        }
        return sendMail(content, toAddess, mail);
    }

    public boolean sendMail(String content, String toAddess, Mail mail) {
        Properties props = new Properties();
        props.put("mail.smtp.host", mail.getHost());
        props.put("mail.smtp.auth", true);
        Session session = Session.getDefaultInstance(props);
        // session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress fromAddress = new InternetAddress(mail.getFrom());
            if (StringUtils.isNotEmpty(mail.getSender())) {
                fromAddress.setPersonal(mail.getSender());
            }
            message.setFrom(fromAddress);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddess));
            message.setSubject(mail.getTitle());
            Multipart multipart = new MimeMultipart();
            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=gb2312");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(mail.getHost(), mail.getFrom(), mail.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            log.error("send mail fail", e);
            return false;
        }
        return true;
    }
}
