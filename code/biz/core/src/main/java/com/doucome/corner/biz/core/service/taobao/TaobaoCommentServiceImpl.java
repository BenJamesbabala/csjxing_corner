package com.doucome.corner.biz.core.service.taobao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.DisposableBean;

import com.doucome.corner.biz.core.taobao.dto.TaobaoCommentDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoCommentEnum;
import com.taobao.api.internal.util.StringUtils;

/**
 * 商品评论service
 * @author ze2200
 *
 */
public class TaobaoCommentServiceImpl implements TaobaoCommentService, DisposableBean {
	
	private HttpClient httpClient;
	
	private static final Log logger = LogFactory.getLog(TaobaoCommentServiceImpl.class);
	
	public TaobaoCommentServiceImpl() {
		ThreadSafeClientConnManager connManager = new ThreadSafeClientConnManager();
		connManager.setDefaultMaxPerRoute(20);
		connManager.setMaxTotal(200);
		httpClient = new DefaultHttpClient(connManager);
	}
	
	@Override
	public List<TaobaoCommentDTO> getItemComments(Long itemNativeId, Long sellerId,
			TaobaoCommentEnum commentEnum) {
		if (isInvalidId(itemNativeId) || isInvalidId(sellerId) || commentEnum == null) {
			return new ArrayList<TaobaoCommentDTO>();
		}
		Map<String, Object> params = commentEnum.getCommentRequestParams(itemNativeId, sellerId);
		String reqUrl = commentEnum.getItemCommentAjaxUrl(params);
		String commentJson = executeRequest(reqUrl);
		if (StringUtils.isEmpty(commentJson)) {
			return null;
		}
		commentJson = trimCommentJson(commentJson);
		List<TaobaoCommentDTO> comments = commentEnum.getItemComments(commentJson);
		if (comments == null) {
			//解析json失败，log日志.理论上在淘宝变更商品评论数据结构的时候会发生.
			logger.error(commentJson);
		}
		return comments;
	}
	
	private boolean isInvalidId(Long id) {
		return id == null || id < 0L;
	}
	
	/**
	 * 去掉淘宝评论无效的json数据
	 * @param jsonStr
	 * @return
	 */
	private String trimCommentJson(String jsonStr) {
		String result = jsonStr;
		int index = result.indexOf("{");
		if (index > 0) {
			result = result.substring(index);
		}
		index = result.lastIndexOf("}");
		if (index != -1 && index != result.length()) {
			result = result.substring(0, index + 1);
		}
		return result;
	}
	
	/**
	 * 执行url请求.
	 * @param url 
	 * @return
	 */
	private String executeRequest(String url) {
		HttpEntity entity = null;
		try {
			System.out.println(url);
			HttpGet getReq = new HttpGet(url);
			HttpResponse response = httpClient.execute(getReq);
			if (response.getStatusLine().getStatusCode() != 200) {
				logger.error("request " + url + " failed, statusCode: " + response.getStatusLine().getStatusCode());
				return null;
			}
			entity = response.getEntity();
			InputStream inputStream = new BufferedInputStream(entity.getContent());
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("GBK")), 4096);
			String temp = null;
			StringBuilder tempBuilder = new StringBuilder();
			while((temp = reader.readLine()) != null) {
				tempBuilder.append(temp);
			}
			return tempBuilder.toString();
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				EntityUtils.consume(entity);
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
	@Override
	public void destroy() throws Exception {
		try {
			httpClient.getConnectionManager().shutdown();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	public static void main(String[] args) {
		TaobaoCommentServiceImpl commentService = new TaobaoCommentServiceImpl();
		List<TaobaoCommentDTO> comments = commentService.getItemComments(17084544461L, 875930325L,
				TaobaoCommentEnum.TMALL);
		System.out.println(comments);
//		comments = commentService.getItemComments(16187543328L, 659744051L, DcItemCommentEnum.TAOBAO);
//		System.out.println(comments);
	}
}