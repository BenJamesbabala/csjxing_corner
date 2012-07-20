package cn.emay.sdk.example;

import cn.emay.sdk.api.Mo;

import java.util.Iterator;
import java.util.List;

public class TestClient {

	public static String softwareSerialNo="XXXX-XXX-XXXX-XXXXX";//软件序列号,请通过亿美销售人员获取
	public static String key="XXXXXX";//序列号首次激活时自己设定
	public static String password="XXXXXX";// 密码,请通过亿美销售人员获取
	//public static Client sdkclient = null;
	
	public static void main(String[] args) {
		try {		
			//sdkclient=new Client("0SDK-EMY-0130-OCXQQ","888888");
			StartMenu();
			while (true) {
				System.out.println("请输入序号进行操作");
				byte[] command = new byte[4];
				System.in.read(command);
				int operate = 0;
				try {
					String commandString = new String(command);
					commandString = commandString.replaceAll("\r\n", "").trim();
					operate = Integer.parseInt(commandString);
				} catch (Exception e) {
					System.out.println("命令输入错误！！！");
				}
				switch (operate) {
				case 1:
					testRegistEx();
					break;
				case 2:
					testLogout();
					break;
				case 3:
					testRegistDetailInfo();
					break;
				case 4:
					testGetBalance();
					break;
				case 5:
					testChargeUp();
					break;
				case 6:
					testSerialPwdUpd();
					break;
				case 7:
					testSendSMS();
					break;
				case 8:
					testGetEachFee();
					break;
				case 9:
					testGetMO();
					break;
				case 10:
					testSetMOForward();
					break;
				case 11:
					testCancelMOForward();
					break;
				case 12:
					System.exit(0);
				default:
					System.out.println("该命令不存在 "+operate);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void StartMenu() {
		int i = 1;
		System.out.println(i + "、激活序列号,初次使用、已注销后再次使用时调用该方法");
		i += 1;
		System.out.println(i + "、注销序列号");
		i += 1;
		System.out.println(i + "、企业信息注册,目地在于短信发送异常时Emay可以连系到企业");
		i += 1;
		System.out.println(i + "、余额查询");
		i += 1;
		System.out.println(i + "、充值");
		i += 1;
		System.out.println(i + "、密码修改");
		i += 1;
		System.out.println(i + "、发送短信");
		i += 1;
		System.out.println(i + "、获取单条短信费用");
		i += 1;
		System.out.println(i + "、获取上行短信");
		i += 1;
		System.out.println(i + "、注册上行短信转接");
		i += 1;
		System.out.println(i + "、取消上行短信转接");
		i += 1;
		System.out.println(i + "、关闭退出");
	}
	
	/**
	 * 软件注销
	 * 1、软件注销后像发送短信、接受上行短信接口都无法使用
	 * 2、软件可以重新注册、注册完成后软件序列号的金额保持注销前的状态
	 */
	public static void testLogout() {
		try {
			int a = Client.getInstance().logout();
			System.out.println("testLogout:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 软件序列号注册、或则说是激活、软件序列号首次使用必须激活
	 * registEx(String serialpass)
	 * 1、serialpass 软件序列号密码、密码长度为6位的数字字符串、软件序列号和密码请通过亿美销售人员获取
	 */
	public static void testRegistEx() {
		int i = Client.getInstance().registEx("933081");
		System.out.println("testTegistEx:" + i);
	}
	/**
	 * 发送短信、可以发送定时和即时短信
	 * sendSMS(String sendTime, String[] mobiles, String smsContent,String addSerial, String srcCharset, int smsPriority)
	 * 1、mobiles 手机数组长度不能超过1000
	 * 2、sendTime 如果该参数不会空"",那么该条短信会在sendTime所指定的时间发送
	 * 3、smsContent 最多500个汉字或1000个纯英文、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内 
	 * 4、addSerial 附加码(长度小于15的字符串) 用户可通过附加码自定义短信类别,或添加自定义主叫号码( 联系亿美索取主叫号码列表)
	 * 5、srcCharset 字符编码，默认为"GBK"
	 * 6、优先级范围1~5，数值越高优先级越高(相对于同一序列号)
	 */
	public static void testSendSMS() {
		try {
			int i=Client.getInstance().sendSMS(null, new String[]{"13777840845"}, "短信测试","", "GBK", 5);
			System.out.println("testSendSMS:" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 软件序列号充值、如果软件序列号金额不足、那么可以调用该方法给序列号充值
	 * chargeUp(String cardNo, String cardPass)
	 * 1、cardNo 充值卡卡号
	 * 2、cardPass 充值卡密码
	 * 3、充值卡卡号和密码请联系亿美销售人员获得
	 */
	public static void testChargeUp() {
		try {
			int a = Client.getInstance().chargeUp("充值卡卡号", "密码");
			System.out.println("testChargeUp:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 企业详细信息注册
	 * registDetailInfo(String eName, String linkMan, String phoneNum,String mobile, String email, String fax, String address,String postcode)
	 * 1、eName 企业名称(最多60字节)
	 * 2、linkMan 联系人姓名(最多20字节)
	 * 3、phoneNum 联系电话(最多20字节)
	 * 4、mobile 联系手机(最多15字节)
	 * 5、email 电子邮件(最多60字节)
	 * 6、fax 联系传真(最多20字节)
	 * 7、address 公司地址(最多60字节)
	 * 8、postcode 邮政编码(最多6字节)
	 * 9、以上参数信息都必须填写、每个参数都不能为空
	 */
	public static void testRegistDetailInfo() {
		try {
			int a = Client.getInstance().registDetailInfo("企业名称", "联系人", "固话","13000000000", "邮件", "传真", "企业地址", "邮政编码");
			System.out.println("testRegistDetailInfo:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改软件序列号密码、注意修改软件序列号密码以后不需要重新注册(激活)
	 * serialPwdUpd(String serialPwd, String serialPwdNew)
	 * 1、serialPwd 旧密码
	 * 2、serialPwdNew 新密码、长度为6位的数字字符串
	 */
	public static void testSerialPwdUpd() {
		try {
			int a = Client.getInstance().serialPwdUpd("123456", "123456");
			System.out.println("testSerialPwdUpd:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取软件序列号的余额
	public static void testGetBalance() {
		try {
			double a = Client.getInstance().getBalance();
			System.out.println("testGetBalance:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取发送一条短信所需要的费用
	public static void testGetEachFee() {
		try {
			double a = Client.getInstance().getEachFee();
			System.out.println("testGetEachFee:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 1、从EUCP平台接收手机用户上行的短信
	 * 2、返回值list中的每个元素为一个Mo对象
	 * 4、Mo具体数据结构参考使用手册
	 */
	public static void testGetMO() {
		try {
			List<Mo> a = Client.getInstance().getMO();
			if (a != null) {
				System.out.println("testGetMO1size:" + a.size());
				Iterator<Mo> it = a.iterator();

				while (it.hasNext()) {
					Mo m = it.next();
					System.out.println("短信内容:" + m.getSmsContent());
					System.out.println("通道号:" + m.getChannelnumber());
					System.out.println("手机号:" + m.getMobileNumber());
					System.out.println("附加码:" + m.getAddSerialRev());
					System.out.println("附加码:" + m.getAddSerial());
					// 上行短信务必要保存,加入业务逻辑代码,如：保存数据库，写文件等等
				}
			} else {
				System.out.println("NO HAVE MO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 注册转接功能是指当用户直接回复我们发送的短信时、EUCP平台会把用户的回复信息直接转发到我们注册转接的手机号上、
	 * 最多可以注册10个手机号码或上行服务代码
	 * setMOForward(String forwardMobile)
	 * 1、forwardMobile 目标手机号码，有且只能有一个手机号码
	 * 2、其中还有一个每次可以设置多个转接号码，自己可以结合使用手册尝试使用
	 */
	public static void testSetMOForward() {
		try {
			int a = Client.getInstance().setMOForward("15800000000");
			System.out.println("testSetMOForward:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//取消转发功能，即用户的上行短信不会再直接转发到我们注册转接功能所指定的手机号码中
	public static void testCancelMOForward() {
		try {
			int a = Client.getInstance().cancelMOForward();
			System.out.println("testCancelMOForward:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
}
