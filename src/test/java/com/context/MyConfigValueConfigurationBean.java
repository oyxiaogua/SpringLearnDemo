package com.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.bean.MyConfigValue3;

@Configuration
@ImportResource("classpath:/myConfigBeanDefine.xml")
public class MyConfigValueConfigurationBean {

	@Bean(name = "myConfigValue3")
	public MyConfigValue3 buildMyConfigValue3() {
		MyConfigValue3 config = new MyConfigValue3();
		config.setId("3");
		return config;
	}
}
