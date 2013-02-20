package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dcome.exception.DcUnSupportOperationException;
import com.doucome.corner.biz.dcome.model.DcIntegralDesc;

/**
 * �û�������Դ ��ö�ٸ�Ӧ�ý���ϵͳ��Ϣ
 * 
 * @author langben 2012-8-27
 * 
 */
public enum DcIntegralSourceEnums {

	/**
	 * ÿ��ǩ��
	 */
	DAILY_CHECKIN("CHK", 2) {

		@Override
		public int getAwardIntegral(Object... obj) {
			return 1;
			/**
			 * try { Integer count = (Integer) obj[0]; if (count < 1) { return
			 * getAwardIntegral(); } int integralCount = getAwardIntegral() +
			 * CHECKIN_INC_STEP * (count-1); return integralCount >
			 * CHECKIN_LIMIT ? CHECKIN_LIMIT : integralCount; } catch (Exception
			 * e) { return getAwardIntegral(); }
			 **/
		}
	},

	/**
	 * �������Ʒ
	 */
	PUB_PROMOTION_ITEM("PUB", 0),

	/**
	 * ���û�ע��
	 */
	USER_REGISTER("REG", 5),

	/**
	 * ������, ���������ָ�����Ʒ��������
	 */
	BUY_REBATE("BUY", 0),
	
	/**
	 * άȨ�˿�۳�����
	 */
	REFUND("REFD" , 0) ,

	/**
	 * ����
	 */
	SHARE("SHR", 0),

	/**
	 * �����������û�.
	 */
	INVITED_USER("INVED", 10),
	/**
	 * ��������
	 */
	SEND_INVITE("INV", 0),
	/**
	 * ��Ʊ
	 */
	STEAL_PROMO_RATE("STL", -10),
	/**
	 * ����Ʊ
	 */
	STEALED_PROMO_RATE("STLED", 0),
	/**
	 * 
	 */
	ACTIVE_USER_AWARD("ACT", 0),
	/**
	 * ��ע�ռ�
	 */
	FOLLOW_QZONE("FOL", 5),
	/**
	 * ���־���
	 */
	AUCTION_BID("BID", 0),
	/**
	 * ϵͳ֪ͨ
	 */
	APP_NOTIFICATION("NOTI", 0) {
		@Override
		public DcIntegralDesc fromJson(String integralDesc) {
			DcIntegralDesc temp = new DcIntegralDesc();
			temp.setOtherInfo(integralDesc);
			return temp;
		}

		@Override
		public String toJson(DcIntegralDesc integralDesc) {
			if (integralDesc == null) {
				return null;
			}
			return integralDesc.getOtherInfo();
		}
	},
	APP_WARN("WARN", 0) {
		@Override
		public DcIntegralDesc fromJson(String integralDesc) {
			DcIntegralDesc temp = new DcIntegralDesc();
			temp.setOtherInfo(integralDesc);
			return temp;
		}

		@Override
		public String toJson(DcIntegralDesc integralDesc) {
			if (integralDesc == null) {
				return null;
			}
			return integralDesc.getOtherInfo();
		}
	},
	EXCHANGE_ITEM("EX", 0),

	UGC_ITEM("UGC", 15),

	ACCEPT_UGC("AUGC", 0),

	BUY_UGC("BUGC", 0),
	/**
	 * �ϻ�����Ϸ
	 */
	WINNER_GANE("WINNER", 0),

	/**
	 * 
	 */
	UNKNOWN("", 0) {
		@Override
		public DcIntegralDesc fromJson(String integralDesc) {
			throw new DcUnSupportOperationException("unknow integral source");
		}

		@Override
		public String toJson(DcIntegralDesc integralDesc) {
			throw new DcUnSupportOperationException("unknow integral source");
		}
	};

	private String value;

	private int awardIntegral;

	private static Map<String, DcIntegralSourceEnums> map = new HashMap<String, DcIntegralSourceEnums>();

	static {
		DcIntegralSourceEnums[] enums = values();
		for (DcIntegralSourceEnums e : enums) {
			map.put(e.getValue(), e);
		}
	}

	public static DcIntegralSourceEnums get(String key) {
		if (key == null) {
			return UNKNOWN;
		}

		DcIntegralSourceEnums result = map.get(key);
		if (result != null) {
			return result;
		}
		return UNKNOWN;
	}

	private DcIntegralSourceEnums(String value, int awardIntegral) {
		this.value = value;
		this.awardIntegral = awardIntegral;
	}

	public String getValue() {
		return value;
	}

	/**
	 * ��������.
	 * 
	 * @return
	 */
	public int getAwardIntegral() {
		return this.awardIntegral;
	}

	/**
	 * �������֣����ڻ�����Ҫ�����ⲿ����������������DAILY_CHECK_IN
	 * 
	 * @param obj
	 * @return
	 */
	public int getAwardIntegral(Object... obj) {
		return this.awardIntegral;
	}

	/**
	 * �ܹ��ɻ�ȡ���ٻ���.
	 * 
	 * @return
	 */
	public int getTotalIntegralCandraw() {
		return this.awardIntegral;
	}

	/**
	 * ��ȡintegraldesc����.
	 * 
	 * @param integralDesc
	 * @return
	 */
	public DcIntegralDesc fromJson(String integralDesc) {
		// �����ָ���쳣.
		if (StringUtils.isEmpty(integralDesc)) {
			return new DcIntegralDesc();
		}
		try {
			return JacksonHelper.fromJSON(integralDesc, DcIntegralDesc.class);
		} catch (Exception e) {
			return new DcIntegralDesc();
		}
	}

	/**
	 * ��ȡintegral desc�ַ���.
	 * 
	 * @param desc
	 * @return
	 */
	public String toJson(DcIntegralDesc desc) {
		// �����ָ���쳣.
		if (desc == null) {
			return null;
		}
		try {
			return JacksonHelper.toJSON(desc);
		} catch (Exception e) {
			return null;
		}
	}
}