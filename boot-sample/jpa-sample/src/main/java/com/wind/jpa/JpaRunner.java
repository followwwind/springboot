package com.wind.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Title: JpaRunner
 * @Package com.wind.sample
 * @Description: TODO
 * @author wind
 * @date 2018/10/17 8:24
 * @version V1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.wind")
public class JpaRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaRunner.class, args);
	}
}
