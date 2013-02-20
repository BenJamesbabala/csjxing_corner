package com.doucome.corner.web.dcome.action.frame.q;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.doucome.corner.biz.core.exception.UpyunException;
import com.doucome.corner.biz.core.service.upyun.UpYunUtils;

public class AppVoteVerifyImgProxyAction extends QBasicAction {

	
	private InputStream inputStream ;
	
	private String r ;
	
	private String aid ;

	@Override
	public String execute() throws Exception {
		HttpClient client = new DefaultHttpClient() ;

		HttpEntity entry = null ;

		HttpGet get = new HttpGet("http://captcha.qq.com/getimage?aid=" + aid + "&r=" + r) ;
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
			
//		HttpServletResponse servletResponse = getResponse() ;
//		
//		servletResponse.getOutputStream().write(imgBuffer) ;
		
		InputStream input = new ByteArrayInputStream(imgBuffer , 0 , imgBuffer.length) ;
		this.inputStream = input ;
		return SUCCESS ;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setR(String r) {
		this.r = r;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	
}
