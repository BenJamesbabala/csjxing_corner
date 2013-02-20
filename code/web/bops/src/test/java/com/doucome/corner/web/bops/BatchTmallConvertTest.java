package com.doucome.corner.web.bops;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utisl.TaobaoUrlUtils;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations = { "classpath:biz-core-test.xml" })
public class BatchTmallConvertTest extends AbstractBaseJUnit4Test {

	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Test
	public void test() throws FileNotFoundException, IOException{
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("d:/tmall.xls")));// 创建一个Excel文件
		for(int i = 1 ;i<5;i++){
			HSSFSheet sheet = workbook.getSheetAt(i) ;
			
			for(Iterator<Row> it = sheet.rowIterator() ;it.hasNext() ;){
				Row row = it.next() ;
				Cell cell = row.getCell(5) ;
				String itemUrl = StringUtils.trim(cell.getStringCellValue()) ;
				if(StringUtils.isNotBlank(itemUrl)){
					String itemId = TaobaoUrlUtils.parseItemId(itemUrl) ;
					
					if(StringUtils.isBlank(itemId) || !StringUtils.isNumeric(itemId)){
						continue ;
					}
					
					String outCode = OutCodeUtils.encodeOutCode("ddz", OutCodeEnums.SYSTEM) ;
					TaobaokeItemDTO  dto = taobaokeServiceDecorator.widgetConventItem(itemId, outCode, TaobaokeFields.taoke_item_fields) ;
					
					if(dto != null){
						String newUrl = "http://diandianzhe.com/zhe/item/" + itemId ;
						cell.setCellValue(newUrl) ;
						
						Hyperlink link = cell.getHyperlink() ;
						if(link != null){
							link.setAddress(newUrl) ;
						}
					}
					
				}
			}
			
		}
		//end
		
		workbook.write(new FileOutputStream(new File("d:/tmall_new.xls"))) ;

	}
}
