package com.dhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class ApiGatewayMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayMicroserviceApplication.class, args);
	}
}
