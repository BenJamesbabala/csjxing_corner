package cn.emay.sdk.client;

public interface SmsClientWrapper {
	
	/**
	 * 发送短信
	 * @param mobiles 短信号码
	 * @param smsContent 短信内容
	 * @return
	 */
	int sendSMS(String[] mobiles , String smsContent) ;

}
