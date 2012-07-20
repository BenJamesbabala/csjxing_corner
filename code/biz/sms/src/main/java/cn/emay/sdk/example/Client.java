package cn.emay.sdk.example;


import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import cn.emay.sdk.api.*;

public class Client {
	
	static ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
	SDKService service = new SDKService();
	private static SDKClient sDKClientSOAImpl= null;
	private static Client client=null;
	String softwareSerialNo="";
	String key="";

	public Client(String softwareSerialNo, String key) {
		this.softwareSerialNo = softwareSerialNo;
		this.key = key;
		sDKClientSOAImpl=service.getSDKService();
	}
	public synchronized static Client getInstance(String softwareSerialNo, String key){
		if(client==null){
			client=new Client(softwareSerialNo,key);
		}
		return client;
		
	}
	//通过配置文件进行初始化
	public synchronized static Client getInstance(){
		
		if(client==null){
			client=new Client(bundle.getString("softwareSerialNo"),bundle.getString("key"));
		}
		return client;
		
	}

	public int chargeUp(String cardNo, String cardPass) {
		return sDKClientSOAImpl.chargeUp(softwareSerialNo, key, cardNo,
				cardPass);
	}

	public double getBalance() {
		return sDKClientSOAImpl.getBalance(softwareSerialNo, key);
	}

	public double getEachFee() {
		return sDKClientSOAImpl.getEachFee(softwareSerialNo, key);
	}

	public List<Mo> getMO() {
		return sDKClientSOAImpl.getMO(softwareSerialNo, key);
	}

	public String getVersion() {
		return sDKClientSOAImpl.getVersion();
	}

	public int logout() {
		return sDKClientSOAImpl.logout(softwareSerialNo, key);
	}

	public int registDetailInfo(String eName, String linkMan, String phoneNum,
			String mobile, String email, String fax, String address,
			String postcode) {
		return sDKClientSOAImpl.registDetailInfo(softwareSerialNo, key, eName,
				linkMan, phoneNum, mobile, email, fax, address, postcode);
	}

	public int registEx(String serialpass) {
		return sDKClientSOAImpl.registEx(softwareSerialNo, key, serialpass);
	}

	public int sendSMS(String sendTime, String[] mobiles, String smsContent,
			String addSerial, String srcCharset, int smsPriority) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < mobiles.length; i++) {
			list.add(mobiles[i]);
		}
		return sDKClientSOAImpl.sendSMS(softwareSerialNo, key, sendTime, list,
				smsContent, addSerial, srcCharset, smsPriority);
	}

	public int serialPwdUpd(String serialPwd, String serialPwdNew) {
		return sDKClientSOAImpl.serialPwdUpd(softwareSerialNo, key, serialPwd,
				serialPwdNew);
	}

	public int setMOForward(String forwardMobile) {
		return sDKClientSOAImpl.setMOForward(softwareSerialNo, key,
				forwardMobile);
	}

	public int setMOForwardEx(String[] forwardMobile) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < forwardMobile.length; i++) {
			list.add(forwardMobile[i]);
		}
		return sDKClientSOAImpl.setMOForwardEx(softwareSerialNo, key, list);
	}
	
	public int cancelMOForward() {
		return sDKClientSOAImpl.cancelMOForward(softwareSerialNo, key);
	}

}
