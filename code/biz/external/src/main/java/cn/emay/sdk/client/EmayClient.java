package cn.emay.sdk.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import cn.emay.sdk.api.SDKClient;
import cn.emay.sdk.api.SDKService;

/**
 * 亿美短信客户端
 * @author shenjia.caosj 2012-7-19
 *
 */
public class EmayClient implements SmsClientWrapper ,InitializingBean {
	
	private Log log = LogFactory.getLog(EmayClient.class) ;
	
	SDKService service ;
	
	private String softwareSerialNo ;
	
	private String key ;

	private String charset ;
	
	private String password ;
	
	private static SDKClient sDKClientSOAImpl ;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			service = new SDKService();
			sDKClientSOAImpl = service.getSDKService();
			int result = sDKClientSOAImpl.registEx(softwareSerialNo , key ,password ) ;
			if(result != 0) {
				log.error("emay client register error ! return code : " + result , new IllegalStateException()) ;
				
			}
		}catch(Exception e){
			log.error("INIT Emay SMS Client ERROR !" + e.getMessage() , e) ;
			return ;
		}
		
		
	}
	
	
	private int sendSMS(String[] mobiles, String smsContent,String addSerial, int smsPriority) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < mobiles.length; i++) {
			list.add(mobiles[i]);
		}
		return sDKClientSOAImpl.sendSMS(softwareSerialNo, key, null, list,
				smsContent, addSerial, charset, smsPriority);
	}
	
	
	@Override
	public int sendSMS(String[] mobiles, String smsContent) {
		return sendSMS(mobiles, smsContent , null , 5) ;
	}
	
	
	public void setSoftwareSerialNo(String softwareSerialNo) {
		this.softwareSerialNo = softwareSerialNo;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public void setCharset(String charset) {
		this.charset = charset;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}
