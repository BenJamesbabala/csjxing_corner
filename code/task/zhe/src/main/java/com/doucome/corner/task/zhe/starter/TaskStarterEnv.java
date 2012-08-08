package com.doucome.corner.task.zhe.starter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author ze2200
 *
 */
public class TaskStarterEnv {
	
	private ApplicationContext ctx;
	
	public TaskStarterEnv(String appXml) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
}
