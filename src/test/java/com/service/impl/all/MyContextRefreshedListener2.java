package com.service.impl.all;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyContextRefreshedListener2 {
	private static final Logger log = LoggerFactory.getLogger(MyContextRefreshedListener2.class);

	@EventListener
	public void handleContextRefreshedEvent(ContextRefreshedEvent event) {
		// 根容器为Spring容器
		if (event.getApplicationContext().getParent() == null) {
			Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(Component.class);
			for (Object bean : beans.values()) {
				log.info("myContextRefreshedListener2 component bean={}",
						bean == null ? "null" : bean.getClass().getName());
			}
		}
	}
}
