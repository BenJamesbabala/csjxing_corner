/**
 * Copyright (C) 1998-2009 TENCENT Inc.All Rights Reserved.		
 * 																	
 * FileName：ApiClient.java					
 *			
 * Description：API客户端，可使用该类型方便地操作API协议
 * History：
 *  2.0  2010-02-26        修改API协议的调用方式以及sign校验方式。不兼容1.0的接口。
 *  1.2  2009-05-27        新增getLastResponseBody方法
 *  1.2  2009-05-25        修正了invoke方法的一个bug，该bug导致读取带换行的response时，校验sign失败
 *  1.1  2009-04-02        增加debug开关，以便关闭Debug信息
 *  1.0  2009-04-02        Create
 */

package com.paipai.api.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;



/** 
 * API客户端，用于操作拍拍API协议。<br>
 * ApiClient不作多线程安全保证，多线程编程建议不要使用同一个ApiClient对象。
 * 
 * @author hokyhu
 * @version 2.0
 */

public class ApiClient
{
	private String secretKey;
	
	private String charset = "gbk";
	private Map<String, String> apiParams = new HashMap<String, String>();
	
	private boolean needSignCheck = false;

	private final static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private HttpClient httpClient = new DefaultHttpClient();

	private long lastErrNum = 0;
    private String lastErrMsg = "";
    
    private boolean debug = false;	// 决定是否输出Debug信息
    
    long lastSeqNum = 0;				// 最后一个请求包的seqno
    byte[] lastResponseContent = null;	// 最后一个成功返回的响应包内容
    String lastResponseContentType;
    
    long lastResponseVersion = 0;		// 最后一个成功返回的响应包版本
    
    public String getLastErrMsg()
    {
    	return lastErrMsg;
    }
    
    public long getLastErNum()
    {
    	return lastErrNum;
    }
    
    public void setCharset(String charset) {
    	if(!Charset.isSupported(charset)) {
    		throw new UnsupportedCharsetException(charset);
    	}
    	this.charset = charset;
    }
    
    public void setFormat(String format) {
    	addParam(ReservedName.FORMAT, format);
    }
    
    public void setPureData(byte pureData) {
    	addParam(ReservedName.PUREDATA, pureData);
    }

	/**
	 * 设置是否需要ApiClient输出debug信息
	 * @param isDebug true则输出debug信息；false不输出debug信息
	 */
    public void setDebug(boolean debug)
    {
    	this.debug = debug;
    }

    public long getLastRespVersion()
    {
    	return lastResponseVersion;
    }

    /**
     * 取得前一次成功的API调用返回的响应包的内容
     * @return 前一次成功的API调用返回的响应包的内容
     */
    public byte[] getLastResponseContent()
    {
    	return lastResponseContent;
    }
    
    public String getLastResponseContentType()
    {
    	return lastResponseContentType;
    }
    
    private void addParam(ReservedName name, String value) {
    	apiParams.put(name.toString(), value);
    }
    
    private void addParam(ReservedName name, Number value) {
    	apiParams.put(name.toString(), value.toString());
    }

    public ApiClient(String spid, String secretKey, long uin, String token)
    {
    	addParam(ReservedName.UIN, uin);
    	addParam(ReservedName.SPID, spid);
    	addParam(ReservedName.TOKEN, token);
    	this.secretKey = secretKey;
    	needSignCheck = true;
    }

    public ApiClient(long uin, String sessionKey)
    {
    	addParam(ReservedName.UIN, uin);
    	addParam(ReservedName.SKEY, sessionKey);
    }

    public ApiClient()
    {

    }
    /**
     * 将时间对象转换成拍拍制定的字符串格式
     * @param date 日期时间
     * @return 返回时间字符串格式
     */
    public static String getTimeString(Date date)
    {
    	return timeFormat.format(date);
    }



    /**
     * 生成md5校验码
     * @param strContent 传送到API服务器的head和body内容
     * @return 返回md5校验码
     */
//    private String getSign(String strContent)
//    {
//        return Md5Util.makeMd5Sum((strContent + spSecretKey).getBytes());
//    }

	private static String getCmd(String requestURI) {
		byte[] uri = requestURI.getBytes();
		int posStart = 0;
		while(uri[posStart] == '/') {	// 去掉开通的'/'
			posStart ++;
		}
		int posEnd = posStart;
		int index = posStart;
		while(index < uri.length) {	// 替换中间的'/'
			if(uri[index] == '/') {
				uri[index] = '.';
			}
			else if(uri[index] == '.') {
				posEnd = index;
			}
			index ++;
		}
		return new String(uri, posStart, posEnd - posStart);
	}
	
    /**
     * 运行API协议，提交请求到服务器，并获得响应包<br>
     * @param apiUrl	API协议文档指定的提交目标URL<br>
     * @param params	请求参数<br>
     * @return API执行成功，则返回true；失败，则返回false。<br>
     * 可通过getLastErrMsg()函数查询失败原因。<br>
     * getLastErrNum()函数可查到对应的错误码。但如果是是客户端错误导致异常中断，此函数总是返回0.
     */
    public boolean invokeApi(String apiUrl, ApiParameter params)
    	throws UnsupportedEncodingException, IOException
    {
    	lastErrNum = 0;

    	TreeMap<String, String[]> signParams = new TreeMap<String, String[]>();
    	
    	int pos = apiUrl.indexOf('?');
    	if(pos > 0) {
    		apiUrl = apiUrl.substring(0, pos);	// 不要带参数的URL
    	}
		apiUrl += "?" + ReservedName.CHARSET + "=" + charset;	// charset参数必须带在url里面以GET方法传递
		if(needSignCheck) {
			signParams.put(ReservedName.CHARSET.toString(), new String[]{charset});
		}
    	
    	HttpPost post = new HttpPost(apiUrl);
    	post.addHeader("User-Agent", "PaiPai API Invoker/Java " + System.getProperty("java.version"));
    	post.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);

	    Charset charsetObj = Charset.forName(charset);
	    // 加入用户参数
	    MultipartEntity entity = new MultipartEntity(HttpMultipartMode.STRICT, null, charsetObj);
	    for(String name : params.getNames()) {
	    	ArrayList<String> signValues = new ArrayList<String>();	// 需要做sign校验的值
	    	for(Object value : params.getParam(name)) {
	    		if (value instanceof String) {
	       			entity.addPart(name, new StringBody((String) value, charsetObj));
	       			if(needSignCheck) {
	       				signValues.add((String) value);
	        		}
				}
	    		else if (value instanceof File) {
	    			entity.addPart(name, new FileBody((File) value));
	    		}
	    		else {	// 不可能出现这种情况
	    			throw new IllegalArgumentException("发现不支持的参数类型。 name:" + name + " value.class:" + value.getClass().getName() + "。");
	    		}
	    	}
	    	if(needSignCheck && signValues.size() > 0) {
	    		signParams.put(name, signValues.toArray(new String[0]));
    		}
	    }
	    // 加入公用参数
	    for(String name : apiParams.keySet()) {
	    	String value = apiParams.get(name);
	    	entity.addPart(name, new StringBody(value, charsetObj));
   			if(needSignCheck) {
	    		signParams.put(name, new String[]{value});
    		}
	    }
	    if(secretKey!=null){
	    	String sign = makeSign(getCmd(post.getURI().getPath()), signParams, secretKey, charset, debug);
	    	entity.addPart(ReservedName.SIGN.toString(), new StringBody(sign, charsetObj));
	    }
	    post.setEntity(entity);
	    //post.addHeader(ReservedName.SIGN.toString(), makeSign(signParams, secretKey, charset, debug));
//        post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=GBK");
        //post.addHeader("Connection", "Keep-Alive");

        // 发送请求并接收XML响应包
        HttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        	lastErrMsg = "HTTP协议出错：" + response.getStatusLine() + "。";
        	return false;
        }

        // Get hold of the response entity
        HttpEntity responseEntity = response.getEntity();

        // If the response does not enclose an entity, there is no need
        // to worry about connection release
        if (responseEntity != null) {
        	if(responseEntity.getContentType()!=null)
        		lastResponseContentType = responseEntity.getContentType().getValue();
            InputStream stream = responseEntity.getContent();
            try {
            	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = stream.read(buffer)) != -1)
                {
                	byteStream.write(buffer, 0, len);
                }
                lastResponseContent = byteStream.toByteArray();
            }
            catch (RuntimeException e) {
                // In case of an unexpected exception you may want to abort
                // the HTTP request in order to shut down the underlying 
                // connection and release it back to the connection manager.
                post.abort();
                throw e;
                
            }
            finally {
                // Closing the input stream will trigger connection release
                stream.close();
            }
        }
        else {
        	lastResponseContentType = null;
        	Header header = response.getFirstHeader("Content-Type");
        	if(header != null) {
        		lastResponseContentType = header.getValue();
        	}
        	lastResponseContent = null;
        }

        return true;
    }
    
    private static void hexDump(byte[] array) {
    	StringBuilder output = new StringBuilder();
    	for(byte value : array) {
			if(value >= 0 && value < 16)
			{
				output.append("0");
			}
			output.append(Integer.toHexString(value & 0xFF)).append(' ');
    	}
    	System.out.println(output.toString());
    }
	
    private static String makeSign(String cmdId, TreeMap<String, String[]> signParams, String secretKey, String charset, boolean debug)
    	throws UnsupportedEncodingException, IOException
    {
    	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    	byteStream.write(cmdId.getBytes(charset));
		for (String key : signParams.keySet()) {
			byteStream.write(key.getBytes(charset));
			for (String value : signParams.get(key)) {
				byteStream.write(value.getBytes(charset));
			}
		}
		byteStream.write(secretKey.getBytes(charset));
		byte[] array = byteStream.toByteArray();
    	if(debug) {
    		System.out.println("Prepare content to encrypt:" + byteStream.toString(charset));
    		System.out.print("Hex dump:");
    		hexDump(array);
    	}

        return Md5Util.makeMd5Sum(array);
    }
}


