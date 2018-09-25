package com.service.impl.all;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component 
public class MyContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger log = LoggerFactory.getLogger(MyContextRefreshedListener.class);
  
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 根容器为Spring容器 
		if(event.getApplicationContext().getParent()==null){
			Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(Component.class);
			for (Object bean:beans.values()){
				log.info("component bean={}",bean==null?"null":bean.getClass().getName());
			}
		}
	}
}