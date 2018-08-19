package com.service.impl.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.service.InterfaceHelloService;

@Component
public class DevHelloServiceImpl implements InterfaceHelloService {
	private static final Logger log = LoggerFactory.getLogger(DevHelloServiceImpl.class);

	@Value("${config.testName:devDefault}")
	private String name;

	public void sayHello() {
		log.info("dev hello,name={}",name);
	}
}
