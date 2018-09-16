package com.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

public class MySingleDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor{
	private static final Logger log = LoggerFactory.getLogger(MySingleDestructionAwareBeanPostProcessor.class);

	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		log.info("destory single object,name={}",beanName);
	}

}
