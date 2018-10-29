package com.service.impl.all;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.InterfaceMyRefService1;

public class MyRefService1Impl implements InterfaceMyRefService1{
	private static final Logger log = LoggerFactory.getLogger(MyRefService1Impl.class);

	private String name;
	public void printName() {
		log.info("name={}",name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
