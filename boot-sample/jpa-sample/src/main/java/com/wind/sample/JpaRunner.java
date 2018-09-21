package com.wind.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wind
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.wind")
//@EnableCaching(proxyTargetClass = true)
public class JpaRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaRunner.class, args);
	}
}
