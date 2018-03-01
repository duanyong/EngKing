package com.reaier.engking;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void contextLoads() {
		String str = "authorization header will be automatically generated when you send the request. Learn more about authorization";
		String strs[] = StringUtils.split(str, " ");


		logger.info(String.valueOf(strs));

	}

	@Test
	public void testWord() {
		String  str="SUN公司被Oracle收购，是否意味着java被逼上了死路？";
		String s = "\\w+";
		Pattern pattern=Pattern.compile(s);
		Matcher ma=pattern.matcher(str);

		while(ma.find()){
			System.out.println(ma.group());
		}
	}

}
