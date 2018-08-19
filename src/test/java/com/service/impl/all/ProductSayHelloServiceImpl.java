package com.service.impl.all;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.InterfaceSayHelloService;

public class ProductSayHelloServiceImpl implements InterfaceSayHelloService {
	private static final Logger log = LoggerFactory.getLogger(ProductSayHelloServiceImpl.class);

	public void sayHello() {
		log.info("product say hello");
	}

}
