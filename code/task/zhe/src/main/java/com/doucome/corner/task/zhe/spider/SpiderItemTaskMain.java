package com.doucome.corner.task.zhe.spider;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.dal.SpiderConfigDAO;
import com.doucome.corner.biz.dal.SpiderItemDAO;
import com.doucome.corner.biz.dal.dataobject.SpiderConfigDO;
import com.doucome.corner.task.zhe.spider.impl.MeiliItemSpider;
import com.doucome.corner.task.zhe.spider.impl.MoguItemSpider;

public class SpiderItemTaskMain {
	private static final Log spiderItemLog = LogFactory.getLog(LogConstant.spiderItem_log);
	private static final Log logger = LogFactory.getLog(SpiderItemTaskMain.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		spiderItemLog.info("start to spider mogujie item.");
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			SpiderItemDAO spiderItemDAO = (SpiderItemDAO) ctx.getBean("spiderItemDAO");
			SpiderConfigDAO spiderConfigDAO = (SpiderConfigDAO) ctx.getBean("spiderConfigDAO");
			TaobaokeServiceDecorator taobaokeServiceDecorator = (TaobaokeServiceDecorator) ctx
					.getBean("taobaokeServiceDecorator");
			MoguItemSpider moguItemSpider = new MoguItemSpider();
			moguItemSpider.setSpiderItemDAO(spiderItemDAO);
			moguItemSpider.setTaobaokeServiceDecorator(taobaokeServiceDecorator);
			MeiliItemSpider meiliItemSpider = new MeiliItemSpider();
			meiliItemSpider.setSpiderItemDAO(spiderItemDAO);
			meiliItemSpider.setTaobaokeServiceDecorator(taobaokeServiceDecorator);

			List<SpiderConfigDO> spiderConfigs = spiderConfigDAO.queryEnableList();
			for (SpiderConfigDO config : spiderConfigs) {
				try {
					int count = moguItemSpider.spider(config);
					if (count <= 0) {
						count = meiliItemSpider.spider(config);
					}
					spiderItemLog.info("spider success for config id: " + config.getId() + " ; count: " + count);
					try {
						Thread.sleep(1000 * 300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					spiderItemLog.error("spider error for config id: " + config.getId(), e);
				}
			}
			spiderItemLog.info("spider item end.");
		} catch (Exception e) {
			logger.error("spider item error", e);
		}
		System.exit(0);

	}

}
