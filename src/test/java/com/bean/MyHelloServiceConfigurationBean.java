package com.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.service.impl.all"})
public class MyHelloServiceConfigurationBean {

}
