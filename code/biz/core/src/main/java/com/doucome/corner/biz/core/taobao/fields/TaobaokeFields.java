package com.doucome.corner.biz.core.taobao.fields;

import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.constant.TaokeReportMembConst;

public class TaobaokeFields {

	public static final String[] taoke_item_fields = new String[] { 
		TaokeItemConst.click_url, 
		TaokeItemConst.shop_click_url,
		TaokeItemConst.commission, 
		TaokeItemConst.commission_rate,
        TaokeItemConst.commission_volume, 
        TaokeItemConst.price, 
        TaokeItemConst.num_iid, 
        TaokeItemConst.pic_url,
        TaokeItemConst.title 
    };
	
	public static final String[] taoke_report_memb_fields = new String[]{
		TaokeReportMembConst.app_key , 
		TaokeReportMembConst.category_id ,
		TaokeReportMembConst.category_name , 
		TaokeReportMembConst.commission , 
		TaokeReportMembConst.commission_rate , 
		TaokeReportMembConst.iid , 
		TaokeReportMembConst.item_num , 
		TaokeReportMembConst.item_title , 
		TaokeReportMembConst.num_iid , 
		TaokeReportMembConst.outer_code , 
		TaokeReportMembConst.pay_price , 
		TaokeReportMembConst.pay_time , 
		TaokeReportMembConst.real_pay_fee , 
		TaokeReportMembConst.seller_nick , 
		TaokeReportMembConst.shop_title , 
		TaokeReportMembConst.trade_id
	} ;
	
}
