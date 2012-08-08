package cn.emay.sdk.example;

import cn.emay.sdk.api.Mo;

import java.util.Iterator;
import java.util.List;

public class TestClient {

	public static String softwareSerialNo="XXXX-XXX-XXXX-XXXXX";//������к�,��ͨ������������Ա��ȡ
	public static String key="XXXXXX";//���к��״μ���ʱ�Լ��趨
	public static String password="XXXXXX";// ����,��ͨ������������Ա��ȡ
	//public static Client sdkclient = null;
	
	public static void main(String[] args) {
		try {		
			//sdkclient=new Client("0SDK-EMY-0130-OCXQQ","888888");
			StartMenu();
			while (true) {
				System.out.println("��������Ž��в���");
				byte[] command = new byte[4];
				System.in.read(command);
				int operate = 0;
				try {
					String commandString = new String(command);
					commandString = commandString.replaceAll("\r\n", "").trim();
					operate = Integer.parseInt(commandString);
				} catch (Exception e) {
					System.out.println("����������󣡣���");
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
					System.out.println("��������� "+operate);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void StartMenu() {
		int i = 1;
		System.out.println(i + "���������к�,����ʹ�á���ע�����ٴ�ʹ��ʱ���ø÷���");
		i += 1;
		System.out.println(i + "��ע�����к�");
		i += 1;
		System.out.println(i + "����ҵ��Ϣע��,Ŀ�����ڶ��ŷ����쳣ʱEmay������ϵ����ҵ");
		i += 1;
		System.out.println(i + "������ѯ");
		i += 1;
		System.out.println(i + "����ֵ");
		i += 1;
		System.out.println(i + "�������޸�");
		i += 1;
		System.out.println(i + "�����Ͷ���");
		i += 1;
		System.out.println(i + "����ȡ�������ŷ���");
		i += 1;
		System.out.println(i + "����ȡ���ж���");
		i += 1;
		System.out.println(i + "��ע�����ж���ת��");
		i += 1;
		System.out.println(i + "��ȡ�����ж���ת��");
		i += 1;
		System.out.println(i + "���ر��˳�");
	}
	
	/**
	 * ���ע��
	 * 1�����ע�������Ͷ��š��������ж��Žӿڶ��޷�ʹ��
	 * 2�������������ע�ᡢע����ɺ�������кŵĽ���ע��ǰ��״̬
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
	 * ������к�ע�ᡢ����˵�Ǽ��������к��״�ʹ�ñ��뼤��
	 * registEx(String serialpass)
	 * 1��serialpass ������к����롢���볤��Ϊ6λ�������ַ�����������кź�������ͨ������������Ա��ȡ
	 */
	public static void testRegistEx() {
		int i = Client.getInstance().registEx("933081");
		System.out.println("testTegistEx:" + i);
	}
	/**
	 * ���Ͷ��š����Է��Ͷ�ʱ�ͼ�ʱ����
	 * sendSMS(String sendTime, String[] mobiles, String smsContent,String addSerial, String srcCharset, int smsPriority)
	 * 1��mobiles �ֻ����鳤�Ȳ��ܳ���1000
	 * 2��sendTime ����ò��������"",��ô�������Ż���sendTime��ָ����ʱ�䷢��
	 * 3��smsContent ���500�����ֻ�1000����Ӣ�ġ���ͻ���Ҫ���в�ֶ�������������ɻ��ҡ���������ƽ̨�����ʵ��ͨ���Զ���֡��Ʒ���ʵ�ʲ������Ϊ׼�������Ƽ����ų���70������ 
	 * 4��addSerial ������(����С��15���ַ���) �û���ͨ���������Զ���������,������Զ������к���( ��ϵ������ȡ���к����б�)
	 * 5��srcCharset �ַ����룬Ĭ��Ϊ"GBK"
	 * 6�����ȼ���Χ1~5����ֵԽ�����ȼ�Խ��(�����ͬһ���к�)
	 */
	public static void testSendSMS() {
		try {
			int i=Client.getInstance().sendSMS(null, new String[]{"13777840845"}, "���Ų���","", "GBK", 5);
			System.out.println("testSendSMS:" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ������кų�ֵ�����������кŽ��㡢��ô���Ե��ø÷��������кų�ֵ
	 * chargeUp(String cardNo, String cardPass)
	 * 1��cardNo ��ֵ������
	 * 2��cardPass ��ֵ������
	 * 3����ֵ�����ź���������ϵ����������Ա���
	 */
	public static void testChargeUp() {
		try {
			int a = Client.getInstance().chargeUp("��ֵ������", "����");
			System.out.println("testChargeUp:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ҵ��ϸ��Ϣע��
	 * registDetailInfo(String eName, String linkMan, String phoneNum,String mobile, String email, String fax, String address,String postcode)
	 * 1��eName ��ҵ����(���60�ֽ�)
	 * 2��linkMan ��ϵ������(���20�ֽ�)
	 * 3��phoneNum ��ϵ�绰(���20�ֽ�)
	 * 4��mobile ��ϵ�ֻ�(���15�ֽ�)
	 * 5��email �����ʼ�(���60�ֽ�)
	 * 6��fax ��ϵ����(���20�ֽ�)
	 * 7��address ��˾��ַ(���60�ֽ�)
	 * 8��postcode ��������(���6�ֽ�)
	 * 9�����ϲ�����Ϣ��������д��ÿ������������Ϊ��
	 */
	public static void testRegistDetailInfo() {
		try {
			int a = Client.getInstance().registDetailInfo("��ҵ����", "��ϵ��", "�̻�","13000000000", "�ʼ�", "����", "��ҵ��ַ", "��������");
			System.out.println("testRegistDetailInfo:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �޸�������к����롢ע���޸�������к������Ժ���Ҫ����ע��(����)
	 * serialPwdUpd(String serialPwd, String serialPwdNew)
	 * 1��serialPwd ������
	 * 2��serialPwdNew �����롢����Ϊ6λ�������ַ���
	 */
	public static void testSerialPwdUpd() {
		try {
			int a = Client.getInstance().serialPwdUpd("123456", "123456");
			System.out.println("testSerialPwdUpd:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��ȡ������кŵ����
	public static void testGetBalance() {
		try {
			double a = Client.getInstance().getBalance();
			System.out.println("testGetBalance:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ����һ����������Ҫ�ķ���
	public static void testGetEachFee() {
		try {
			double a = Client.getInstance().getEachFee();
			System.out.println("testGetEachFee:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 1����EUCPƽ̨�����ֻ��û����еĶ���
	 * 2������ֵlist�е�ÿ��Ԫ��Ϊһ��Mo����
	 * 4��Mo�������ݽṹ�ο�ʹ���ֲ�
	 */
	public static void testGetMO() {
		try {
			List<Mo> a = Client.getInstance().getMO();
			if (a != null) {
				System.out.println("testGetMO1size:" + a.size());
				Iterator<Mo> it = a.iterator();

				while (it.hasNext()) {
					Mo m = it.next();
					System.out.println("��������:" + m.getSmsContent());
					System.out.println("ͨ����:" + m.getChannelnumber());
					System.out.println("�ֻ���:" + m.getMobileNumber());
					System.out.println("������:" + m.getAddSerialRev());
					System.out.println("������:" + m.getAddSerial());
					// ���ж������Ҫ����,����ҵ���߼�����,�磺�������ݿ⣬д�ļ��ȵ�
				}
			} else {
				System.out.println("NO HAVE MO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ע��ת�ӹ�����ָ���û�ֱ�ӻظ����Ƿ��͵Ķ���ʱ��EUCPƽ̨����û��Ļظ���Ϣֱ��ת��������ע��ת�ӵ��ֻ����ϡ�
	 * ������ע��10���ֻ���������з������
	 * setMOForward(String forwardMobile)
	 * 1��forwardMobile Ŀ���ֻ����룬����ֻ����һ���ֻ�����
	 * 2�����л���һ��ÿ�ο������ö��ת�Ӻ��룬�Լ����Խ��ʹ���᳢ֲ��ʹ��
	 */
	public static void testSetMOForward() {
		try {
			int a = Client.getInstance().setMOForward("15800000000");
			System.out.println("testSetMOForward:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ȡ��ת�����ܣ����û������ж��Ų�����ֱ��ת��������ע��ת�ӹ�����ָ�����ֻ�������
	public static void testCancelMOForward() {
		try {
			int a = Client.getInstance().cancelMOForward();
			System.out.println("testCancelMOForward:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
}
