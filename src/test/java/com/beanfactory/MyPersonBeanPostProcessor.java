package com.beanfactory;

import java.beans.PropertyDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyPersonBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements  BeanPostProcessor {
	private static final Logger log = LoggerFactory.getLogger(MyPersonBeanPostProcessor.class);

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.info("postProcessBeforeInitialization被调用");
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.info("postProcessAfterInitialization被调用");
		return bean;
	}

	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		log.info("InstantiationAwareBeanPostProcessor postProcessBeforeInstantiation被调用");
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}

	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		log.info("InstantiationAwareBeanPostProcessor postProcessAfterInstantiation被调用");
		return super.postProcessAfterInstantiation(bean, beanName);
	}

	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
			String beanName) throws BeansException {
		log.info("InstantiationAwareBeanPostProcessor postProcessPropertyValues被调用");
		return super.postProcessPropertyValues(pvs, pds, bean, beanName);
	}
	
	
	

}
