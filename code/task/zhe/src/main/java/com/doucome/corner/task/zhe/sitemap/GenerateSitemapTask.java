package com.doucome.corner.task.zhe.sitemap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.VelocityMergeService;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.enums.TaokeItemSearchSortEnums;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.taobao.model.TaokeItemSearchCondition;
import com.doucome.corner.biz.core.utils.DateTool;
import com.doucome.corner.biz.dal.dataobject.DdzConfigDO;
import com.doucome.corner.biz.zhe.service.DdzConfigService;

/**
 * 类GenerateSitemapTask.java的实现描述：sitemap生成
 * 
 * @author ib 2012-6-25 上午01:07:21
 */
public class GenerateSitemapTask {

    private static final Log         logger              = LogFactory.getLog(GenerateSitemapTask.class);

    private static final int         MAX_PAGES           = 1;

    private DdzConfigService         ddzConfigService;
    private TaobaokeServiceDecorator taobaokeServiceDecorator;
    private VelocityMergeService     velocityMergeService;

    private int                      count               = 0;
    private List<String>             sitemapXmlFileNames = new ArrayList<String>();
    private String                   basePath            = "D:\\corner\\sitemap\\output\\";
    private String                   date;

    public static void main(String[] args) {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            DdzConfigService ddzConfigService = (DdzConfigService) ctx.getBean("ddzConfigService");
            TaobaokeServiceDecorator taobaokeServiceDecorator = (TaobaokeServiceDecorator) ctx.getBean("taobaokeServiceDecorator");
            VelocityMergeService velocityMergeService = (VelocityMergeService) ctx.getBean("velocityMergeService");
            GenerateSitemapTask task = new GenerateSitemapTask();
            task.setDdzConfigService(ddzConfigService);
            task.setTaobaokeServiceDecorator(taobaokeServiceDecorator);
            task.setVelocityMergeService(velocityMergeService);
            task.run();
        } catch (Exception e) {
            logger.error("send report email error", e);
        }
    }

    public void run() {
        date = DateTool.format(new Date(), DateTool.DATE_FORMAT_DAY);
        List<DdzConfigDO> configs = ddzConfigService.queryForModule("sitemap");
        List<Long> ids = new ArrayList<Long>(20);
        for (DdzConfigDO cfg : configs) {
            Pagination pagination = new Pagination(1);
            pagination.setSize(10);
            pagination.setMaxPages(MAX_PAGES);
            TaokeItemSearchCondition condition = new TaokeItemSearchCondition();
            condition.setKeyword(cfg.getValue());
            condition.setSort(TaokeItemSearchSortEnums.commissionRate_desc.getValue());
            QueryResult<TaobaokeItemDTO> internalItems = taobaokeServiceDecorator.getItems(
                                                                                           condition,
                                                                                           TaobaokeFields.taoke_item_fields,
                                                                                           pagination);
            if (internalItems != null) {
                int i = 1;
                do {
                    handleItems(internalItems, ids);

                    if (i >= internalItems.getPagination().getTotalPages()) {
                        break;
                    }

                    pagination.setPage(i++);
                    internalItems = taobaokeServiceDecorator.getItems(condition, TaobaokeFields.taoke_item_fields,
                                                                      pagination);
                    System.out.println(cfg.getValue());
                    System.out.println(ids.size());

                } while (true);
            }
        }
        if (ids.size() > 0) {
            createXML(ids);
        }
        createIndexXML();
    }

    public void handleItems(QueryResult<TaobaokeItemDTO> items, List<Long> ids) {
        for (TaobaokeItemDTO item : items.getItems()) {
            if (item.getNumIid() != null) {
                ids.add(item.getNumIid());
                if (ids.size() >= 1000) {
                    createXML(ids);
                    ids.clear();
                }
            }
        }
    }

    public void createIndexXML() {
        String content = "";
        FileWriter write = null;
        try {
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("files", sitemapXmlFileNames);
            velocityContext.put("date", date);
            content = velocityMergeService.doExecute("/sitemap/sitemapIndex.vm", velocityContext);
            File file = new File(basePath + "sitemap_index.xml");
            write = new FileWriter(file);
            write.write(content);
            write.flush();
        } catch (Exception e) {
            logger.error("write xml error ", e);
        } finally {
            if (write != null) {
                try {
                    write.close();
                } catch (IOException e) {
                    logger.error(" write.close() error ", e);
                }
            }
        }
    }

    public void createXML(List<Long> ids) {
        String content = "";
        FileWriter write = null;
        try {
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("items", ids);
            velocityContext.put("date", date);
            content = velocityMergeService.doExecute("/sitemap/sitemapTemp.vm", velocityContext);
            String fileName = "sitemap_" + date + "_" + ++count + ".xml";
            sitemapXmlFileNames.add(fileName);
            File file = new File(basePath + fileName);
            write = new FileWriter(file);
            write.write(content);
            write.flush();
        } catch (Exception e) {
            logger.error("write xml error ", e);
        } finally {
            if (write != null) {
                try {
                    write.close();
                } catch (IOException e) {
                    logger.error(" write.close() error ", e);
                }
            }
        }
    }

    public void setDdzConfigService(DdzConfigService ddzConfigService) {
        this.ddzConfigService = ddzConfigService;
    }

    public void setTaobaokeServiceDecorator(TaobaokeServiceDecorator taobaokeServiceDecorator) {
        this.taobaokeServiceDecorator = taobaokeServiceDecorator;
    }

    public void setVelocityMergeService(VelocityMergeService velocityMergeService) {
        this.velocityMergeService = velocityMergeService;
    }

}
