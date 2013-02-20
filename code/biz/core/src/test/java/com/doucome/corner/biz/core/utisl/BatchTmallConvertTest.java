package com.doucome.corner.biz.core.utisl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;


@ContextConfiguration(locations = { "classpath:biz-core-test.xml" })
public class BatchTmallConvertTest extends AbstractBaseJUnit4Test {
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String url = "http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MMNDsURNCIP%2BgGawSrYG79CTTBWHP4npPBK907ypPiWgMoVCx5KcP8CtWodgWQ8S5lAFynuuMpuBlvgO5mH43a2AuHQZDm3ujOKaiGLchmWcaJCk%2FmQAPZBL%2BPPC4pqNU00X3MnTEouwP8O%2FDdrH0cGUw%3D%3D&unid=Sddz&spm=2014.12560096.1.0" ;
		
		HttpClient client = new DefaultHttpClient() ;
		HttpGet get = new HttpGet(url) ;
		HttpResponse response = client.execute(get) ;
		HttpEntity e = response.getEntity() ;
		String s = toEntryString(e) ;
		System.out.println(s);
	}
	
	
	@Test
	public void test() throws Exception {
		String f = "d:/baihuo.txt" ;
		String f2 = "d:/baihuo_new.txt" ;
		BufferedReader br = new BufferedReader(new FileReader(new File(f))) ;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(f2))) ;
		String str = null ;
		while(true){
			str = br.readLine() ;
			if(str == null){
				break  ;
			}
			bw.newLine() ;
			String itemUrl = StringUtils.trim(str) ;
			
			String itemId = TaobaoUrlUtils.parseItemId(itemUrl) ;
			
			if(StringUtils.isBlank(itemId) || !StringUtils.isNumeric(itemId)){
				bw.write(itemUrl) ;
				continue ;
			}
			
			String outCode = OutCodeUtils.encodeOutCode("ddz", OutCodeEnums.SYSTEM) ;
			TaobaokeItemDTO  dto = taobaokeServiceDecorator.widgetConventItem(itemId, outCode, TaobaokeFields.taoke_item_fields) ;
			
			if(dto == null){
				bw.write(itemUrl) ;
			} else {
				bw.write(dto.getClickUrl()) ;
			}
			
		}
		
		bw.flush() ;
		bw.close() ;
	}
	
	private static String toEntryString (HttpEntity entry){
		if(entry == null){
			return null ;
		}
		try {
			return EntityUtils.toString(entry) ;
		} catch (ParseException e) {
			return null ;
		} catch (IOException e) {
			return null ;
		}
	}
	
	@Test
	public void test_xxx(){
		
	}
}
