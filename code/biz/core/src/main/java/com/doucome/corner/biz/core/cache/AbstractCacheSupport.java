package com.doucome.corner.biz.core.cache;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.doucome.corner.biz.cache.CacheClient;

/**
 * �������ʵ��
 * 
 * @author shenjia.caosj 2011-4-7
 * 
 */
public class AbstractCacheSupport implements InitializingBean {

	protected static final long ONE_YEAR_MILLISECONDS = 3600L * 1000 * 24 * 365; // 1��

	protected static final long ONE_MONTH_MILLISECONDS = 3600L * 1000 * 24 * 30; // 1����

	protected static final long ONE_HOUR_MILLISECONDS = 3600L * 1000; // 1Сʱ

	protected static final long ONE_DAY_MILLISECONDS = 3600L * 1000 * 24; // 1��
	
	protected static final long ONE_MINUTES_MILLISECONDS = 60L * 1000  ; //10����
	
	protected static final long ONE_SECOND_MILLISECONDS = 1000 ;

	/**
	 * Cache RegionName
	 */
	protected String regionName;

	/**
	 * Cache Client
	 */
	protected CacheClient cacheClient;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public CacheClient getCacheClient() {
		return cacheClient;
	}

	public void setCacheClient(CacheClient cacheClient) {
		this.cacheClient = cacheClient;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (cacheClient == null) {
			throw new IllegalStateException("cacheClient is null !");
		}
		if (StringUtils.isBlank(regionName)) {
			throw new IllegalStateException("regionName is Blank !");
		}
	}
	
	protected String buildCachekeyWithArgs(Object... args){
		String regionName = getRegionName() ;
		StringBuilder key = new StringBuilder(regionName) ;
		if(!ArrayUtils.isEmpty(args)) {
			for(Object a : args){
				key.append("_").append(a) ;
			}
		}
		return key.toString() ;
	}
	
	protected String buildCachekeyWithArgs(String... args){
		String regionName = getRegionName() ;
		StringBuilder key = new StringBuilder(regionName) ;
		if(!ArrayUtils.isEmpty(args)) {
			for(String a : args){
				key.append("_").append(a) ;
			}
		}
		return key.toString() ;
	}
	
	protected String buildCachekeyWithArgs(Long... args){
		String regionName = getRegionName() ;
		StringBuilder key = new StringBuilder(regionName) ;
		if(!ArrayUtils.isEmpty(args)) {
			for(Long a : args){
				key.append("_").append(a) ;
			}
		}
		return key.toString() ;
	}

	/**
	 * �������
	 * 
	 * @author shenjia.caosj 2011-4-12
	 * 
	 * @param <T>
	 */
	public static class InternalStoreItem<T> implements Serializable {
		
		public InternalStoreItem (T item){
			this.item = item ;
			this.gmtCreate = new Date() ;
		}
		
		private static final long serialVersionUID = 1L;
		private Date gmtCreate; // �����޸�ʱ��
		private T item; // ��������item
		private int version = 0 ;

		public Date getGmtCreate() {
			return gmtCreate;
		}

		public void setGmtCreate(Date gmtCreate) {
			this.gmtCreate = gmtCreate;
		}

		public T getItem() {
			return item;
		}

		public void setItem(T item) {
			this.item = item;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}
		
	}
}
