package com.doucome.corner.biz.dcome.enums;

/**
 * ��Ʒ״̬.
 * @author ze2200
 *
 */
public enum DcItemStatusEnum {
	/**
	 * ����״̬
	 */
	NORMAL("N"),
	/**
	 * �¼�״̬
	 */
	DISABLE("D");
	
	private String value;
	
	private DcItemStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	/**
	 * ��ȡ����ʵ��
	 * @param status
	 * @return
	 */
	public static DcItemStatusEnum getInstance(String status) {
		try {
			return valueOf(DcItemStatusEnum.class, status);
		} catch (Exception e) {
			for (DcItemStatusEnum statusEnum: values()) {
				if (statusEnum.getValue().equalsIgnoreCase(status)) {
					return statusEnum;
				}
			}
		}
		return null;
	}
}
