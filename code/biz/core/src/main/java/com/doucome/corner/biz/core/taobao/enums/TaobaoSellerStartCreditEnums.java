package com.doucome.corner.biz.core.taobao.enums;

public enum TaobaoSellerStartCreditEnums {
	
	//1heart(һ��) 2heart (����) 3heart(����) 4heart(����) 5heart(����) 1diamond(һ��) 2diamond(����) 3diamond(����) 4diamond(����) 5diamond(����) 1crown(һ��) 2crown(����) 3crown(����) 4crown(�Ĺ�) 5crown(���) 1goldencrown(һ�ƹ�) 2goldencrown(���ƹ�) 3goldencrown(���ƹ�) 4goldencrown(�Ļƹ�) 5goldencrown(��ƹ�)
	credit_1heart(1 , "1heart") , 
	credit_2heart(2 , "2heart") ,
	credit_3heart(3 , "3heart") ,
	credit_4heart(4 , "4heart") , 
	credit_5heart(5 , "5heart") ,
	credit_1diamond(6 , "1diamond") , 
	credit_2diamond(7 , "2diamond") ,
	credit_3diamond(8 , "3diamond") , 
	credit_4diamond(9  , "4diamond") ,
	credit_5diamond(10 , "5diamond") ,
	credit_1crown(11 , "1crown") ,
	credit_2crown(12 , "2crown") ,
	credit_3crown(13  , "3crown") ,
	credit_4crown(14 ,  "4crown") ,
	credit_5crown(15 , "5crown") ,
	credit_1goldencrown(16 , "1goldencrown") ,
	credit_2goldencrown(17 , "2goldencrown") ,
	credit_3goldencrown(18 , "3goldencrown") ,
	credit_4goldencrown(19 , "4goldencrown") ,
	credit_5goldencrown(20 , "5goldencrown") ,
	;
	
	private TaobaoSellerStartCreditEnums(int code , String value){
		this.code = code ;
		this.value = value ;
	}

	private String value ;
	
	private int code ;
	
	public int getCode() {
		return code;
	}



	public String getValue() {
		return value;
	}

	
}

