package cn.emay.sdk.client;

public interface SmsClientWrapper {
	
	/**
	 * ���Ͷ���
	 * @param mobiles ���ź���
	 * @param smsContent ��������
	 * @return
	 */
	int sendSMS(String[] mobiles , String smsContent) ;

}
