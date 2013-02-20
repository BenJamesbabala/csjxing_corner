package com.doucome.corner.biz.dcome.service;

import java.awt.Dimension;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.util.ImageUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.DcHttpUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;


@ContextConfiguration(locations={
		"classpath:biz-dcome-test.xml" 
	})
public class DcItemServiceImplTest extends AbstractBaseJUnit4Test {

	@Autowired
	DcItemService dcItemService ;
	
	@Test
	public void testFillPicHeightProps() throws Exception {
		DcItemSearchCondition condition = new DcItemSearchCondition() ;
		List<DcItemDTO> itemList = dcItemService.getItemsNoPagination(condition, new Pagination(1 , 100000)) ;
		
		for(int m = 0 ; m < itemList.size() ; m ++ ){
			DcItemDTO itemDTO = itemList.get(m) ;
			String picUrls = itemDTO.getPicUrls() ;
			//List<String> picUrlList = itemDTO.getPicUrlList() ;
			if(!JSONUtils.mayBeJSON(picUrls) && StringUtils.isNotBlank(picUrls)){
				List<String> picUrlList = ArrayStringUtils.toList(picUrls) ;
				List<DcItemPicModel> picModelList = new ArrayList<DcItemPicModel>() ;
				for(int i = 0 ;i< picUrlList.size() ;i ++){
					
					String picUrl = itemDTO.getPicUrl(i, PictureSizeEnums.NONE.getName()) ;
					DcItemPicModel model = new DcItemPicModel() ;
					model.setUrl(picUrlList.get(i)) ;
					try {
						InputStream is = DcHttpUtils.getInputStream(picUrl) ;
						Dimension dimension = ImageUtils.getImageDimension(is, org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_JPEG) ;
						model.setHeightProp(DcItemUtils.getItemPicHeightProp(dimension));
					} catch (Exception e){
						model.setHeightProp(0) ;
					}
					picModelList.add(model) ;
				}
			
				dcItemService.updatePicUrlsById(itemDTO.getId(), picModelList);//(itemDTO.getId(), itemDTO.getPicUrls(), ArrayStringUtils.toString(picHeightPropList)) ;
			}
			
			
		}
		
		int i = 5;
	}
	
	@Test
	public void testCreate() throws DcDuplicateKeyException{
		for(int i=0 ;i<30 ;i++){
			DcItemDO item = new DcItemDO() ;
			item.setCategoryId(4L) ;
			item.setClickUrl("http://baidu.com") ;
			item.setComments(8) ;
			item.setItemDesc("描述") ;
			item.setItemPrice(new BigDecimal("20.90")) ;
			item.setItemTitle("草家*美白保湿玫瑰花水 保湿补水美白抗皱爽肤水/化妆水 120ml" + i) ;
			item.setLoves(10) ;
			item.setPicUrls("http://img03.taobaocdn.com/bao/uploaded/i3/T1Rm1AXglQXXbm4aQ0_034307.jpg") ;
			item.setSells(20) ;
			item.setSource(DcItemSourceEnum.TAOBAO.getValue()) ;
			item.setSrcUrl("http://item.taobao.com/item.htm?id=14831179443&ali_trackid=2:mm_30421514_0_0:1343444535_4z5_549628964&spm=2014.12526345.1.0") ;
			Long id = dcItemService.createItem(item);
			System.out.println(id);
		}
	}
	
	@Test
	public void testResetCategory() {
		DcItemSearchCondition condition = new DcItemSearchCondition();
		condition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
		condition.setCategoryId(0l);
		Map<Long, List<Long>> ids = new HashMap<Long, List<Long>>();
		int page = 1, pageSize = 1000;
		List<DcItemDTO> tempItems = null;
		QueryResult<DcItemDTO> items = null;
		do {
			items = dcItemService.getItemsWithPagination(condition, new Pagination(page, pageSize));
			tempItems = items.getItems();
			ids.put(1l, null);
			ids.put(2l, null);
			ids.put(3l, null);
			ids.put(4l, null);
			ids.put(5l, null);
			ids.put(6l, null);
			for (DcItemDTO item: tempItems) {
				Long categoryId = item.getPossibleCategoryId();
				List<Long> itemIds = ids.get(categoryId);
				if (itemIds == null) {
					itemIds = new ArrayList<Long>();
				}
				itemIds.add(item.getId());
				ids.put(categoryId, itemIds);
			}
			for (Map.Entry<Long, List<Long>> entry: ids.entrySet()) {
				dcItemService.batchUpdateCategoryId(entry.getValue(), entry.getKey());
			}
			ids.clear();
			page++;
		} while (items.getItems().size() == 1000);
	}
}
