package com.beanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;

public class MySmartInitializingSingleton implements SmartInitializingSingleton{
	private static final Logger log = LoggerFactory.getLogger(MySmartInitializingSingleton.class);

	public void afterSingletonsInstantiated() {
		log.info("mySmartInitializingSingleton afterSingletonsInstantiated.........");
	}

}
