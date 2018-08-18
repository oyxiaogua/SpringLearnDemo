package com.bean;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationDateBean {
	@Bean(name = "myDate")
	public Date myDate() {
		return new Date();
	}
}
