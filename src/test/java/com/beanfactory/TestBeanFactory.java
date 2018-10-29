package com.beanfactory;

import java.util.Date;
import java.util.Map;
import java.util.Set;

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
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

import com.bean.Book;
import com.bean.Box;
import com.bean.Car;
import com.bean.MyConditionConfigurationBean;
import com.bean.MyConfigValue2;
import com.bean.MyConfigurationDateBean;
import com.bean.MyConfigurationTestBean;
import com.bean.MyHelloServiceConfigurationBean;
import com.bean.MyPerson;
import com.bean.MyPrototypePerson;
import com.context.TestAnnotationApplicationContext;
import com.service.InterfaceHelloService;
import com.service.InterfaceMyRefService2;
import com.service.impl.all.TestSayService;

import cn.hutool.json.JSONUtil;

public class TestBeanFactory {
	private static final Logger log = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);

	@Test
	public void testXmlQualifier() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/myTestQualifier.xml");
		context.start();
		TestSayService testSayService = context.getBean(TestSayService.class);
		testSayService.callSayHello();
		context.close();
	}
	
	@Test
	public void testAnnoConditionConfig() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConditionConfigurationBean.class);
		InterfaceHelloService service= context.getBean(InterfaceHelloService.class);
		service.sayHello();
		context.close();
	}
	@Test
	public void testAnnoConfigProfile() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "product");
		context.register(MyHelloServiceConfigurationBean.class);
		context.refresh();
		String[] beanDefinitionNameArr = context.getBeanDefinitionNames();
		for (String beanDefineName : beanDefinitionNameArr) {
			log.info("bean={}", beanDefineName);
		}
		InterfaceHelloService helloService=null;
		if(context.containsBean("productHelloServiceImpl")){
			helloService=(InterfaceHelloService) context.getBean("productHelloServiceImpl");
			helloService.sayHello();
		}
		if(context.containsBean("devHelloServiceImpl")){
			helloService=(InterfaceHelloService) context.getBean("devHelloServiceImpl");
			helloService.sayHello();
		}
		testSimpleMetadataReaderFactory(context);
		log.info("-------------------------------------");
		testMetadataReaderFactory(context);
		log.info("-------------------------------------");
		context.close();
	}
	
	/**
	 * 打印标注了Component的类
	 * @param context
	 * @throws Exception
	 */
	private void testMetadataReaderFactory(ApplicationContext context) throws Exception{
		ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(context);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(context);
        Resource[] resources = resolver.getResources("classpath*:com/service/impl/all/**/*.class");
        for (final Resource resource : resources) {
            final MetadataReader mdReader = metaReader.getMetadataReader(resource);
            final AnnotationMetadata am = mdReader.getAnnotationMetadata();
            Set<String> types = am.getAnnotationTypes();
            for(String type : types) {
           	 if(type.equals(Component.class.getName())) {
           		 log.info("name={}",resource.getFilename());
           	 }
            }
        }
	}
	
	/**
	 * 打印标注了Component的类
	 * @param context
	 * @throws Exception
	 */
	private void testSimpleMetadataReaderFactory (ApplicationContext context) throws Exception{
		//implements ResourceLoaderAware获取resourceLoader
		final String packageSearchPath = "classpath*:com/service/impl/all/**/*.class";
		final Resource[] resources =context.getResources(packageSearchPath);
		final SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory(context);
		for (final Resource resource : resources) {
             final MetadataReader mdReader = factory.getMetadataReader(resource);
             final AnnotationMetadata am = mdReader.getAnnotationMetadata();
             Set<String> types = am.getAnnotationTypes();
             for(String type : types) {
            	 if(type.equals(Component.class.getName())) {
            		 log.info("name={}",resource.getFilename());
            	 }
             }
         }
	}

	@Test
	public void testXmlConfigProfile() {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "product");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/myTestProfile.xml");
		context.start();
		String[] beanDefinitionNameArr = context.getBeanDefinitionNames();
		for (String beanDefineName : beanDefinitionNameArr) {
			log.info("bean={}", beanDefineName);
		}
		InterfaceHelloService helloService=null;
		if(context.containsBean("productHelloServiceImpl")){
			helloService=(InterfaceHelloService) context.getBean("productHelloServiceImpl");
			helloService.sayHello();
		}
		if(context.containsBean("devHelloServiceImpl")){
			helloService=(InterfaceHelloService) context.getBean("devHelloServiceImpl");
			helloService.sayHello();
		}
		((ClassPathXmlApplicationContext) context).close();
	}

	@Test
	public void testXmlConfigurationImport() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/myTestBeanApplicationContext.xml");
		String[] beanDefinitionNameArr = context.getBeanDefinitionNames();
		for (String beanDefineName : beanDefinitionNameArr) {
			log.info("bean={}", beanDefineName);
		}
		Map<String, String> propMap = (Map<String, String>) context.getBean("myPropMap");
		log.info("prop={}", JSONUtil.toJsonPrettyStr(propMap));
		MyConfigValue2 config2 = (MyConfigValue2) context.getBean("myConfigValue2");
		log.info("config2={}", config2);
		((ClassPathXmlApplicationContext) context).close();
	}

	@Test
	public void testAnnoConfigurationImport() {
		// ApplicationContext context = new
		// AnnotationConfigApplicationContext(MyConfigurationTestBean.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MyConfigurationTestBean.class);
		context.refresh();
		String[] beanDefinitionNameArr = context.getBeanDefinitionNames();
		for (String beanDefineName : beanDefinitionNameArr) {
			log.info("bean={}", beanDefineName);
		}
		Map<String, String> propMap = (Map<String, String>) context.getBean("myPropMap");
		log.info("prop={}", JSONUtil.toJsonPrettyStr(propMap));
		MyConfigValue2 config2 = (MyConfigValue2) context.getBean("myConfigValue2");
		log.info("config2={}", config2);
		context.close();
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
		person1.printAllBeanFactoryPostProcessor();
		MyPrototypePerson person2 = (MyPrototypePerson) ac.getBean("person2");
		log.info("person2={}", person2);
		Date myDate = (Date) ac.getBean("myDate");
		log.info("myDate={}", DateFormatUtils.ISO_DATETIME_FORMAT.format(myDate));
		Book book= (Book) ac.getBean("book");
		log.info("book={}", book);
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
		Car car2 = (Car) bf.getBean("car2");
		log.info("car={}", car2);
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
	
	@Test
	public void testBeanAutowireByName() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/beanRefService1.xml");
		InterfaceMyRefService2 service1 = (InterfaceMyRefService2) context.getBean("myRefService2_1");
		service1.printName2();
		
		InterfaceMyRefService2 service2 = (InterfaceMyRefService2) context.getBean("myRefService2_2");
		service2.printName2();
		((ClassPathXmlApplicationContext) context).close();
	}
}
