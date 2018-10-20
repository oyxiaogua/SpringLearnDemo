package com.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

import com.bean.MyConfigurationDateBean;

public class MyPersonBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	private static final Logger log = LoggerFactory.getLogger(MyPersonBeanPostProcessor.class);

	public MyPersonBeanFactoryPostProcessor() {
		super();
		log.info("MyPersonBeanFactoryPostProcessor constructor");
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		log.info("MyPersonBeanFactoryPostProcessor调用postProcessBeanFactory方法");
		BeanDefinition bd = arg0.getBeanDefinition("person1");
		bd.getPropertyValues().addPropertyValue("name2", "postProcessBeanFactorySetValue");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) arg0;
        beanFactory.registerBeanDefinition("myConfigurationDateBean", new RootBeanDefinition(MyConfigurationDateBean.class));
		ConfigurationClassPostProcessor postProcessor = new ConfigurationClassPostProcessor();
        postProcessor.postProcessBeanDefinitionRegistry(beanFactory);
        
        bd = arg0.getBeanDefinition("book");
        if(bd!=null){
    		bd.getPropertyValues().addPropertyValue("name", "myPersonBeanFactoryPostProcessor");
        }
	}

}