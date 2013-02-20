package com.doucome.corner.task.zhe.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.SpiderItemDAO;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;

/**
 * @author ib 2012-9-8 ÏÂÎç6:06:51
 */
public class CollectMogu {

    private static final Log         spiderItemLog = LogFactory.getLog(LogConstant.spiderItem_log);
    private static final Log         logger        = LogFactory.getLog(CollectMogu.class);

    private static final String      AJAX_URL      = "http://www.mogujie.com/book/ajax";
    private static final String      ITEM_BASE_URL = "http://www.mogujie.com/note/";

    private static Pattern           jsonPattern;
    private static Pattern           itemPattern;
    private static Pattern           pricePattern;
    static {
        try {
            String jsonRegex = "(?<= tid=\\\\\")[a-z0-9]{7}(?=\\\\\" bwp=)";
            jsonPattern = Pattern.compile(jsonRegex);
            String itemRegex = "(?<=<a rel=\"nofollow\" target=\"_blank\" href=\"http://mogujie.cn/tgo/\\?to=http%3A%2F%2Fitem.taobao.com%2Fitem.htm%3Fid%3D)\\d+?(?=&id=(.*)\" class=\"show_big qplus_link\">\\s+<img alt=\"\" title=\"\" src=\"(.*?)\">)";
            itemPattern = Pattern.compile(itemRegex);
            String priceRegex = "(?<=class=\"buy_link fl\" rel=\"nofollow\"><b( class=\"c_t\")?>.?)(\\d+\\.\\d+)?(?=\\s*È¥ÌÔ±¦¹ºÂò</b><i></i></a>)";
            pricePattern = Pattern.compile(priceRegex);
        } catch (Exception e) {
            logger.error("init collect pattern error", e);
        }
    }

    private SpiderItemDAO            spiderItemDAO;
    private TaobaokeServiceDecorator taobaokeServiceDecorator;

    /**
     * @param args
     */
    public static void main(String[] args) {
        spiderItemLog.info("start to spider mogujie item.");

        if (args.length < 1) {
            spiderItemLog.info("has not file to spider.");
            return;
        }

        String filePath = args[0];
        List<String> bookKeyList = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            File file = new File(filePath);
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = reader.readLine()) != null) {
                bookKeyList.add(str.trim());
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            SpiderItemDAO spiderItemDAO = (SpiderItemDAO) ctx.getBean("spiderItemDAO");
            TaobaokeServiceDecorator taobaokeServiceDecorator = (TaobaokeServiceDecorator) ctx.getBean("taobaokeServiceDecorator");
            CollectMogu collection = new CollectMogu();
            collection.setSpiderItemDAO(spiderItemDAO);
            collection.setTaobaokeServiceDecorator(taobaokeServiceDecorator);
            for (String bookKey : bookKeyList) {
                try {
                    collection.run(bookKey);
                    spiderItemLog.info("spider success for book key: " + bookKey);
                    try {
                        Thread.sleep(1000 * 300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    spiderItemLog.error("spider error for book key: " + bookKey, e);
                }
            }
            spiderItemLog.info("spider mogujie item end.");
        } catch (Exception e) {
            logger.error("spider mogujie item error", e);
        }
        System.exit(0);

    }

    private void run(String bookKey) {
        String[] strs = bookKey.split("\\|\\|\\|");
        if (strs.length < 1) {
            return;
        }
        String book = strs[0];
        String category = "";
        if (strs.length > 1) {
            category = strs[1];
        }
        int page = 1;
        int count = 0;
        String moguId = null;
        do {
            Map<String, String> map = new HashMap<String, String>();
            map.put("page", String.valueOf(page));
            map.put("totalCol", "4");
            map.put("lastTweetId", moguId);
            map.put("book", book);
            String json = HtmlClient.wgetPage(AJAX_URL, map);
            if (StringUtils.isBlank(json)) {
                spiderItemLog.info("json is blank, bookkey:" + bookKey);
                return;
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
                    spiderItemDO.setSpiderFrom("mogujie-note");
                    spiderItemDO.setItemId(itemId);
                    spiderItemDO.setPicUrl(picUrl);
                    spiderItemDO.setPrice(price);
                    spiderItemDO.setCategory(category);
                    TaobaokeItemDTO itemDTO = taobaokeServiceDecorator.widgetConventItem(String.valueOf(itemId), "",
                                                                                   TaobaokeFields.taoke_item_fields);
                    if (itemDTO != null) {
                        spiderItemDO.setPrice(itemDTO.getPrice());
                        spiderItemDO.setTitle(itemDTO.getTitle());
                        spiderItemDO.setCommission(itemDTO.getCommission());
                        if (itemDTO.getCommissionRate() != null) {
                            spiderItemDO.setRate(DecimalUtils.divide(itemDTO.getCommissionRate(),
                                                                     BigDecimal.valueOf(10000l)));
                        }
                        spiderItemDO.setVolume(itemDTO.getVolume());
                        spiderItemDO.setCreditScore(itemDTO.getSellerCreditScore());
                        spiderItemDAO.insertItem(spiderItemDO);
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

    public void setSpiderItemDAO(SpiderItemDAO spiderItemDAO) {
        this.spiderItemDAO = spiderItemDAO;
    }

    public void setTaobaokeServiceDecorator(TaobaokeServiceDecorator taobaokeServiceDecorator) {
        this.taobaokeServiceDecorator = taobaokeServiceDecorator;
    }

}
