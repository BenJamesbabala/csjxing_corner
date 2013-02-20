package com.doucome.corner.biz.core.sms.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import com.doucome.corner.biz.core.sms.model.SmsBusinessEnums;


/**
 * SmsBusiness��������
 * <ul>
 * 	<li>����������Ϣ�����ö�ȡ</li>
 * 	<li>�����İ������ô�DB��ȡ</li>
 * </ul>
 * @author langben 2011-3-22
 *
 */
public class SmsBusinessConfig {
    	
	private Resource config; //xml�����ļ���SmsBusiness.xml��
    private SmsBusinessPack smsBusinessesPack = null ;
        
	public void init() {
        //get xml comfig resource
        try {
            Digester digester = new Digester();

            digester.setValidating(false);
            digester.addObjectCreate("businesses", SmsBusinessPack.class);
            digester.addBeanPropertySetter("businesses/transport/host", "host");
            digester.addBeanPropertySetter("businesses/transport/port", "port");
            digester.addBeanPropertySetter("businesses/transport/uri", "uri");
            digester.addBeanPropertySetter("businesses/transport/parameter", "parameter");

            //for one message 
            
            digester.addObjectCreate("businesses/business", SmsBusiness.class);
            digester.addSetProperties("businesses/business", "id", "id");
            digester.addBeanPropertySetter("businesses/business/chl-code","chlCode");
            digester.addBeanPropertySetter("businesses/business/chu-code", "chuCode");
            digester.addBeanPropertySetter("businesses/business/name", "name");
            digester.addBeanPropertySetter("businesses/business/property", "property");
            digester.addBeanPropertySetter("businesses/business/fee-type", "feeType");
            digester.addBeanPropertySetter("businesses/business/money", "money");
            //add message to SmsBusiness object 
            digester.addCallMethod("businesses/business/message", "addMessage",2);
            digester.addCallParam("businesses/business/message", 0, "key");
            digester.addCallParam("businesses/business/message", 1);
            digester.addSetNext("businesses/business", "addSmsBusiness");
	
            //for transport info
            smsBusinessesPack = (SmsBusinessPack) digester.parse(config.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("SmsBusiness.xml config file error :" + e.getMessage(), e);
        } catch (SAXException e) {
            throw new RuntimeException("SmsBusiness.xml config file parse error :" + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("SmsBusiness.xml config file parse error :" + e.getMessage(), e);
        } 
       
    }

    public void setConfig(Resource config) {
		this.config = config;
	}
	
	public SmsBusinessPack getSmsBusinessesPack() {
		return smsBusinessesPack;
	}
	
	public SmsBusiness getSmsBusiness(SmsBusinessEnums business){
		if(smsBusinessesPack == null){
			return null ;
		}
		return smsBusinessesPack.getSmsBusiness(business.getValue()) ;
	}

	/**
	 * �������ð�������������Ϣ��
	 * <ul>
	 * 	<li>�������ط�����������Ϣ</li>
	 * 	<li>�������ݵ�������Ϣ</li>
	 * </ul>
	 * @author langben 2011-3-22
	 *
	 */
	public static class SmsBusinessPack {
	    String host; //����Ϣ��������ַ
	    int    port; //�˿�
	    String uri; //Ӧ����
	    String parameter; //����
	    private Map<String,SmsBusiness> smsBusiness = new HashMap<String,SmsBusiness>();
	 	    
	    public Map<String,SmsBusiness> getSmsBusinesses(){
	    	return smsBusiness ;
	    }
	    
	    public void addSmsBusiness(SmsBusiness sms) {
	        if (!smsBusiness.containsKey(sms.getId())) {
	            this.smsBusiness.put(sms.getId(), sms);
	        } else {
	            throw new RuntimeException("Duplicate sms business id");
	        }
	    }

	    public SmsBusiness getSmsBusiness(String id) {
	        return (SmsBusiness) this.smsBusiness.get(id);
	    }

	    public void setHost(String host) {
	        this.host = host;
	    }

	    public void setPort(int port) {
	        this.port = port;
	    }

	    public void setUri(String uri) {
	        this.uri = uri;
	    }

	    public String getURL() {
	        return "http://" + host + ":" + port + uri;
	    }

	    public String getParameter() {
	        return parameter;
	    }

	    public void setParameter(String parameter) {
	        this.parameter = parameter;
	    }
	}
}
