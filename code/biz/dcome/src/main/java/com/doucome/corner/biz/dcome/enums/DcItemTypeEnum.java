package com.doucome.corner.biz.dcome.enums;

/**
 * �û�������Դ
 * ��ö�ٸ�Ӧ�ý���ϵͳ��Ϣ
 * @author langben 2012-8-27
 * 
 */
public enum DcItemTypeEnum {
	NORMAL("N"),
	VIRTUAL("V"),
	
	/**
	 * ����
	 */
	HUAFEI("HF") ,
	
	/**
	 * ���ֱ�
	 */
	JFB("JFB") ,
	
	/**
	 * Q��
	 */
	QQBI("QB") ,
	
	OTHER("O");
	
	private String itemType;
	
	private DcItemTypeEnum(String itemType) {
		this.itemType = itemType;
	}
	
	public String getItemType() {
		return this.itemType;
	}
}