package com.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyPerson
		implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(MyPerson.class);

	private String name;
	private String name2;
	private ApplicationContext context;

	public MyPerson() {
		log.info("myperson constructor");
	}

	public void myInit() {
		log.info("myperson init被调用");
	}

	public void myDestroy() {
		log.info("myperson destroy被调用");
	}
	
	@PostConstruct
	public void myPostConstruct(){
		log.info("myperson @PostConstruct被调用");
	}
	
	@PreDestroy
	public void myPreConstruct(){
		log.info("myperson @PreConstruct被调用");
	}

	public void destroy() throws Exception {
		log.info("DisposableBean destory被调用");
	}

	public void afterPropertiesSet() throws Exception {
		log.info("InitializingBean afterPropertiesSet被调用");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("setApplicationContext被调用");
		this.context=applicationContext;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		log.info("setBeanFactory被调用,beanFactory");
	}

	public void setBeanName(String beanName) {
		log.info("setBeanName被调用,beanName:" + beanName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		log.info("setName方法被调用");
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
		log.info("setName2方法被调用");
	}

	public String toString() {
		return "MyPerson [name=" + name + ", name2=" + name2 + "]";
	}
	
	public void printAllBeanFactoryPostProcessor(){
		Map<String, BeanFactoryPostProcessor> bbpMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, BeanFactoryPostProcessor.class, true, false);
		List<String> keyList=new ArrayList<String>(bbpMap.keySet());
		log.info("all keys={}",keyList);
	}

	
}