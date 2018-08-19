package com.bean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/testBean.properties")
@Import({MyTestBean1.class,MyTestBean2.class,MyTestBeanImportBeanDefinitionRegistrar.class, MyTestBeanImportSelector.class})
@ImportResource("classpath:/myConfigBeanDefine.xml")
public class MyConfigurationTestBean {
	@Value("${datasource.jdbc}") 
	private String jdbc;
	
	@Value("${datasource.pwd:defaultPwd}") 
	private String pwd;
	
	@Bean
	public Map<String, String> myPropMap(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("jdbc", jdbc);
		map.put("pwd", pwd);
		return map;
	}
}
