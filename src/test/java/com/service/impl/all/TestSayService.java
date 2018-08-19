package com.service.impl.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.service.InterfaceSayHelloService;

public class TestSayService {
	@Autowired
	@Qualifier("dev")
	private InterfaceSayHelloService sayHelloService;

	public void callSayHello() {
		sayHelloService.sayHello();
	}

	public InterfaceSayHelloService getSayHelloService() {
		return sayHelloService;
	}

	public void setSayHelloService(InterfaceSayHelloService sayHelloService) {
		this.sayHelloService = sayHelloService;
	}

}
