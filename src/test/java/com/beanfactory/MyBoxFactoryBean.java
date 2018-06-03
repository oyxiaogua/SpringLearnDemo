package com.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.bean.Box;
import com.bean.Car;

public class MyBoxFactoryBean implements BeanFactoryPostProcessor {

	public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) throws BeansException {
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) bf;
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Box.class);
		beanDefinitionBuilder.addPropertyReference("car", "car1");
		beanFactory.registerBeanDefinition("box", beanDefinitionBuilder.getRawBeanDefinition());
		beanFactory.registerSingleton("box2", new Box());

		BeanDefinitionBuilder beanDefinitionBuilder2 = BeanDefinitionBuilder.genericBeanDefinition(Box.class);
		beanDefinitionBuilder2.addPropertyValue("name", "testBox");
		beanDefinitionBuilder2.addPropertyValue("car", new Car());
		beanFactory.registerBeanDefinition("box3", beanDefinitionBuilder2.getRawBeanDefinition());

	}

}
