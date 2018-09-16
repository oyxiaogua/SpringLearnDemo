package com.beanfactory;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyPrototypeDestroyPrototypeBeansPostProcessor
		implements BeanPostProcessor, BeanFactoryAware, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(MyPrototypeDestroyPrototypeBeansPostProcessor.class);

	private BeanFactory beanFactory;

	private final List<Object> prototypeBeans = new LinkedList<>();

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanFactory.isPrototype(beanName)) {
			synchronized (prototypeBeans) {
				prototypeBeans.add(bean);
			}
		}
		return bean;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public void destroy() throws Exception {
		synchronized (prototypeBeans) {
			for (Object bean : prototypeBeans) {
				if (bean instanceof DisposableBean) {
					DisposableBean disposable = (DisposableBean) bean;
					try {
						log.info("destory prototype object,bean={}",disposable);
						disposable.destroy();
					} catch (Exception e) {
						log.error("destroy error",e);
					}
				}
			}
			prototypeBeans.clear();
		}
	}
}