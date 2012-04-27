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
 * ��SendMailService.java��ʵ���������ʼ����ͷ���
 * 
 * @author ib 2012-4-25 ����01:24:20
 */
public class SendMailService {

    private String host         = "smtp.ym.163.com";                  // smtp������
    private String user         = "fanli@diandianzhe.com";            // �û���
    private String pwd          = "3edc4rfv";                         // ����
    private String from         = "fanli@diandianzhe.com";            // �����˵�ַ
    private String to           = "ibhaha@163.com";                   // �ռ��˵�ַ
    private String subject      = "ttt";                              // �ʼ�����
    private String templatePath = "/ali/777/testimg/template.htm";

    public void setAddress(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    public void send() {
        
        Properties props = new Properties();
        // ���÷����ʼ����ʼ������������ԣ�����ʹ�����׵�smtp��������
        props.put("mail.smtp.host", host);
        // ��Ҫ������Ȩ��Ҳ�����л����������У�飬��������ͨ����֤��һ��Ҫ����һ����
        props.put("mail.smtp.auth", "true");
        // �øո����úõ�props���󹹽�һ��session
        Session session = Session.getDefaultInstance(props);
        // ������������ڷ����ʼ��Ĺ�������console����ʾ������Ϣ��������ʹ
        // �ã�������ڿ���̨��console)�Ͽ��������ʼ��Ĺ��̣�
        session.setDebug(true);
        // ��sessionΪ����������Ϣ����
        MimeMessage message = new MimeMessage(session);
        try {
            // ���ط����˵�ַ
            message.setFrom(new InternetAddress(from));
            // �����ռ��˵�ַ
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // ���ر���
            message.setSubject(subject);
            // ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
            Multipart multipart = new MimeMultipart();

            // �����ʼ����ı�����

            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(getHtml(templatePath), "text/html;charset=gb2312");
            multipart.addBodyPart(contentPart);

            // ��multipart����ŵ�message��
            message.setContent(multipart);
            // �����ʼ�
            message.saveChanges();
            // �����ʼ�
            Transport transport = session.getTransport("smtp");
            // ���ӷ�����������
            transport.connect(host, user, pwd);
            // ���ʼ����ͳ�ȥ
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
        // ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
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
