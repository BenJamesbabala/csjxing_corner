package com.doucome.corner.task.zhe.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.business.DcQqUserRegisterBO;

/**
 * @author ib 2012-9-8 ÏÂÎç6:06:51
 */
public class AddUser {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String filePath = args[0];
        List<String> infoList = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            File file = new File(filePath);
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = reader.readLine()) != null) {
                infoList.add(str.trim());
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
            DcQqUserRegisterBO dcQqUserRegisterBO = (DcQqUserRegisterBO) ctx.getBean("dcQqUserRegisterBO");
            for (String info : infoList) {
                String[] infos = info.split("\\|\\|\\|");
                if (infos.length > 1) {
                    QqUserInfoModel userInfoModel = new QqUserInfoModel();
                    String uid = UUIDUtils.random20() + UUIDUtils.random20();
                    userInfoModel.setOpenId(StringUtils.substring(uid, 0, 30));
                    userInfoModel.setPlatform("qzone");
                    userInfoModel.setGender(QqGenderEnums.Female);
                    userInfoModel.setNickName(infos[1]);
                    userInfoModel.setFigureUrl(infos[0]);
                    DcUserDO user = dcQqUserRegisterBO.registerUser(userInfoModel);
                    if (user != null) {
                        System.out.println("add user id:" + user.getUserId());
                    }
                }
            }
        } catch (Exception e) {
        }
        System.exit(0);

    }

}
