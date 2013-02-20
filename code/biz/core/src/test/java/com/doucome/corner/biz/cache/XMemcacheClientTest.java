package com.doucome.corner.biz.cache;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-core-test.xml" 
	})
public class XMemcacheClientTest extends AbstractBaseJUnit4Test {

	@Autowired
	private CacheClient memcCacheClient ;
	
	@Test
	public void testPutAndGet(){
		boolean b = memcCacheClient.put("test", "222",1000) ;
		System.out.println("put : " + b);
		System.out.println(memcCacheClient.get("test"));
	}

	@Test
	public void testGetBulk(){
		memcCacheClient.put("1", "1") ;
		memcCacheClient.put("2", "2") ;
		memcCacheClient.put("3", "3") ;
		memcCacheClient.put("4", "4") ;
		memcCacheClient.put("5", "5") ;
		memcCacheClient.put("6", "6") ;
		System.out.println(memcCacheClient.get("2"));
		Map<String, Object> m = memcCacheClient.getBulk("1","2","3","4","5") ;
		
		System.out.println("");
		
	}
}
