package com.doucome.corner.biz.dcome.business;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.unittest.AbstractBaseJUnit4Test;


@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcImageUploadBOTest extends AbstractBaseJUnit4Test  {

	@Autowired
	private DcImageUploadBO dcImageUploadBO ;
	
	@Test
	public void testUploadUserAvatarFromUrl() {
		dcImageUploadBO.uploadUserAvatarFromUrl(0L, "http://img.guang.com/user/22/08/137/1370822_180x180.jpg") ;
	}
	
	@Test
	public void testUploadItemPicture(){
		//String s = dcImageUploadBO.uploadItemPicture(new File("D:/test.jpg"), "jpg") ;
	//	System.out.println(s);
	}
	
	@Test
	public void testUploadItemFromUrl(){
		
//		String url = "http://s5.img.guang.com/product/2012/05/10060/43/85/604385_2747681747443_310X310.jpg" ;
//		String s = dcImageUploadBO.uploadItemPictureFromUrl(url) ;
//		System.out.println(s);
	}
}
