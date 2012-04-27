package com.doucome.corner.biz.core.service.taobao;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public class TaobaoSessionTest {

	public static void main(String[] args) {
//		
//		String authCode = "TOP-10f705b9ce7360528b404d15e4326282d7x04f8N16K7692yDvFFvtquU9dbzS9a-END" ;
//		String url = "http://container.open.taobao.com/container?authcode=" + authCode ;
//		
//		HttpClient hc = new DefaultHttpClient() ;
//		 //m = new DEFAULTg(url) ;
//		try {
//			int i = hc.executeMethod(m) ;
//			if(i == 200){
//				String out = m.getResponseBodyAsString() ;
//				System.out.println(out);
//			}
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String url1 = "http://container.open.taobao.com/container?authcode={ÊÚÈ¨Âë}" ;
//	
		
		HttpClient hc = new DefaultHttpClient() ;
		HttpUriRequest r = new HttpDelete();
		//r.
		//hc.execute(r) ;
	}
}
