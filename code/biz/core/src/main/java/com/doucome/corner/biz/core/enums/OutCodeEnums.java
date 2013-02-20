package com.doucome.corner.biz.core.enums;

public enum OutCodeEnums {

	/**
	 * ��ʾoutCode�������DDZ_ACCOUNT_ID
	 */
	DDZ_ACCOUNT_ID("A") ,
	
	/**
	 * ����۱����ֶ����
	 */
	DDZ_ACCOUNT_ID_MANUAL("B") ,
	
	/**
	 * ����ۼ��ֱ�����
	 */
	DDZ_ACCOUNT_ID_JFB("J") ,
	
	/**
	 * ��ޢ 
	 */
	DOUCOME("D"),
	
	/**
	 * ��ޢPK�
	 */
	DOUCOME_PROMOTION("P") ,
	
	/**
	 * ��ޢuser_id
	 */
	DOUCOME_USER_ID("U") ,
	
	/**
	 * ϵͳ�Զ�����û�й����Ĭ������
	 */
	SYSTEM("S") ,
	
	UNKNOWN("UN") ,
	;
	
	private OutCodeEnums(String name) {
		this.name = name ;
	}
	
	private String name ;

	public String getName() {
		return name;
	}
	
	public static OutCodeEnums toOutCodeEnums(String name){
		for(OutCodeEnums e : OutCodeEnums.values()){
			if(e.getName().equals(name)){
				return e ;
			}
		}
		return UNKNOWN ;
	}
	
}
