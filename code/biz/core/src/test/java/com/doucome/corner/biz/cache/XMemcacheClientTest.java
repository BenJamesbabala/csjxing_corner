package com.doucome.corner.biz.cache;

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
}
