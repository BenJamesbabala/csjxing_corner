package com.doucome.corner.biz.dcome.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcSceneServiceImplTest extends AbstractBaseJUnit4Test {

	@Autowired
	DcSceneService dcSceneService ;
	
	@Test
	public void testCreate(){
		DcSceneDO scene = new DcSceneDO() ;
		scene.setActive(TrueFalseEnums.TRUE.getValue());
		scene.setBannerPicUrl("http://163.com") ;
		scene.setName("学习雷锋");
		scene.setSceneDesc("dewfewgetrtrtrhyrhj") ;
		scene.setSystemId(1L) ;
		Long id = dcSceneService.createScene(scene) ;
		System.out.println(id);
	}
	
	@Test
	public void batchCreate(){
		for(int i=0 ;i<40 ;i++){
			DcSceneDO scene = new DcSceneDO() ;
			scene.setActive(TrueFalseEnums.TRUE.getValue());
			scene.setBannerPicUrl("http://163.com") ;
			scene.setName("吾家有女初长成" + i);
			scene.setSceneDesc("dewfewgetrtrtrhyrhj") ;
			scene.setSystemId(1L) ;
			Long id = dcSceneService.createScene(scene) ;
			System.out.println(id);
		}
	}
}
