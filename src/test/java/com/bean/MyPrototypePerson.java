package com.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MyPrototypePerson implements InitializingBean, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(MyPrototypePerson.class);

	private String name;

	private String name2;

	public MyPrototypePerson() {
		log.info("myPrototypePerson constructor");
	}

	public void myInit() {
		log.info("myPrototypePerson init被调用");
	}

	public void myDestroy() {
		log.info("myPrototypePerson destroy被调用");
	}

	@PostConstruct
	public void myPostConstruct() {
		log.info("myPrototypePerson @PostConstruct被调用");
	}

	@PreDestroy
	public void myPreConstruct() {
		log.info("myPrototypePerson @PreConstruct被调用");
	}

	public void afterPropertiesSet() throws Exception {
		log.info("myPrototypePerson InitializingBean afterPropertiesSet被调用");
	}

	public void destroy() throws Exception {
		log.info("myPrototypePerson DisposableBean destory被调用");
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
		return "myPrototypePerson [name=" + name + ", name2=" + name2 + "]";
	}

}