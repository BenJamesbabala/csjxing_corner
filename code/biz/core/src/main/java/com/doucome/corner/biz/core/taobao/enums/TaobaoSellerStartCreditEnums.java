package com.doucome.corner.biz.core.taobao.enums;

public enum TaobaoSellerStartCreditEnums {
	
	//1heart(一心) 2heart (两心) 3heart(三心) 4heart(四心) 5heart(五心) 1diamond(一钻) 2diamond(两钻) 3diamond(三钻) 4diamond(四钻) 5diamond(五钻) 1crown(一冠) 2crown(两冠) 3crown(三冠) 4crown(四冠) 5crown(五冠) 1goldencrown(一黄冠) 2goldencrown(二黄冠) 3goldencrown(三黄冠) 4goldencrown(四黄冠) 5goldencrown(五黄冠)
	credit_1heart(1 , "1heart" , "s_red_1") , 
	credit_2heart(2 , "2heart" , "s_red_2") ,
	credit_3heart(3 , "3heart" , "s_red_3") ,
	credit_4heart(4 , "4heart" , "s_red_4") , 
	credit_5heart(5 , "5heart" , "s_red_5") ,
	credit_1diamond(6 , "1diamond" , "s_blue_1") , 
	credit_2diamond(7 , "2diamond" , "s_blue_2") ,
	credit_3diamond(8 , "3diamond" , "s_blue_3") , 
	credit_4diamond(9  , "4diamond", "s_blue_4") ,
	credit_5diamond(10 , "5diamond" , "s_blue_5") ,
	credit_1crown(11 , "1crown" , "s_cap_1") ,
	credit_2crown(12 , "2crown" , "s_cap_2") ,
	credit_3crown(13  , "3crown" , "s_cap_3") ,
	credit_4crown(14 ,  "4crown" ,"s_cap_4") ,
	credit_5crown(15 , "5crown" , "s_cap_5") ,
	credit_1goldencrown(16 , "1goldencrown" , "s_crown_1") ,
	credit_2goldencrown(17 , "2goldencrown" , "s_crown_2") ,
	credit_3goldencrown(18 , "3goldencrown" , "s_crown_3") ,
	credit_4goldencrown(19 , "4goldencrown" , "s_crown_4") ,
	credit_5goldencrown(20 , "5goldencrown" , "s_crown_5") ,
	;
	
	private TaobaoSellerStartCreditEnums(int code , String value , String pic){
		this.code = code ;
		this.value = value ;
		this.pic = pic ;
	}

	private String value ;
	
	private int code ;
	
	private String pic ;
	
	public int getCode() {
		return code;
	}

	public String getPic() {
		return pic;
	}

	public String getValue() {
		return value;
	}

	public static TaobaoSellerStartCreditEnums fromCode(int code){
		for(TaobaoSellerStartCreditEnums e : TaobaoSellerStartCreditEnums.values()){
			if(e.getCode() == code){
				return e ;
			}
		}
		return credit_1heart ;
	}
	
}

