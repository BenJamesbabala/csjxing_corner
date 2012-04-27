package com.doucome.corner.biz.core.service.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.doucome.corner.biz.core.constant.Constant;
import com.doucome.corner.biz.core.service.UriService;

public class DefaultUriService implements UriService, InitializingBean {

	private Map<String, String> uriMappings = new HashMap<String, String>();

	private Map<String, UriModel>  uriMappingProvider = new HashMap<String, UriModel>();

	private static UriService thisHolder = null;

	@Override
	public String getURI(String uriName) {
		UriModel uri = uriMappingProvider.get(uriName);
		if (uri == null) {
			throw new IllegalStateException("there is no uri named " + uriName);
		}
		return uri.getDefUri() ;
	}

	/**
	 * 
	 * @param uriName
	 * @return
	 */
	public static String getFactoryURI(String uriName) {
		if (thisHolder == null) {
			throw new IllegalStateException("UriService is not init");
		}

		return thisHolder.getURI(uriName);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		for(Map.Entry<String,String> uriEntry : uriMappings.entrySet()){
			if(StringUtils.isNotBlank(uriEntry.getValue())){
				uriMappingProvider.put(uriEntry.getKey(), new UriModel(uriEntry.getValue())) ;
			}
		}
		
		thisHolder = this;
	}

	public void setUriMappings(Map<String, String> uriMappings) {
		this.uriMappings = uriMappings;
	}

	public static void main(String[] args) throws URISyntaxException,
			MalformedURLException {
		
		UriModel u = new UriModel("HTTPS://taobao.com:80/inde.xhdfewf/#fdfef") ;
		
		System.out.println(u.getDefUri());
	}

	public static class UriModel {
		
		public UriModel(String uriString) throws URISyntaxException {
			uri = new URI(uriString) ;
			oriUri = uriString ;
			if (StringUtils.startsWithIgnoreCase(Constant.HTTP, uri.getScheme()) && uri.getPort() == Constant.DEFAULT_HTTP_PORT) {
				URI newUri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
				defUri = newUri.toString() ;
			}else{
				defUri = uri.toString() ;
			}
			
			
		}

		/**
		 * 原始URI
		 */
		private String oriUri;

		/**
		 * 解析后的URI
		 */
		private URI uri;

		/**
		 * 实际使用的URI
		 */
		private String defUri;

		public String getOriUri() {
			return oriUri;
		}

		public void setOriUri(String oriUri) {
			this.oriUri = oriUri;
		}

		public URI getUri() {
			return uri;
		}

		public void setUri(URI uri) {
			this.uri = uri;
		}

		public String getDefUri() {
			return defUri;
		}

		public void setDefUri(String defUri) {
			this.defUri = defUri;
		}
		
		@Override
		public String toString() {
			return defUri ;
		}
	}
}
