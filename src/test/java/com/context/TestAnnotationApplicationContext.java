package com.context;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bean.Car;
import com.bean.MyConfigValue3;

public class TestAnnotationApplicationContext {
	private static final Logger log = LoggerFactory.getLogger(TestAnnotationApplicationContext.class);

	@Test
	public void testAnnotationConfigApplicationContext() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyCarConfigurationBeans.class);
		Car car = ctx.getBean("car", Car.class);
		log.info("rtn={}", car);
		ctx.close();

		ctx = new AnnotationConfigApplicationContext(MyConfigValueConfigurationBean.class);
		MyConfigValue3 configValue3 = ctx.getBean("myConfigValue3", MyConfigValue3.class);
		log.info("rtn={}", configValue3);
		ctx.close();
	}

	@Test
	public void testAnnotationConfigApplicationContextRefresh() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MyCarConfigurationBeans.class);
		ctx.refresh();
		Car car = ctx.getBean("car", Car.class);
		log.info("rtn={}", car);
		ctx.close();
	}

}
