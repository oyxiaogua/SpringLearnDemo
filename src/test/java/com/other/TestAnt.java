package com.other;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

public class TestAnt {
	@Test
	public void testAntPathMatcher() {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		pathMatcher.setCachePatterns(true);
		pathMatcher.setCaseSensitive(true);
		pathMatcher.setTrimTokens(true);
		pathMatcher.setPathSeparator("/");

		Assert.assertTrue(pathMatcher.match("a*", "ab"));
		Assert.assertTrue(pathMatcher.match("a*/**/a", "ab/asd/a"));
		Assert.assertTrue(pathMatcher.match("a*/**/a", "ab/asdsa/asdasd/a"));
	}
}
