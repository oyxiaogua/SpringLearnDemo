package com.service.impl.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.service.InterfaceHelloService;

@Component
public class ProductHelloServiceImpl implements InterfaceHelloService {
	private static final Logger log = LoggerFactory.getLogger(ProductHelloServiceImpl.class);

	@Value("${config.testName:productDefault}")
	private String name;

	public void sayHello() {
		log.info("product hello,name={}",name);
	}
}
