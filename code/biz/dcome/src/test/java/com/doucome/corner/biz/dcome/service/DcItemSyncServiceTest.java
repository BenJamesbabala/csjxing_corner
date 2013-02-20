package com.doucome.corner.biz.dcome.service;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.unittest.AbstractBaseJUnit4Test;
import com.doucome.corner.web.common.model.ResultModel;
import com.sun.swing.internal.plaf.basic.resources.basic;

@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcItemSyncServiceTest extends AbstractBaseJUnit4Test {

	@Autowired
	DcItemSyncService dcitemsyncService ;
	
	@Test
	public void testSyncItemDiscount(){
		int syncCount = dcitemsyncService.syncItemDiscount(null, null);
		System.out.println("syncCount: " + syncCount);
	}
	
	@Test
    public void downloadImg() {
        DefaultHttpClient client = new DefaultHttpClient() ;
		
		CookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie o_cookie = setCookie("o_cookie", "452232914");
		cookieStore.addCookie(o_cookie);
		o_cookie = setCookie("pt2gguin", "o452232914");
		cookieStore.addCookie(o_cookie);
		o_cookie = setCookie("uin", "o452232914");
		cookieStore.addCookie(o_cookie);
		o_cookie = setCookie("ptisp", "ctc");
		cookieStore.addCookie(o_cookie);
//		o_cookie = setCookie("verifysession", "h000abf7beab2f4d0e139b11fb56c917907e96bef16f5f3f4d2c4d34725f50c09e48f31ab5aaf4a626c");
//		cookieStore.addCookie(o_cookie);
		HttpContext context = new BasicHttpContext();
		context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		
		HttpEntity entry = null ;
		
		HttpGet get = new HttpGet("http://captcha.qq.com/getimage?aid=8000108" + "&r=" + Math.random());
		try {
			HttpResponse response = client.execute(get, context);
			
			cookieStore = (CookieStore) context.getAttribute(ClientContext.COOKIE_STORE);
			System.out.println("---------------");
			for (org.apache.http.cookie.Cookie cookie: cookieStore.getCookies()) {
				System.out.println(cookie.getName() + ": " + cookie.getValue());
			}
			context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	private BasicClientCookie setCookie(String name, String value) {
		BasicClientCookie cookie = new BasicClientCookie(name, value);
		cookie.setVersion(0);
		cookie.setDomain(".qq.com");
		cookie.setPath("/");
		cookie.setSecure(true);
		return cookie;
	}
}
