package com.doucome.corner.biz.core.service.upyun;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;

import com.doucome.corner.biz.core.enums.HttpMethodEnums;
import com.doucome.corner.biz.core.exception.UpyunException;
import com.doucome.corner.biz.core.service.upyun.model.UpyunDataEntity;

public class UpYunServiceImpl implements UpYunService , InitializingBean{

	private static final Log log = LogFactory.getLog(UpYunServiceImpl.class);

	HttpClient client = new DefaultHttpClient() ;

	private String username ;

	private String password ;
	
	private int timeout = 10 * 3000 ;
	
	private String uploadUrl ;

	private String md5Password ;

	@Override
	public void afterPropertiesSet() throws Exception {
		md5Password = UpYunUtils.md5(password) ;
	}
	

	private String buildRelativePath(String bucketname,String filePath) {
		return "/" + bucketname + filePath;
	}
	

	@Override
	public boolean deleteFile(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void uploadImageFromUrl(String imgUrl, String picPath , String bucketname) {
		HttpEntity entry = null ;
		try {		
		HttpGet get = new HttpGet(imgUrl) ;
		HttpResponse response = client.execute(get) ;
		entry = response.getEntity() ;
		InputStream is = entry.getContent();  
		byte[] imgBuffer = UpYunUtils.inputStream2Buf(is , (int)entry.getContentLength());
		
		//调用又拍上传
		UpyunDataEntity upyunEntity = new UpyunDataEntity() ;
		
		}catch(Exception e){
			log.error(e.getMessage() , e);
			throw new UpyunException("upload image err . " + e.getMessage() , e);
		}finally{
			try {
				EntityUtils.consume(entry);
			} catch (IOException e) {
				log.error(e.getMessage() , e);
			}
		}
	}

	
	public void upload(UpyunDataEntity upyunEntity) throws UpyunException {
		HttpURLConnection conn = null ;
		try{
			
			byte[] buf = upyunEntity.getData() ;
			String file = upyunEntity.getToPath() ;
			boolean autoCreateDirectory = upyunEntity.isAutoCreateDirectory() ;
			String bucketname = upyunEntity.getBucketname() ;
			String fileSecret = upyunEntity.getFileSecret() ;
			String contentMD5 = upyunEntity.getContentMD5() ;
			String toPath = upyunEntity.getToPath() ;
			String uri = buildRelativePath(bucketname, toPath) ;
			String dateStr = UpYunUtils.getGMTDate(new Date()) ;
			
			URL url = new URL(uploadUrl + "/"+bucketname + file);
		
		
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("PUT");
			conn.setUseCaches(false);
			conn.setRequestProperty("Date", dateStr );
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", String.valueOf(buf.length));
			conn.setRequestProperty("Authorization", UpYunUtils.sign(HttpMethodEnums.PUT ,dateStr , uri , buf.length , username , md5Password ));
			if(StringUtils.isNotBlank(contentMD5)){
				conn.setRequestProperty("Content-Md5", contentMD5);
			}
			if(StringUtils.isNotBlank(fileSecret)){
				conn.setRequestProperty("Content-Secret", fileSecret);
			}
			if(autoCreateDirectory){
				conn.setRequestProperty("mkdir", "true") ;
			}
		
		
			conn.connect();
			OutputStream os = conn.getOutputStream();
			byte[] data = buf ;
			int wl = 0;
			try {		
				os.write(data, 0, data.length);
			} finally {
				os.flush();
				if (os != null) {
					os.close();
				}
			}
			int responseCode = conn.getResponseCode() ;
			if(responseCode != 200){
				throw new UpyunException("http put error , statusCode:"+responseCode + ".msg:" + conn.getResponseMessage()) ;
			}
		}catch (IOException e) {
			throw new UpyunException(e.getMessage() , e) ;
		} catch(Exception e){
			throw new UpyunException(e.getMessage() , e) ;
		}finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}


	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}


	
	
}
