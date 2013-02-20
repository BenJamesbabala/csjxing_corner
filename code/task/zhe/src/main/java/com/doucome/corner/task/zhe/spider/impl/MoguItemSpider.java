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

public class MoguItemSpider extends AbstractItemSpider {
	private static final Log logger = LogFactory.getLog(MoguItemSpider.class);
	private static final String AJAX_URL = "http://www.mogujie.com/book/ajax";
	private static final String ITEM_BASE_URL = "http://www.mogujie.com/note/";
	private static Pattern profilePattern;
	private static Pattern jsonPattern;
	private static Pattern itemPattern;
	private static Pattern pricePattern;
	static {
		try {
			String profileRegex = "(?<=MOGUPROFILE = ).*(?=};)";
			profilePattern = Pattern.compile(profileRegex);
			String jsonRegex = "(?<= tid=\\\\\")[a-z0-9]{7}(?=\\\\\" class=\\\\\"favaImg\\\\\")";
			jsonPattern = Pattern.compile(jsonRegex);
			String itemRegex = "(?<=<a rel=\"nofollow\" target=\"_blank\" href=\"http://mogujie.cn/tgo/\\?to=http%3A%2F%2Fitem.taobao.com%2Fitem.htm%3Fid%3D)\\d+?(?=&id=(.*)\" class=\"show_big qplus_link\">\\s+<img alt=\"\" title=\"\" src=\"(.*?)\">)";
			itemPattern = Pattern.compile(itemRegex);
			String priceRegex = "(?<=class=\"buy_link fl\" rel=\"nofollow\"><b( class=\"c_t\")?>.?)(\\d+\\.\\d+)?(?=\\s*È¥ÌÔ±¦¹ºÂò</b><i></i></a>)";
			pricePattern = Pattern.compile(priceRegex);
		} catch (Exception e) {
			logger.error("init collect pattern error", e);
		}
	}

	@Override
	public int spider(SpiderConfigDO config) {
		if (config == null || !StringUtils.endsWithIgnoreCase(config.getSite(), "mogujie") || StringUtils.isBlank(config.getUrl())) {
			return 0;
		}
		String url = config.getUrl();
		String bookHtml = HtmlClient.wgetPage(url);
		String bookKey = null;
		Matcher profileMatcher = profilePattern.matcher(bookHtml);
		if (profileMatcher.find()) {
			String bookJson = profileMatcher.group(0);
			int firstIndex = bookJson.indexOf(",book:\"");
			int lastIndex = bookJson.indexOf("\",", firstIndex);
			bookKey = bookJson.substring(firstIndex + 7, lastIndex);
		}
		int count = 0;
		if (StringUtils.isNotBlank(bookKey)) {
			String category = config.getCategory();
			int page = 1;
			String moguId = null;
			do {
				Map<String, String> map = new HashMap<String, String>();
				map.put("page", String.valueOf(page));
				map.put("totalCol", "4");
				map.put("lastTweetId", moguId);
				map.put("book", bookKey);
				String json = HtmlClient.wgetPage(AJAX_URL, map);
				if (StringUtils.isBlank(json)) {
					spiderItemLog.info("json is blank, bookkey:" + bookKey);
					break;
				}
				Matcher jsonMatcher = jsonPattern.matcher(json);
				while (jsonMatcher.find()) {
					try {
						SpiderItemDO spiderItemDO = new SpiderItemDO();
						moguId = jsonMatcher.group();
						String moguHtml = HtmlClient.wgetPage(ITEM_BASE_URL + moguId);

						Matcher itemMatcher = itemPattern.matcher(moguHtml);
						Long itemId = null;
						String picUrl = null;
						while (itemMatcher.find()) {
							String itemIdStr = itemMatcher.group(0);
							itemId = Long.valueOf(itemIdStr);
							picUrl = itemMatcher.group(2);
						}

						Matcher priceMatcher = pricePattern.matcher(moguHtml);
						BigDecimal price = null;
						while (priceMatcher.find()) {
							String priceStr = priceMatcher.group();
							price = new BigDecimal(priceStr);
						}
						spiderItemDO.setExtId(moguId);
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
						logger.error("spider item error,moguId: " + moguId, e);
					}
				}
				page++;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (page < 10);
			spiderItemLog.info("spider success, key: " + StringUtils.substring(bookKey, bookKey.length() - 20)
					+ "; count: " + count);
		}
		return count;
	}

	public static void main(String[] args) {
		MoguItemSpider a = new MoguItemSpider();
		a.spider(null);
	}

}
