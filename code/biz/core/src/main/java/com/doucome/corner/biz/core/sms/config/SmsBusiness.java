package com.doucome.corner.biz.core.sms.config;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * SmsBusiness
 * @author langben 2011-3-22
 *
 */
public class SmsBusiness extends AbstractModel {
	
	public static final String DEFAULT_MESSAGE_KEY = "normal" ;
	
	/**
	 * ±Í æid
	 */
	private String id;
	private String chlCode;
	private String chuCode;
	private String name;
	private String feeType ;
	private BigDecimal money ;
	private String property ;
    protected Map<String ,String> message = new HashMap<String,String>();

    public String getDefaultMessage() {
        return getMessage(DEFAULT_MESSAGE_KEY);
    }
    
    public String getDefaultFormattedMessage(String[] param) {
        return getFormattedMessage(DEFAULT_MESSAGE_KEY , param);
    }

    public String getFormattedMessage(String msgKey, String[] param) {
    	String msg = getMessage(msgKey) ;
    	if(StringUtils.isBlank(msg)){
    		return StringUtils.EMPTY ;
    	}
        return MessageFormat.format(msg, param);
    }
    
    public String getMessage(String msgKey){
    	return message.get(msgKey) ;
    }

    public void addMessage(String key, String msg) {
        if (!this.message.containsKey(key)) {
            message.put(key, msg);
        } else {
            throw new RuntimeException("Dulicate message key : " + name + " - "+ key);
        }
    }
    
  //-----------------------------------------------
    //----------------------------------------------
    

	public String getId() {
		return id;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChlCode() {
		return chlCode;
	}

	public void setChlCode(String chlCode) {
		this.chlCode = chlCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getChuCode() {
		return chuCode;
	}

	public void setChuCode(String chuCode) {
		this.chuCode = chuCode;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}


    
}
