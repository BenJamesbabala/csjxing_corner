package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dcome.exception.DcUnSupportOperationException;
import com.doucome.corner.biz.dcome.model.DcIntegralDesc;

/**
 * 用户积分来源 此枚举更应该叫做系统消息
 * 
 * @author langben 2012-8-27
 * 
 */
public enum DcIntegralSourceEnums {

	/**
	 * 每日签到
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
	 * 发布活动商品
	 */
	PUB_PROMOTION_ITEM("PUB", 0),

	/**
	 * 新用户注册
	 */
	USER_REGISTER("REG", 5),

	/**
	 * 购买返利, 购买奖励积分根据商品返利计算
	 */
	BUY_REBATE("BUY", 0),
	
	/**
	 * 维权退款，扣除积分
	 */
	REFUND("REFD" , 0) ,

	/**
	 * 分享
	 */
	SHARE("SHR", 0),

	/**
	 * 邀请来了新用户.
	 */
	INVITED_USER("INVED", 10),
	/**
	 * 邀请朋友
	 */
	SEND_INVITE("INV", 0),
	/**
	 * 抢票
	 */
	STEAL_PROMO_RATE("STL", -10),
	/**
	 * 被抢票
	 */
	STEALED_PROMO_RATE("STLED", 0),
	/**
	 * 
	 */
	ACTIVE_USER_AWARD("ACT", 0),
	/**
	 * 关注空间
	 */
	FOLLOW_QZONE("FOL", 5),
	/**
	 * 积分竞价
	 */
	AUCTION_BID("BID", 0),
	/**
	 * 系统通知
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
	 * 老虎机游戏
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
	 * 奖励积分.
	 * 
	 * @return
	 */
	public int getAwardIntegral() {
		return this.awardIntegral;
	}

	/**
	 * 奖励积分，用于积分需要根据外部参数计算的情况，如DAILY_CHECK_IN
	 * 
	 * @param obj
	 * @return
	 */
	public int getAwardIntegral(Object... obj) {
		return this.awardIntegral;
	}

	/**
	 * 总共可获取多少积分.
	 * 
	 * @return
	 */
	public int getTotalIntegralCandraw() {
		return this.awardIntegral;
	}

	/**
	 * 获取integraldesc对象.
	 * 
	 * @param integralDesc
	 * @return
	 */
	public DcIntegralDesc fromJson(String integralDesc) {
		// 避免空指针异常.
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
	 * 获取integral desc字符串.
	 * 
	 * @param desc
	 * @return
	 */
	public String toJson(DcIntegralDesc desc) {
		// 避免空指针异常.
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