package com.doucome.corner.biz.dcome.enums;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ��Ϸö��
 * @author ze2200
 *
 */
public enum DcGameEnum {
	
	GOLDEN_EGG("EGG", 10, 3),
	UNKNOWN("UNKNOWN", 0, -1);
	
	private String type;
	private int playLimit;
	private int exchangeAmount;
	
	private DcGameEnum(String type, int playLimit, int exchangeAmount) {
		this.type = type;
		this.playLimit = playLimit;
		this.exchangeAmount = exchangeAmount;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getPlayLimit() {
		return this.playLimit;
	}
	
	public int getExchangeAmount() {
		return exchangeAmount;
	}
	
	/**
	 * ��Ϸʱ��id.�Ե�ǰ�����ʱ�乹��
	 * @return
	 */
	public String getTimeId() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		//���22���Ժ�ʱ����ǵڶ���22:00:00
		if (hour >= 22) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	
	public static DcGameEnum getInstance(String game) {
		for (DcGameEnum temp: DcGameEnum.values()) {
			if (temp.getType().equalsIgnoreCase(game)) {
				return temp;
			}
		}
		try {	
			return Enum.valueOf(DcGameEnum.class, game.toUpperCase());
		} catch (Exception e) {
			return UNKNOWN;
		}
	}
}
