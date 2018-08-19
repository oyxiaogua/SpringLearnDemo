package com.service.impl.all;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.InterfaceSayHelloService;

public class DevSayHelloServiceImpl implements InterfaceSayHelloService{
	private static final Logger log = LoggerFactory.getLogger(DevSayHelloServiceImpl.class);

	public void sayHello() {
		log.info("dev say hello");
	}

}
