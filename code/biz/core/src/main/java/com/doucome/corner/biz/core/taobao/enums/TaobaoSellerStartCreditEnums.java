package com.doucome.corner.biz.core.taobao.enums;

public enum TaobaoSellerStartCreditEnums {
	
	//1heart(一心) 2heart (两心) 3heart(三心) 4heart(四心) 5heart(五心) 1diamond(一钻) 2diamond(两钻) 3diamond(三钻) 4diamond(四钻) 5diamond(五钻) 1crown(一冠) 2crown(两冠) 3crown(三冠) 4crown(四冠) 5crown(五冠) 1goldencrown(一黄冠) 2goldencrown(二黄冠) 3goldencrown(三黄冠) 4goldencrown(四黄冠) 5goldencrown(五黄冠)
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

