package cn.emay.sdk.api;

import java.util.ArrayList;
import java.util.List;


public class Client {
	SDKService service = new SDKService();
	private static SDKClient sDKClientSOAImpl = null;
	String softwareSerialNo = "";
	String key = "";

	public Client(String softwareSerialNo, String key) {
		this.softwareSerialNo = softwareSerialNo;
		this.key = key;
		sDKClientSOAImpl = service.getSDKService();
	}

	public int cancelMOForward() {
		return sDKClientSOAImpl.cancelMOForward(softwareSerialNo, key);
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

	public List<StatusReport> getReport() {
		return sDKClientSOAImpl.getReport(softwareSerialNo, key);
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

}
