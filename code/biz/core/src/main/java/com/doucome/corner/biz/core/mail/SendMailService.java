package com.doucome.corner.biz.core.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.apache.struts.chain.contexts.ServletActionContext;

/**
 * 类SendMailService.java的实现描述：邮件发送服务
 * 
 * @author ib 2012-4-25 上午01:24:20
 */
public class SendMailService {

    private String host         = "smtp.ym.163.com";                  // smtp服务器
    private String user         = "fanli@diandianzhe.com";            // 用户名
    private String pwd          = "3edc4rfv";                         // 密码
    private String from         = "fanli@diandianzhe.com";            // 发件人地址
    private String to           = "ibhaha@163.com";                   // 收件人地址
    private String subject      = "ttt";                              // 邮件标题
    private String templatePath = "/ali/777/testimg/template.htm";

    public void setAddress(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    public void send() {
        
        Properties props = new Properties();
        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");
        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);
        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);
        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 加载标题
            message.setSubject(subject);
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容

            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(getHtml(templatePath), "text/html;charset=gb2312");
            multipart.addBodyPart(contentPart);

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, user, pwd);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("ibhaha@163.com");

        SendMailService cn = new SendMailService();
        // 设置发件人地址、收件人地址和邮件标题
        for (String email : list) {
            cn.setAddress("fanli@diandianzhe.com", email, "just test join mail2");
            cn.send();
        }

    }

    public String getHtml(String path) {
        return "hahha!!!!!";
        // BufferedReader reader = null;
        // StringBuffer buffer = null;
        // try {
        // reader = new BufferedReader(new FileReader(new File(path)));
        // String str = null;
        // buffer = new StringBuffer();
        // while ((str = reader.readLine()) != null) {
        // buffer.append(str);
        // }
        // return buffer.toString();
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // if (reader != null) reader.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // return null;
    }
}
