package com.doucome.corner.biz.core.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.doucome.corner.biz.cache.CacheClient;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 缓存基类实现
 * 
 * @author langben 2011-4-7
 * 
 */
public class AbstractCacheSupport implements InitializingBean {

	protected static final long ONE_YEAR_MILLISECONDS = 3600L * 1000l * 24l * 365l; // 1年

	protected static final long ONE_MONTH_MILLISECONDS = 3600L * 1000l * 24l * 30l; // 1个月

	protected static final long ONE_HOUR_MILLISECONDS = 3600L * 1000l; // 1小时

	protected static final long ONE_DAY_MILLISECONDS = 3600L * 1000l * 24l; // 1天
	
	protected static final long ONE_MINUTES_MILLISECONDS = 60L * 1000L  ; //1分钟
	
	protected static final long TEN_MINUTES_MILLISECONDS = 600L * 1000L  ; //1分钟
	
	protected static final long TEN_SECOND_MILLISECONDS = 1000 * 10L ;
	
	protected static final long ONE_SECOND_MILLISECONDS = 1000L ;
	
	private static List<AbstractCacheSupport> manageCacheList = new ArrayList<AbstractCacheSupport>() ;
	
	public static List<AbstractCacheSupport> getMgrCacheClientList(){
		return manageCacheList ;
	}
	

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
		
		manageCacheList.add(this) ;
	}
	
	protected String buildCachekeyWithArgs(Object... args){
		String regionName = getRegionName() ;
		StringBuilder key = new StringBuilder(regionName) ;
		if(!ArrayUtils.isEmpty(args)) {
			for(Object a : args){
				if(a != null){
					key.append("_").append(a) ;
				}
			}
		}
		return key.toString() ;
	}
		
	public final boolean removeCacheByKey(String keyName){
		CacheClient cc = getCacheClient() ;
		return cc.delete(keyName) ;
	}
	
	public final Object getCacheByKey(String keyName){
		CacheClient cc = getCacheClient() ;
		return cc.get(keyName) ;
	}

	/**
	 * 缓存对象
	 * 
	 * @author langben 2011-4-12
	 * 
	 * @param <T>
	 */
	public static class InternalStoreItem<T> extends AbstractModel implements Serializable {
		
		public InternalStoreItem (T item){
			this.item = item ;
			this.gmtCreate = new Date() ;
		}
		
		private static final long serialVersionUID = 1L;
		private Date gmtCreate; // 缓存修改时间
		private T item; // 缓存数据item
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
