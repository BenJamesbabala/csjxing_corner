package com.doucome.corner.biz.core.sms;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.emay.sdk.client.SmsClientWrapper;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.sms.config.SmsBusiness;
import com.doucome.corner.biz.core.sms.config.SmsBusinessConfig;
import com.doucome.corner.biz.core.sms.model.BuildedSmsMtDO;
import com.doucome.corner.biz.core.sms.model.SmsBusinessEnums;
import com.doucome.corner.biz.core.sms.model.SmsMtDO;
import com.doucome.corner.biz.core.sms.model.SmsMtResult;

public class SendSmsServiceImpl implements SendSmsService {
	
	private static final Log log               	= LogFactory.getLog(SendSmsServiceImpl.class);
    private static final Log smsLog		= LogFactory.getLog(LogConstant.sms_log);
	
	@Autowired
	private SmsClientWrapper smsClientWrapper ;
	
	@Autowired
	private SmsBusinessConfig		smsBusinessConfig ;

	@Override
	public SmsMtResult sendMessage(SmsMtDO mtDO) {
		BuildedSmsMtDO buildedSmsMtDO = this.buildSendMessage(mtDO) ;
		return this.sendMessage(buildedSmsMtDO);
	}
	
	private SmsMtResult sendMessage(BuildedSmsMtDO buildedSmsMtDO){
		if(StringUtils.isBlank(buildedSmsMtDO.getBusinessId())
				|| StringUtils.isBlank(buildedSmsMtDO.getToMobile())){
			throw new IllegalArgumentException("toMobile、businessId can't be blank !!") ;
		}
		
		if(StringUtils.isBlank(buildedSmsMtDO.getSmsContent())){
			throw new IllegalArgumentException("smsContent can't be blank !!") ;
		}
		
		int i = smsClientWrapper.sendSMS(new String[]{buildedSmsMtDO.getToMobile()}, buildedSmsMtDO.getSmsContent()) ;
		
		if(smsLog.isInfoEnabled()){
        	StringBuilder sb = new StringBuilder() ;
        	sb.append("SMS result[").append(i).append("] ").append(buildedSmsMtDO) ;
        	smsLog.info(sb) ;
        }
		
		return null ;
	}
	
	
	private BuildedSmsMtDO buildSendMessage(SmsMtDO mtDO) {
		
		if(StringUtils.isBlank(mtDO.getToMobile()) || mtDO.getBusinessId()==null){
			throw new IllegalArgumentException("toMobile and businessId can't be blank !!") ;
		}
		SmsBusinessEnums smsBusinessId = mtDO.getBusinessId() ;
		SmsBusiness smsBusiness = smsBusinessConfig.getSmsBusinessesPack().getSmsBusiness(smsBusinessId.getValue());
        if(smsBusiness == null){
        	throw new IllegalArgumentException("cant find SmsBusiness named :" + smsBusinessId) ;
        }
		
        if(StringUtils.isBlank(mtDO.getMessageKey())){
        	mtDO.setMessageKey(SmsBusiness.DEFAULT_MESSAGE_KEY) ;
        }
        if(StringUtils.isBlank(mtDO.getChlCode())){
        	mtDO.setChlCode(smsBusiness.getChlCode()) ;
        }
        if(StringUtils.isBlank(mtDO.getMsgId())){
        	mtDO.setMsgId(smsBusinessId.getValue()) ;//将SMS的BusinessId 作为 msgId
        }
		String smsContent = null ;
		if (mtDO.getMsgParameter() == null) {
            smsContent = smsBusiness.getMessage(mtDO.getMessageKey());
        } else {
            smsContent = smsBusiness.getFormattedMessage(mtDO.getMessageKey(), mtDO.getMsgParameter());
        }
		smsContent = nullTOBlank(smsContent).replace('\n', ' ').replace('\r', ' ').replace('\t', ' ');
		BuildedSmsMtDO buildedSmsMtDO = new BuildedSmsMtDO() ;
		buildedSmsMtDO.setToMobile(mtDO.getToMobile()) ;
		buildedSmsMtDO.setMsgId(mtDO.getMsgId()) ;
		buildedSmsMtDO.setBatId(mtDO.getBatId()) ;
		buildedSmsMtDO.setChlCode(mtDO.getChlCode()) ;
		buildedSmsMtDO.setBusinessId(mtDO.getBusinessId().getValue()) ;
		buildedSmsMtDO.setSmsContent(smsContent) ;
		
		if(log.isInfoEnabled()){
			log.info("build smsSendDO   :" + buildedSmsMtDO) ;
		}
		return buildedSmsMtDO ;
	}
	
	private String nullTOBlank(String in) {
        if ("null".equalsIgnoreCase(in) || (in == null)) {
            return "";
        } else {
            return in;
        }
    }

}
