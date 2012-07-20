package com.doucome.corner.biz.core.service.taobao;

import java.util.Date;
import java.util.List;

import org.apache.http.impl.cookie.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.constant.TaokeReportMembConst;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeReportMemberDTO;
import com.doucome.corner.biz.core.taobao.model.TaobaokeDate;
import com.doucome.corner.biz.core.taobao.model.TaokeCaturlCondition;
import com.doucome.corner.biz.core.taobao.model.TaokeItemSearchCondition;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations = { "classpath:biz-core-test.xml" })
public class TaobaokeServiceDecoratorTest extends AbstractBaseJUnit4Test {

	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Test
	public void test(){
		//http://store.taobao.com/shop/view_shop.htm?user_number_id=726566298&tracelog=wdpda_jrdp&wwnick
		List o = taobaokeServiceDecorator.conventShops(new String[]{"726566298"} , "" , new String[]{
				TaokeItemConst.click_url ,
				TaokeItemConst.commission ,
				TaokeItemConst.commission_volume , 
				TaokeItemConst.commission_rate ,
				TaokeItemConst.price ,
				TaokeItemConst.pic_url
		}) ;
		for(Object a : o){
			System.out.println(a);
		}
		
	}
	
	@Test
	public void test_item(){
		List<TaobaokeItemDTO> o = taobaokeServiceDecorator.conventItems(new String[]{"12222224756"} , "ibsuccess" , new String[]{
				TaokeItemConst.click_url ,
				TaokeItemConst.commission ,
				TaokeItemConst.commission_volume , 
				TaokeItemConst.commission_rate ,
				TaokeItemConst.price ,
				TaokeItemConst.pic_url
		}) ;
		for(TaobaokeItemDTO a : o){
			System.out.println(a);
		}
	}

	@Test
	public void test_getReport() throws Exception{
		/**
		 * http://www.dujiaok.com/home/index.htm?top_appkey=12512482&top_parameters=ZXhwaXJlc19pbj0zMTUzNjAwMSZpZnJhbWU9MSZyZV9leHBpcmVzX2luPTAmcmVmcmVzaF90b2tlbj02MTAxNjI1YWE4Y2QxMzI1YWM1YmFiMGE4MTZmNWEyZTQxMzI1NzRjZmJhNTIxMjY2NDQyOTg5JnRzPTEzMzA2ODQwOTYzNTYmdmlzaXRvcl9pZD02NjQ0Mjk4OSZ2aXNpdG9yX25pY2s9Ymx1ZWNvbA%253D%253D&top_session=6100325c033f0978829e8328831d34a011c96d656a530a466442989&top_sign=toCJIM8uRvGVYo%252BXpl7V%252Bg%253D%253D
		 */
		
		Date d = DateUtils.parseDate("2012-05-28", new String[]{"yyyy-MM-dd"}) ;
		
		QueryResult<TaobaokeReportMemberDTO> result = taobaokeServiceDecorator.getReport(new TaobaokeDate(d), new String[]{
			TaokeReportMembConst.item_num , 
			TaokeReportMembConst.item_title ,
			TaokeReportMembConst.commission_rate ,
			TaokeReportMembConst.commission , 
			TaokeReportMembConst.outer_code ,
			TaokeReportMembConst.pay_price ,
			TaokeReportMembConst.pay_time ,
			TaokeReportMembConst.real_pay_fee , 
			TaokeReportMembConst.seller_nick , 
			TaokeReportMembConst.trade_id
		}, new Pagination(1)) ;
		
		
		System.out.println(result);
	}

	@Test
	public void test_getItems(){
		TaokeItemSearchCondition condition = new TaokeItemSearchCondition() ;
		//condition.setArea("º¼ÖÝ");
		
		condition.setKeyword("ÌÔ½ð±Ò£¡ Ä¤·¨ÊÀ¼Ò1908²ÝÝ®ËáÄÌÃæÄ¤100g ÃÀ°×±£Êª²¹Ë®¿¹Ãô¿¹·øÉä");
		condition.setAutoSend(false) ;
		condition.setOuterCode("code") ;
		condition.setEndCommissionNum(102L) ;
		condition.setSevendaysReturn(true) ;
		QueryResult<TaobaokeItemDTO> result = taobaokeServiceDecorator.getItems(condition, new String[]{
				TaokeItemConst.click_url ,
				TaokeItemConst.commission ,
				TaokeItemConst.commission_volume , 
				TaokeItemConst.commission_rate ,
				TaokeItemConst.price ,
				TaokeItemConst.pic_url
		}
		, new Pagination(1)) ;
	}
	
	@Test
	public void test_getCaturl(){
		TaokeCaturlCondition condition = new TaokeCaturlCondition() ;
		condition.setCid(150401L) ;
		String url = taobaokeServiceDecorator.getCaturl(condition) ;
		System.out.println(url);
	}
}
