package com.dhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(value = MultipartAutoConfiguration.class)
public class UploadpictureMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UploadpictureMicroserviceApplication.class, args);
	}
}
