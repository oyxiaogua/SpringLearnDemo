package com.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import com.bean.Book;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	private static final Logger log = LoggerFactory.getLogger(MyBeanDefinitionRegistryPostProcessor.class);

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		log.info("MyBeanDefinitionRegistryPostProcessor调用postProcessBeanFactory方法");
		RootBeanDefinition bookBean = new RootBeanDefinition(Book.class);
		// 新增Bean定义
		registry.registerBeanDefinition("book", bookBean);
	}

}