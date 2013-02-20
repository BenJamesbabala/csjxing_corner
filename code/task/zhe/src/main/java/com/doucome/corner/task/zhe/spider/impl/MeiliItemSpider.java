package com.doucome.corner.task.zhe.spider.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.dal.dataobject.SpiderConfigDO;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;
import com.doucome.corner.task.zhe.spider.AbstractItemSpider;
import com.doucome.corner.task.zhe.spider.HtmlClient;

public class MeiliItemSpider extends AbstractItemSpider {
	private static final Log logger = LogFactory.getLog(MeiliItemSpider.class);
	private static final String AJAX_URL = "http://www.meilishuo.com/aj/getGoods/catalog";
	private static final String ITEM_BASE_URL = "http://www.meilishuo.com/share/";
	private static Pattern jsonPattern;
	private static Pattern itemPattern;
	private static Pattern picPattern;
	private static Pattern pricePattern;
	static {
		try {
			String jsonRegex = "(?<=\"twitter_id\":\")\\d+?(?=\",\")";
			jsonPattern = Pattern.compile(jsonRegex);
			String itemRegex = "(?<=id%3D)\\d+?(?=(%26)?'\\);</script>)";
			itemPattern = Pattern.compile(itemRegex);
			String picRegex = "(?<=<img class=\"twitter_pic\" src=\")(.*?)(?=\">)";
			picPattern = Pattern.compile(picRegex);
			String priceRegex = "(?<=<a class=\"price_go\" href=\")(.*?)(?=\" target=\"_blank\"><span class=\"price\">\\p{Sc}(\\d+)?</span>)";
			pricePattern = Pattern.compile(priceRegex);
		} catch (Exception e) {
			logger.error("init collect pattern error", e);
		}
	}

	public static void main(String[] args) {
		String url = "http://www.meilishuo.com/guang/catalog/shoes?cata_id=6000000000000&frame=0&word=36323&price=all&page=0&section=hot";
		// int wenhao = url.indexOf("?");
		// String paramString = url.substring(wenhao+1);
		// System.out.println(paramString);
		// String[] params = paramString.split("&");
		// Map<String, String> map = new HashMap<String, String>();
		// for (int i = 0; i < params.length; i++) {
		// String[] keyValue = params[i].split("=");
		// if(keyValue.length==2){
		// map.put(keyValue[0], keyValue[1]);
		// }
		// }
		MeiliItemSpider a = new MeiliItemSpider();
		SpiderConfigDO config = new SpiderConfigDO();
		config.setSite("meilishuo");
		config.setUrl(url);
		a.spider(config);
	}

	@Override
	public int spider(SpiderConfigDO config) {
		if (config == null || !StringUtils.endsWithIgnoreCase(config.getSite(), "meilishuo")
				|| StringUtils.isBlank(config.getUrl())) {
			return 0;
		}
		String url = config.getUrl();

		int wenhao = url.indexOf("?");
		String paramString = url.substring(wenhao + 1);
		String[] params = paramString.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < params.length; i++) {
			String[] keyValue = params[i].split("=");
			if (keyValue.length == 2) {
				map.put(keyValue[0], keyValue[1]);
			}
		}
		map.put("frame", "1");
		map.put("view", "1");
		int count = 0;

		if (StringUtils.isNotBlank(map.get("cata_id")) && StringUtils.isNotBlank(map.get("word"))) {
			String category = config.getCategory();
			int page = 0;
			String meiliId = null;
			do {
				map.put("page", String.valueOf(page));
				String json = HtmlClient.wgetPage(AJAX_URL, map);
				if (StringUtils.isBlank(json)) {
					spiderItemLog.info("meili json is blank, word:" + map.get("word"));
					break;
				}
				Matcher jsonMatcher = jsonPattern.matcher(json);

				while (jsonMatcher.find()) {
					try {
						SpiderItemDO spiderItemDO = new SpiderItemDO();
						meiliId = jsonMatcher.group();
						String meiliHtml = HtmlClient.wgetPage(ITEM_BASE_URL + meiliId);

						Long itemId = null;
						String picUrl = null;
						String meiliUrl = null;

						Matcher priceMatcher = pricePattern.matcher(meiliHtml);
						BigDecimal price = null;
						while (priceMatcher.find()) {
							meiliUrl = priceMatcher.group(0);
							String priceStr = priceMatcher.group(2);
							price = new BigDecimal(priceStr);
						}

						if (StringUtils.isBlank(meiliUrl)) {
							continue;
						}
						String jsHtml = HtmlClient.wgetPage(meiliUrl);
						Matcher itemMatcher = itemPattern.matcher(jsHtml);
						while (itemMatcher.find()) {
							String itemIdStr = itemMatcher.group(0);
							itemId = Long.valueOf(itemIdStr);
						}

						if (itemId == null) {
							continue;
						}

						Matcher picMatcher = picPattern.matcher(meiliHtml);
						while (picMatcher.find()) {
							picUrl = picMatcher.group();
						}

						spiderItemDO.setExtId(meiliId);
						spiderItemDO.setSpiderFrom(config.getSite());
						spiderItemDO.setItemId(itemId);
						spiderItemDO.setPicUrl(picUrl);
						spiderItemDO.setPrice(price);
						spiderItemDO.setCategory(category);

						if (insertSpiderItemDO(spiderItemDO)) {
							count++;
						}
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						logger.error("spider item error,moguId: " + meiliId, e);
					}
				}
				page++;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (page < 10);
			spiderItemLog.info("spider meili success, work: " + map.get("word") + "; count: " + count);
		}
		return count;
	}

}
