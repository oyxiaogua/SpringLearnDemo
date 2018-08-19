package com.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.service.InterfaceHelloService;

@Configuration
public class MyConditionConfigurationBean {

    @Bean(name = "helloService")
    @Conditional(WindowsCondition.class)
    public InterfaceHelloService windowsService() {
        return new com.service.impl.dev.DevHelloServiceImpl();
    }

    @Bean(name = "helloService")
    @Conditional(LinuxCondition.class)
    public InterfaceHelloService linuxEmailerService() {
    	return new com.service.impl.product.ProductHelloServiceImpl();
    }
}