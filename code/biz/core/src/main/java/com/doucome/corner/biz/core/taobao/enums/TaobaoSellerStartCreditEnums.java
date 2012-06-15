package com.doucome.corner.biz.core.taobao.enums;

public enum TaobaoSellerStartCreditEnums {
	
	//1heart(һ��) 2heart (����) 3heart(����) 4heart(����) 5heart(����) 1diamond(һ��) 2diamond(����) 3diamond(����) 4diamond(����) 5diamond(����) 1crown(һ��) 2crown(����) 3crown(����) 4crown(�Ĺ�) 5crown(���) 1goldencrown(һ�ƹ�) 2goldencrown(���ƹ�) 3goldencrown(���ƹ�) 4goldencrown(�Ļƹ�) 5goldencrown(��ƹ�)
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

