package com.doucome.corner.biz.core.service.taobao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.doucome.corner.biz.core.exception.EncryptException;
import com.doucome.corner.biz.core.taobao.constant.TaobaoConstant;
import com.doucome.corner.biz.core.taobao.parser.WidgetObjectJsonParser;
import com.doucome.corner.biz.core.utils.TaobaoWidgetUtils;
import com.taobao.api.ApiException;
import com.taobao.api.ApiRuleException;
import com.taobao.api.Constants;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoParser;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.internal.parser.xml.ObjectXmlParser;
import com.taobao.api.internal.util.RequestParametersHolder;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.taobao.api.internal.util.TaobaoLogger;
import com.taobao.api.internal.util.WebUtils;

/**
 * 基于REST的TOP客户端。
 * 
 * @author carver.gu
 * @since 1.0, Sep 13, 2009
 */
public class DefaultTaobaoWidgetClient implements TaobaoClient {

	private static final String APP_KEY = "app_key";
	private static final String FORMAT = "format";
	private static final String METHOD = "method";
	private static final String TIMESTAMP = "timestamp";
	private static final String VERSION = "v";
	private static final String SIGN = "sign";
	private static final String SIGN_METHOD = "sign_method";
	private static final String PARTNER_ID = "partner_id";
	private static final String SESSION = "session";

	private String nick ;
	private String serverUrl;
	private String appKey;
	private String appSecret;
	private String format = Constants.FORMAT_JSON;
	private String signMethod = Constants.SIGN_METHOD_HMAC;

	private int connectTimeout = 3000;//3秒
	private int readTimeout = 15000;//15秒
	private boolean needCheckRequest = true;
	private boolean needEnableParser = true;

	public DefaultTaobaoWidgetClient(String serverUrl, String appKey, String appSecret , String nick) {
		this.appKey = appKey;
		this.appSecret = appSecret;
		this.serverUrl = serverUrl;
		this.nick = nick ;
	}

	public <T extends TaobaoResponse> T execute(TaobaoRequest<T> request) throws ApiException {
		return execute(request, null);
	}

	public <T extends TaobaoResponse> T execute(TaobaoRequest<T> request, String session) throws ApiException {
		TaobaoParser<T> parser = null;
		if (this.needEnableParser) {
			if (Constants.FORMAT_XML.equals(this.format)) {
				parser = new ObjectXmlParser<T>(request.getResponseClass());
			} else {
				parser = new WidgetObjectJsonParser<T>(request.getResponseClass());
			}
		}
		return _execute(request, parser, session);
	}

	private <T extends TaobaoResponse> T _execute(TaobaoRequest<T> request, TaobaoParser<T> parser, String session) throws ApiException {
		if (this.needCheckRequest) {
			try {
				request.check();// if check failed,will throw ApiRuleException.
			} catch (ApiRuleException e) {
				T localResponse = null;
				try {
					localResponse = request.getResponseClass().newInstance();
				} catch (InstantiationException e2) {
					throw new ApiException(e2);
				} catch (IllegalAccessException e3) {
					throw new ApiException(e3);
				}
				localResponse.setErrorCode(e.getErrCode());
				localResponse.setMsg(e.getErrMsg());
				return localResponse;
			}
		}

		Map<String, Object> rt = doPost(request, session);
		if (rt == null)
			return null;

		T tRsp = null;
		if (this.needEnableParser) {
			try {
				tRsp = parser.parse((String) rt.get("rsp"));
				tRsp.setBody((String) rt.get("rsp"));
			} catch (RuntimeException e) {
				TaobaoLogger.logBizError((String) rt.get("rsp"));
				throw e;
			}
		} else {
			try {
				tRsp = request.getResponseClass().newInstance();
				tRsp.setBody((String) rt.get("rsp"));
			} catch (Exception e) {
			}
		}

		tRsp.setParams((TaobaoHashMap) rt.get("textParams"));
		if (!tRsp.isSuccess()) {
			TaobaoLogger.logErrorScene(rt, tRsp, appSecret);
		}
		return tRsp;
	}

	public <T extends TaobaoResponse> Map<String, Object> doPost(TaobaoRequest<T> request, String session) throws ApiException {
		Map<String, Object> result = new HashMap<String, Object>();
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		TaobaoHashMap appParams = new TaobaoHashMap(request.getTextParams());
		requestHolder.setApplicationParams(appParams);

		// 添加协议级请求参数
		TaobaoHashMap protocalMustParams = new TaobaoHashMap();
		protocalMustParams.put(METHOD, request.getApiMethodName());
		protocalMustParams.put(VERSION, "2.0");
		protocalMustParams.put(APP_KEY, appKey);
		Long timestamp = request.getTimestamp();// 允许用户设置时间戳
		if (timestamp == null) {
			timestamp = System.currentTimeMillis();
		}
		String timeStamp = TaobaoWidgetUtils.timestamp(timestamp) ;
		protocalMustParams.put(TIMESTAMP, timeStamp);// 因为沙箱目前只支持时间字符串，所以暂时用Date格式
		requestHolder.setProtocalMustParams(protocalMustParams);

		TaobaoHashMap protocalOptParams = new TaobaoHashMap();
		protocalOptParams.put(FORMAT, format);
		//protocalOptParams.put(SIGN_METHOD, signMethod);
		//protocalOptParams.put(SESSION, session);
		protocalOptParams.put(PARTNER_ID, TaobaoConstant.WIDGET_SDK_VERSION);
		requestHolder.setProtocalOptParams(protocalOptParams);

		// 添加签名参数
		try {
//			if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
//				protocalMustParams.put(SIGN, TaobaoUtils.signTopRequestNew(requestHolder, appSecret, false));
//			} else if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
//				protocalMustParams.put(SIGN, TaobaoUtils.signTopRequestNew(requestHolder, appSecret, true));
//			} else {
//				protocalMustParams.put(SIGN, TaobaoUtils.signTopRequest(requestHolder, appSecret));
//			}
			protocalMustParams.put(SIGN, TaobaoWidgetUtils.sign(appSecret, appKey, timeStamp));
		} catch (EncryptException e) {
			throw new ApiException(e);
		}

		StringBuffer urlSb = new StringBuffer(serverUrl);
		try {
			String sysMustQuery = WebUtils.buildQuery(requestHolder.getProtocalMustParams(), Constants.CHARSET_UTF8);
			String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), Constants.CHARSET_UTF8);

			urlSb.append("?");
			urlSb.append(sysMustQuery);
			if (sysOptQuery != null & sysOptQuery.length() > 0) {
				urlSb.append("&");
				urlSb.append(sysOptQuery);
			}
		} catch (IOException e) {
			throw new ApiException(e);
		}

		String rsp = null;
//		try {
//			// 是否需要上传文件
//			if (request instanceof TaobaoUploadRequest) {
//				TaobaoUploadRequest<T> uRequest = (TaobaoUploadRequest<T>) request;
//				Map<String, FileItem> fileParams = TaobaoUtils.cleanupMap(uRequest.getFileParams());
//				rsp = WebUtils.doPost(urlSb.toString(), appParams, fileParams, connectTimeout, readTimeout);
//			} else {
//				rsp = WebUtils.doPost(urlSb.toString(), appParams, connectTimeout, readTimeout);
//			}
//		} catch (IOException e) {
//			throw new ApiException(e);
//		}
		
		
		try {
			HttpPost post = new HttpPost(urlSb.toString()) ;
			post.addHeader("Referer", "http://diandianzhe.com/zhe/index.htm") ;
			post.addHeader("Origin", "	http://diandianzhe.com") ;
			post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0") ;
			post.addHeader("Host", "gw.api.taobao.com") ;
			post.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8") ;
			
			//组装post参数
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			Set<Entry<String, String>> entries = appParams.entrySet();
			
			for(Entry<String, String> paramEntry : entries){
				nameValuePairs.add(new BasicNameValuePair(paramEntry.getKey()	, paramEntry.getValue()));
			}			 
			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
			HttpClient client = new DefaultHttpClient() ;
			HttpResponse response = client.execute(post) ;
			HttpEntity responseEntity = response.getEntity() ;
			rsp = EntityUtils.toString(responseEntity) ;
			
		} catch (ClientProtocolException e) {
			throw new ApiException(e);
		} catch (IOException e) {
			throw new ApiException(e);
		}
		
		result.put("rsp", rsp);
		result.put("textParams", appParams);
		result.put("protocalMustParams", protocalMustParams);
		result.put("protocalOptParams", protocalOptParams);
		result.put("url", urlSb.toString());
		return result;
	}

	public void setNeedCheckRequest(boolean needCheckRequest) {
		this.needCheckRequest = needCheckRequest;
	}

	public void setNeedEnableParser(boolean needEnableParser) {
		this.needEnableParser = needEnableParser;
	}

	public void setNeedEnableLogger(boolean needEnableLogger) {
		TaobaoLogger.setNeedEnableLogger(needEnableLogger);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}
