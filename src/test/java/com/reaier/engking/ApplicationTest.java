package com.reaier.engking;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.TimeZone;

@Log4j2
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	ConfigurableApplicationContext context;

	@BeforeEach
	protected void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		System.setProperty("sun.net.http.allowRestrictedHeaders",   "true");

		System.setProperty("javax.net.debug",                       "all");
		System.setProperty("https.protocols",                       "TLSv1.2,TLSv1.1,SSLv3");

		Application.context = context;
	}
}