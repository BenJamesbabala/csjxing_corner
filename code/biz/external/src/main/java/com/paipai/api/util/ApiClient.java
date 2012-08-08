/**
 * Copyright (C) 1998-2009 TENCENT Inc.All Rights Reserved.		
 * 																	
 * FileName��ApiClient.java					
 *			
 * Description��API�ͻ��ˣ���ʹ�ø����ͷ���ز���APIЭ��
 * History��
 *  2.0  2010-02-26        �޸�APIЭ��ĵ��÷�ʽ�Լ�signУ�鷽ʽ��������1.0�Ľӿڡ�
 *  1.2  2009-05-27        ����getLastResponseBody����
 *  1.2  2009-05-25        ������invoke������һ��bug����bug���¶�ȡ�����е�responseʱ��У��signʧ��
 *  1.1  2009-04-02        ����debug���أ��Ա�ر�Debug��Ϣ
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
 * API�ͻ��ˣ����ڲ�������APIЭ�顣<br>
 * ApiClient�������̰߳�ȫ��֤�����̱߳�̽��鲻Ҫʹ��ͬһ��ApiClient����
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
    
    private boolean debug = false;	// �����Ƿ����Debug��Ϣ
    
    long lastSeqNum = 0;				// ���һ���������seqno
    byte[] lastResponseContent = null;	// ���һ���ɹ����ص���Ӧ������
    String lastResponseContentType;
    
    long lastResponseVersion = 0;		// ���һ���ɹ����ص���Ӧ���汾
    
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
	 * �����Ƿ���ҪApiClient���debug��Ϣ
	 * @param isDebug true�����debug��Ϣ��false�����debug��Ϣ
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
     * ȡ��ǰһ�γɹ���API���÷��ص���Ӧ��������
     * @return ǰһ�γɹ���API���÷��ص���Ӧ��������
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
     * ��ʱ�����ת���������ƶ����ַ�����ʽ
     * @param date ����ʱ��
     * @return ����ʱ���ַ�����ʽ
     */
    public static String getTimeString(Date date)
    {
    	return timeFormat.format(date);
    }



    /**
     * ����md5У����
     * @param strContent ���͵�API��������head��body����
     * @return ����md5У����
     */
//    private String getSign(String strContent)
//    {
//        return Md5Util.makeMd5Sum((strContent + spSecretKey).getBytes());
//    }

	private static String getCmd(String requestURI) {
		byte[] uri = requestURI.getBytes();
		int posStart = 0;
		while(uri[posStart] == '/') {	// ȥ����ͨ��'/'
			posStart ++;
		}
		int posEnd = posStart;
		int index = posStart;
		while(index < uri.length) {	// �滻�м��'/'
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
     * ����APIЭ�飬�ύ���󵽷��������������Ӧ��<br>
     * @param apiUrl	APIЭ���ĵ�ָ�����ύĿ��URL<br>
     * @param params	�������<br>
     * @return APIִ�гɹ����򷵻�true��ʧ�ܣ��򷵻�false��<br>
     * ��ͨ��getLastErrMsg()������ѯʧ��ԭ��<br>
     * getLastErrNum()�����ɲ鵽��Ӧ�Ĵ����롣��������ǿͻ��˴������쳣�жϣ��˺������Ƿ���0.
     */
    public boolean invokeApi(String apiUrl, ApiParameter params)
    	throws UnsupportedEncodingException, IOException
    {
    	lastErrNum = 0;

    	TreeMap<String, String[]> signParams = new TreeMap<String, String[]>();
    	
    	int pos = apiUrl.indexOf('?');
    	if(pos > 0) {
    		apiUrl = apiUrl.substring(0, pos);	// ��Ҫ��������URL
    	}
		apiUrl += "?" + ReservedName.CHARSET + "=" + charset;	// charset�����������url������GET��������
		if(needSignCheck) {
			signParams.put(ReservedName.CHARSET.toString(), new String[]{charset});
		}
    	
    	HttpPost post = new HttpPost(apiUrl);
    	post.addHeader("User-Agent", "PaiPai API Invoker/Java " + System.getProperty("java.version"));
    	post.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);

	    Charset charsetObj = Charset.forName(charset);
	    // �����û�����
	    MultipartEntity entity = new MultipartEntity(HttpMultipartMode.STRICT, null, charsetObj);
	    for(String name : params.getNames()) {
	    	ArrayList<String> signValues = new ArrayList<String>();	// ��Ҫ��signУ���ֵ
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
	    		else {	// �����ܳ����������
	    			throw new IllegalArgumentException("���ֲ�֧�ֵĲ������͡� name:" + name + " value.class:" + value.getClass().getName() + "��");
	    		}
	    	}
	    	if(needSignCheck && signValues.size() > 0) {
	    		signParams.put(name, signValues.toArray(new String[0]));
    		}
	    }
	    // ���빫�ò���
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

        // �������󲢽���XML��Ӧ��
        HttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        	lastErrMsg = "HTTPЭ�����" + response.getStatusLine() + "��";
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


