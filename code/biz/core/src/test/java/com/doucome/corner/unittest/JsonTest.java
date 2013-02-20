package com.doucome.corner.unittest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.doucome.corner.biz.core.utils.JacksonHelper;

public class JsonTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String json = "{is_subsite:\"0\",local:\"6\",book:\"\",lastTweetId:\"18d60jw\",totalCnt:\"5928\",action:\"clothing\",fcid:\"1184\",f3cid:\"\",sort:\"pop\",page_tpl:\"/book/clothing/%E4%B8%8A%E8%A1%A3/{page}/pop/all/?color=&amp;fcid=1184&amp;childid=0&amp;childname=&amp;minPrice=&amp;maxPrice=&amp;fc=&amp;fc_v=&amp;f=&amp;q_natural=%E5%8D%AB%E8%A1%A3&amp;lady=0&amp;c_fcid=1184&amp;from_cate=clothing-1184&amp;section=0&amp;time_factor=18_0&amp;totalCnt=5928&amp;controller=book&amp;allpage=allpage\",userid:\"\",avatar:\"\",isBuyer:\"false\"}" ;
		Object obj = JacksonHelper.fromJSON(URLEncoder.encode(json,"UTF-8"), Object.class) ;
		System.out.println(obj);
	}
}
