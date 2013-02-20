package com.doucome.corner.web.wryneck.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.exception.UpyunException;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.service.upyun.UpYunUtils;

/**
 * 
 * @author langben 2013-1-2
 *
 */
@SuppressWarnings("serial")
public class WryneckPicDownloadAction extends WryneckBasicAction {

	private InputStream inputStream ;
	
	/**
	 * 
	 */
	private String fileName ;
	
	private String name ;
	
	@Override
	public String execute() throws Exception {
		
		HttpClient client = new DefaultHttpClient() ;

		HttpEntity entry = null ;

		String imgUrl = DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER) + "/wryneck/" + name ;
		
		HttpGet get = new HttpGet(imgUrl) ;
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
					
		InputStream input = new ByteArrayInputStream(imgBuffer , 0 , imgBuffer.length) ;
		this.inputStream = input ;
		this.fileName = System.currentTimeMillis() + ".jpg" ;
		return SUCCESS ;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
