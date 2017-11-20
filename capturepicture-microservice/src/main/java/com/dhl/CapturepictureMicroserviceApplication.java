package com.dhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CapturepictureMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapturepictureMicroserviceApplication.class, args);
	}
}
