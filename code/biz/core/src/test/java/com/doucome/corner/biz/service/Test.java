package com.doucome.corner.biz.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.doucome.corner.biz.core.exception.UpyunException;
import com.doucome.corner.biz.core.service.upyun.UpYunUtils;

public class Test {

	public static void main(String[] args) throws Exception  {
HttpClient client = new DefaultHttpClient() ;
		
		HttpEntity entry = null ;
		try {		
			HttpGet get = new HttpGet("http://captcha.qq.com/getimage?aid=8000108&r=0.3266268726438284") ;
			HttpResponse response = client.execute(get) ;
			int statusCode = response.getStatusLine().getStatusCode() ;
			if(statusCode != 200){
				throw new UpyunException("get resource error , errcode :" + statusCode) ;
			}
			entry = response.getEntity() ;
			long len = entry.getContentLength() ;
			
			InputStream is = entry.getContent();  
			byte[] imgBuffer = UpYunUtils.inputStream2Buf(is , (int)entry.getContentLength());
			if(imgBuffer == null){
				throw new IllegalArgumentException("img url is not correct .") ;
			}
			
			FileOutputStream o = new FileOutputStream(new File("d:/ver.png")) ;
			o.write(imgBuffer) ;
			o.close() ;
		}catch (UpyunException e) {
	}
	}
}
