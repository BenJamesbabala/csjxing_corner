package com.doucome.corner.biz.dcome.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dcome.enums.DcCommentSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcCommentServiceImplTest extends AbstractBaseJUnit4Test{

	@Autowired
	private DcCommentService dcCommentService ;

	@Test
	public void testCreate(){
		for(int i=0;i<100;i++){
			DcCommentDO comment = new DcCommentDO() ;
			comment.setContent("ºÃ¿´") ;
			comment.setItemId(1L) ;
			comment.setSource(DcCommentSourceEnums.QZONE.getValue()) ;
			comment.setStatus(DcCommentStatusEnums.NORMAL.getValue()) ;
			comment.setUserId(1L) ;
			comment.setUserNick("ÀË±¼ÀËÂ¥") ;
			dcCommentService.createComment(comment) ;
		}
	}
}
