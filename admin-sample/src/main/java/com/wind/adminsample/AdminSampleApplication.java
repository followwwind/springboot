package com.wind.adminsample;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wind
 */
@SpringBootApplication
@EnableAdminServer
public class AdminSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminSampleApplication.class, args);
	}
}
