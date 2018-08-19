package com.service.impl.all;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.service.InterfaceHelloService;

@Component
@Profile(value = "product")
@PropertySource("classpath:/config-${spring.profiles.active}.properties")
public class ProductHelloServiceImpl implements InterfaceHelloService {
	private static final Logger log = LoggerFactory.getLogger(ProductHelloServiceImpl.class);

	@Value("${testName:productDef}")
	private String name;

	public void sayHello() {
		log.info("product hello,name={}",name);
	}
}
