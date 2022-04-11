package com.busBalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication @EnableEurekaClient
public class BusBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusBalancerApplication.class, args);
	}

}
