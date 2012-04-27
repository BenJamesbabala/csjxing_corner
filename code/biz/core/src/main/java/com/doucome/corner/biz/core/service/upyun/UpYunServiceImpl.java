package com.doucome.corner.biz.core.service.upyun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.doucome.corner.biz.core.enums.HttpMethodEnums;

public class UpYunServiceImpl implements UpYunService {

	private static final Log logger = LogFactory.getLog(UpYunServiceImpl.class);

	

	private static String bucketname = "diandian";

	private static String username = "langben";

	private static String password = "1111112c";

	private String md5Password = UpYunUtils.md5(password);

	@Override
	public boolean uploadFile(String path, File file, boolean auto) {
		HttpClient httpClient = new DefaultHttpClient();
		md5Password = UpYunUtils.md5(password);
		String url = "http://test.api.upyun.com/" + bucketname + "/" + path;
		try {
			//InputStream in = new FileInputStream(file);
			HttpPut request = new HttpPut(url);
//			MultipartEntity mpEntity = new MultipartEntity();
//			ContentBody contb = new FileBody(file);
//			StringBody descript = new StringBody(file.getName());
//			mpEntity.addPart("file", contb);
//			mpEntity.addPart("descript",descript);
			
//			request.setEntity(mpEntity);
//		
			
			InputStream in = new FileInputStream(file) ;
			
			int l = in.available();
			String str_md5 = UpYunUtils.md5(in);
			String gmtDate = UpYunUtils.getGMTDate(new Date());
			String relativePath = buildRelativePath(path, bucketname);
			String sign = UpYunUtils.sign(HttpMethodEnums.PUT, gmtDate,
					relativePath, l, username, md5Password);
			
			request.setHeader("Authorization", sign);
			request.setHeader("Content-Md5", str_md5);
			request.setHeader("Date" ,  gmtDate ) ;
			
			//request.setHeader("Content-Length", String.valueOf(l) ) ;
			
			// request.setHeader("Content-Length", String.valueOf(l)) ;
			
			if (auto) {
				request.setHeader("mkdir", String.valueOf(auto));
			}			
			
			HttpParams params = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 300000);
			HttpConnectionParams.setSoTimeout(params, 300000);
			HttpResponse response = httpClient.execute(request);
			
			System.out.println(response);
			int a = 5;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	private String buildRelativePath(String filePath, String bucketname) {
		return "/" + bucketname + "/" + filePath;
	}

	@Override
	public boolean deleteFile(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		UpYunService up = new UpYunServiceImpl();

		up.uploadFile("test1223.jpg", new File("d:/test1.jpg"), true);
		System.out.println(UpYunUtils.getGMTDate(new Date()));

	}
}
