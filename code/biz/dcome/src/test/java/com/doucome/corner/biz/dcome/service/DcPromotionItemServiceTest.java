package com.doucome.corner.biz.dcome.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcPromotionItemServiceTest extends AbstractBaseJUnit4Test {

	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcPromotionItemService dcPromotionItemService ;
	
	@Autowired
	private DcPromotionService dcPromotionService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Test
	public void testInsert(){
		Integer[] userIds = {
				12318 , 12319 ,12320 ,12321,12322,12323,12324,12325,12326,12327,12328,12329,
				12330,12331,12332,12333,12334,12335,12336,12337,12338,12339,12340,12341,12342,
				//12343,12344,12345,12346,12347,12348
		} ;
		
		
		int end = 746 ;
		
		DcPromotionDTO promotion = dcPromotionService.getCurPromotion() ;
		
		if(promotion == null){
			return ;
		}
		List<Long> idList = new ArrayList<Long>() ;
		
		for(int i = 0 ;i<100 ;i++){
			int mmm = RandomUtils.nextInt(end) ;
			Long itemId = 100000L + mmm;
			idList.add(itemId) ;
		}
		
		List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList) ;
		
		for(Iterator<DcItemDTO> i = itemList.iterator() ;i.hasNext() ;){
			DcItemDTO dto = i.next() ;
			if(dto == null){
				i.remove() ;
				continue ;
			}
			if(!StringUtils.equals(dto.getStatus() , DcItemStatusEnum.NORMAL.getValue())){
				i.remove() ;
				continue ;
			}
		}
		
		
		for(int i = 0 ;i<userIds.length ;i++){
			Long userId = Long.valueOf(userIds[i]) ;
			DcUserDTO user = dcUserService.getUser(userId) ;
			if(user == null){
				continue ;
			}
			
			if(i >= itemList.size() ){
				break ;
			}
			
			DcItemDTO item = itemList.get(i) ;
			
			DcPromotionItemDO dpi = new DcPromotionItemDO() ;
			dpi.setItemId(item.getId()) ;
			dpi.setPromotionId(promotion.getId()) ;
			dpi.setRateCount(0) ;
			dpi.setUserId(userId) ;
			dpi.setUserNick(user.getNick()) ;
			try {
				dcPromotionItemService.createPromotionItem(dpi) ;
			} catch (DcDuplicateKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
