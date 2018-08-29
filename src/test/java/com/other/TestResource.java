package com.other;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class TestResource {
	private static final Logger log = LoggerFactory.getLogger(TestResource.class);

	@Test
	public void testResourcePatternResolver() throws Exception {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		Resource[] metaInfResources = resourcePatternResolver.getResources("classpath*:*.xml");
		for (Resource res : metaInfResources) {
			log.info("result={}",res.getURL());
		}
	}
}
