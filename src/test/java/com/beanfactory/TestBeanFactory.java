package com.beanfactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.bean.Box;
import com.bean.Car;
import com.bean.MyConfigValue2;
import com.context.TestAnnotationApplicationContext;

public class TestBeanFactory {
	private static final Logger log = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);

	@Test
	public void testLoadBeanWithXmlBeanDefinitionReader() {
		Resource res = new ClassPathResource("/carBeanDefine.xml");
		BeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) bf);
		reader.loadBeanDefinitions(res);
		Car car1 = (Car) bf.getBean("car1");
		log.info("car={}", car1);
		((DefaultListableBeanFactory) bf).destroySingletons();
	}

	@Test
	public void testLoadBeanWithClassPathXmlApplicationContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/carBeanDefine.xml");
		Car car1 = (Car) context.getBean("car1");
		log.info("car={}", car1);
		((ClassPathXmlApplicationContext) context).close();
	}

	@Test
	public void testCreateBeanWithBeanFactoryPostProcessor() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/carBeanDefine.xml","/boxBeanDefine.xml"});  
		Car car1 = (Car) context.getBean("car1");
		log.info("car={}", car1);
		Box box1 = (Box) context.getBean("box");
		log.info("box1={}", box1);
		Box box2 = (Box) context.getBean("box2");
		log.info("box2={}", box2);
		Box box3 = (Box) context.getBean("box3");
		log.info("box3={}", box3);
		((ClassPathXmlApplicationContext) context).close();
	}
	
	@Test
	public void testBeanPropValueRef() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/myConfigBeanDefine.xml");  
		MyConfigValue2 config2 = (MyConfigValue2) context.getBean("myConfigValue2");
		log.info("config2={}", config2);
		((ClassPathXmlApplicationContext) context).close();
	}
}
