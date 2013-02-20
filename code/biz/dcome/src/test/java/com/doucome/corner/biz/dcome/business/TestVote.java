package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

public class TestVote extends AbstractBaseJUnit4Test {

	public static void main(String[] args) throws Exception {
		String appid = "100634362" ;
		
		String code = "KOEX" ;
		
		String g_tk = "1215441470" ;
		
		String qzreferrer = "http://contest.open.qq.com/vote/pc#section4" ;
		
		int type = 1 ;
		
		String uin = "247676795" ;
		
		
		HttpPost post = new HttpPost("http://appstore.qzone.qq.com/cgi-bin/qzapps/qz_appstore_contest_vote.cgi?type=1&appid=" + appid + "&code=" + code) ;
		post.addHeader("Referer", "http://ctc.qzs.qq.com/qzone/v5/toolpages/fp_utf8.html") ;
		post.addHeader("Origin", "	http://diandianzhe.com") ;
		post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0") ;
		post.addHeader("Host", "appstore.qzone.qq.com") ;
		post.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8") ;
		
		//组装post参数
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("qzreferrer", qzreferrer));
		nameValuePairs.add(new BasicNameValuePair("type", String.valueOf(type)));
		nameValuePairs.add(new BasicNameValuePair("g_tk", g_tk));
		nameValuePairs.add(new BasicNameValuePair("uin", uin));
		nameValuePairs.add(new BasicNameValuePair("code", code));
					 
		
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
		HttpClient client = new DefaultHttpClient() ;
		HttpResponse response = client.execute(post) ;
		HttpEntity responseEntity = response.getEntity() ;
		String rsp = EntityUtils.toString(responseEntity) ;
		
		System.out.println(rsp);
		
	}
}
