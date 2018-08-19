package com.beanfactory;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.bean.Box;
import com.bean.Car;
import com.bean.MyConfigValue2;
import com.bean.MyConfigurationDateBean;
import com.bean.MyConfigurationTestBean;
import com.bean.MyPerson;
import com.context.TestAnnotationApplicationContext;

import cn.hutool.json.JSONUtil;

public class TestBeanFactory {
	private static final Logger log = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);

	@Test
	public void testXmlConfigurationImport() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/myTestBeanApplicationContext.xml");
		String[] beanDefinitionNameArr = context.getBeanDefinitionNames();
        for (String beanDefineName : beanDefinitionNameArr) {
        	log.info("bean={}",beanDefineName);
        }
        Map<String, String> propMap=(Map<String, String>) context.getBean("myPropMap");
        log.info("prop={}",JSONUtil.toJsonPrettyStr(propMap));
        MyConfigValue2 config2 = (MyConfigValue2) context.getBean("myConfigValue2");
		log.info("config2={}", config2);
		((ClassPathXmlApplicationContext) context).close();
	}
	@Test
	public void testConfigurationImport() {
		//ApplicationContext context = new AnnotationConfigApplicationContext(MyConfigurationTestBean.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MyConfigurationTestBean.class);
		context.refresh();
		String[] beanDefinitionNameArr = context.getBeanDefinitionNames();
        for (String beanDefineName : beanDefinitionNameArr) {
        	log.info("bean={}",beanDefineName);
        }
        Map<String, String> propMap=(Map<String, String>) context.getBean("myPropMap");
        log.info("prop={}",JSONUtil.toJsonPrettyStr(propMap));
        MyConfigValue2 config2 = (MyConfigValue2) context.getBean("myConfigValue2");
		log.info("config2={}", config2);
        ((AnnotationConfigApplicationContext) context).close();
	}

	@Test
	public void testConfigurationClassPostProcessor() {
		ConfigurationClassPostProcessor postProcessor = new ConfigurationClassPostProcessor();
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		registry.registerBeanDefinition("test", new RootBeanDefinition(MyConfigurationDateBean.class));
		postProcessor.postProcessBeanDefinitionRegistry(registry);
		BeanDefinition myDateBean = registry.getBeanDefinition("myDate");
		log.info("myDateBean={}", myDateBean);
	}

	@Test
	public void testLoadBeanByApplicationContext() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("/myPersonApplicationContext.xml");
		log.info("xml加载完毕");
		MyPerson person1 = (MyPerson) ac.getBean("person1");
		log.info("person={}", person1);
		Date myDate = (Date) ac.getBean("myDate");
		log.info("myDate={}", DateFormatUtils.ISO_DATETIME_FORMAT.format(myDate));
		log.info("关闭容器");
		((ClassPathXmlApplicationContext) ac).close();
	}

	@Test
	public void testLoadBeanByBeanFactory() {
		Resource res = new ClassPathResource("/myPersonApplicationContext.xml");
		BeanFactory factory = new DefaultListableBeanFactory();
		BeanDefinitionReader bdr = new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
		bdr.loadBeanDefinitions(res);
		DefaultListableBeanFactory bf = (DefaultListableBeanFactory) factory;
		log.info("xml加载完毕");
		// beanFactory需要手动注册beanPostProcessor类的方法
		bf.addBeanPostProcessor(new MyPersonBeanPostProcessor());

		MyPerson person1 = (MyPerson) bf.getBean("person1");
		log.info("person={}", person1);
		log.info("关闭容器");
		bf.destroySingletons();
	}

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
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "/carBeanDefine.xml", "/boxBeanDefine.xml" });
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
